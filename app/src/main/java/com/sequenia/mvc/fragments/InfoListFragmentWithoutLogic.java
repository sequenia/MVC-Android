package com.sequenia.mvc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sequenia.mvc.R;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Экран со списком.
 *
 * Здесь определено, каким образом список отображается на экране.
 *
 * Логика загрузки данных находится в контроллере.
 *
 * На экране есть:
 * - Список
 * - Пустой экран, если пришедший список пустой
 * - Кнопка обновления
 *
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListFragmentWithoutLogic extends AppFragment implements InfoListView {

    protected RecyclerView recyclerView; // Список
    private View loadingView;          // View с индикатором загрузки
    private View emptyScreen;          // View с сообщением, что список пустой
    protected Button refreshButton;      // Кнопка обновления

    protected ListAdapter adapter;       // Адаптер для отображения элементов

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(getActivity().getLayoutInflater());
        recyclerView.setAdapter(adapter);

        loadingView = view.findViewById(R.id.progress);
        emptyScreen = view.findViewById(R.id.empty_screen);
        refreshButton = (Button) view.findViewById(R.id.refresh);

        return view;
    }

    @Override
    public void showInfoList(List<Info> infoList) {
        adapter.setItems(infoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setContentVisibility(boolean visibility) {
        recyclerView.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setLoadingVisibility(boolean visibility) {
        loadingView.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setEmptyScreenVisibility(boolean visibility) {
        emptyScreen.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setRefreshButtonEnabled(boolean enabled) {
        refreshButton.setEnabled(enabled);
    }

    public static class ListAdapter extends RecyclerView.Adapter {

        private List<Object> items;
        private LayoutInflater inflater;

        public ListAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new InfoViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((InfoViewHolder) holder).bind((Info) items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setItems(List items) {
            if(this.items == null) {
                this.items = new ArrayList<>();
            }

            this.items.clear();
            this.items.addAll(items);
        }

        public void addItems(List items) {
            if(this.items == null) {
                this.items = new ArrayList<>();
            }

            this.items.addAll(items);
        }
    }

    private static class InfoViewHolder extends RecyclerView.ViewHolder {

        private TextView fullInfo;

        public InfoViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(createView(R.layout.item_info, inflater, parent));

            fullInfo = (TextView) itemView.findViewById(R.id.full_info);
        }

        public void bind(Info info) {
            fullInfo.setText(info.getFullInfo());
        }
    }

    public static View createView(int layoutId, LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(layoutId, parent, false);
    }
}
