package com.sequenia.mvc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paginate.Paginate;
import com.sequenia.mvc.controllers.InfoPaginationListController;
import com.sequenia.mvc.controllers.InfoPaginationWithCacheController;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoPaginationListView;

import java.util.List;

/**
 * Created by chybakut2004 on 21.07.16.
 */

public class InfoPaginationWithCacheFragment extends InfoListFragmentWithoutLogic implements InfoPaginationListView {

    private InfoPaginationWithCacheController<InfoPaginationListView> controller;

    private Paginate paginate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new InfoPaginationWithCacheController<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.loadData(true, true, true);
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
    public void showInfoPage(List<Info> page) {
        adapter.addItems(page);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void bindPagination() {
        paginate = Paginate.with(recyclerView, new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                controller.loadData(true, false);
            }

            @Override
            public boolean isLoading() {
                return controller.isLoading();
            }

            @Override
            public boolean hasLoadedAllItems() {
                return !controller.hasMore();
            }
        }).setLoadingTriggerThreshold(10)
                .addLoadingListItem(true)
                .build();
    }

    @Override
    public void unbindPagination() {
        if (paginate != null) {
            paginate.unbind();
        }
    }

    @Override
    public void setPaginationLoadingVisibility(boolean visibility) {
        if (paginate != null) {
            paginate.setHasMoreDataToLoad(visibility);
        }
    }
}