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
import com.sequenia.mvc.controllers.InfoListController;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListFragment extends AppFragment implements InfoListView {

    private RecyclerView recyclerView;
    private View loadingView;
    private View emptyScreen;
    private Button refreshButton;

    private ListAdapter adapter;

    private InfoListController<InfoListView> controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        controller = new InfoListController<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        loadingView = view.findViewById(R.id.progress);
        emptyScreen = view.findViewById(R.id.empty_screen);
        refreshButton = (Button) view.findViewById(R.id.refresh);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.loadData(true);
            }
        });

        controller.onTakeView(this, savedInstanceState == null);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        controller.onLossView();
    }

    @Override
    public void setList(List<Info> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setListVisibility(boolean visibility) {
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

    private class ListAdapter extends RecyclerView.Adapter {

        private List items;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new InfoViewHolder( getActivity().getLayoutInflater(), parent);
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
            this.items = items;

            if(this.items == null) {
                this.items = new ArrayList<>();
            }
        }
    }

    private class InfoViewHolder extends RecyclerView.ViewHolder {

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
