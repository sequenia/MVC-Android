package com.sequenia.mvc.controllers;

import com.sequenia.mvc.models.InfoModel;
import com.sequenia.mvc.models.TestInfoModel;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoPaginationListView;
import com.sequenia.sequeniamvc.MVC;
import com.sequenia.sequeniamvc.SimplePaginationController;

import java.util.List;

/**
 * Created by chybakut2004 on 20.07.16.
 */

public class InfoPaginationListController<T extends InfoPaginationListView>
        extends SimplePaginationController<Integer, Info, T> {

    private InfoModel infoModel;

    public InfoPaginationListController() {
        infoModel = new TestInfoModel();
    }

    @Override
    public void onTakeView(T view, boolean firstTime) {
        super.onTakeView(view, firstTime);

        getView().setRefreshButtonEnabled(!isLoading());
    }

    @Override
    public void showPage(List<Info> page) {
        getView().showInfoPage(page);
    }

    @Override
    public void showAllData(List<Info> allData) {
        getView().showInfoList(allData);
    }

    @Override
    public void loadPage(Integer page) {
        getView().setRefreshButtonEnabled(false);
        infoModel.getInfoPage(0, page, new InfoModel.InfoListListener() {
            @Override
            public void onInfoListLoaded(List<Info> infoList) {
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                }
                onDataLoaded(infoList);
            }
        }, new MVC.Model.ModelErrorListener() {
            @Override
            public void onError(MVC.Model.ModelError error) {
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                    getView().showMessage("Ошибка");
                }
            }
        });
    }

    @Override
    public Integer incrementPage(Integer current) {
        return current + 1;
    }

    @Override
    public Integer getStartPage() {
        return 0;
    }

    @Override
    public boolean hasMore() {
        return currentPage < 3;
    }

    @Override
    public void bindPagination() {
        getView().bindPagination();
    }

    @Override
    public void unbindPagination() {
        getView().unbindPagination();
    }

    @Override
    public void setPageLoadingVisibility(boolean visibility) {
        getView().setPaginationLoadingVisibility(visibility);
    }

    @Override
    public void setAllLoadingVisibility(boolean visibility) {
        getView().setLoadingVisibility(visibility);
    }

    @Override
    public void setContentVisibility(boolean visibility) {
        getView().setContentVisibility(visibility);
    }

    @Override
    public void setEmptyScreenVisibility(boolean visibility) {
        getView().setEmptyScreenVisibility(visibility);
    }
}
