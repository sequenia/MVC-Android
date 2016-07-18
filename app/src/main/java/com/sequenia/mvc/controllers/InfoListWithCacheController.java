package com.sequenia.mvc.controllers;

import com.sequenia.mvc.models.InfoModel;
import com.sequenia.mvc.models.TestInfoModel;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoListView;
import com.sequenia.sequeniamvc.MVC;
import com.sequenia.sequeniamvc.SimpleListWithCacheController;

import java.util.List;

/**
 * Контроллер для списка с отображением закешированных данных при старте
 *
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListWithCacheController<T extends InfoListView> extends SimpleListWithCacheController<Info, T> {

    private InfoModel infoModel;
    private int tryIndex;

    public InfoListWithCacheController() {
        this.infoModel = new TestInfoModel();
        this.tryIndex = 0;
    }

    @Override
    public void onTakeView(T view, boolean firstTime) {
        super.onTakeView(view, firstTime);

        getView().setRefreshButtonEnabled(!isLoading() && dataFromCacheLoaded());
    }

    @Override
    public void loadData() {
        getView().setRefreshButtonEnabled(false);
        infoModel.getInfoList(tryIndex, new InfoModel.InfoListListener() {
            @Override
            public void onInfoListLoaded(List<Info> infoList) {
                tryIndex += 1;
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                }
                onDataLoaded(infoList);
            }
        }, new MVC.Model.ModelErrorListener() {
            @Override
            public void onError(MVC.Model.ModelError error) {
                tryIndex += 1;
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                    getView().showMessage("Ошибка");
                }
                onDataLoadingError();
            }
        });
    }

    @Override
    public void loadDataFromCache() {
        getView().setRefreshButtonEnabled(false);
        infoModel.getInfoListFromCache(new InfoModel.InfoListListener() {
            @Override
            public void onInfoListLoaded(List<Info> infoList) {
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                }
                onDataFromCacheLoaded(infoList);
            }
        });
    }

    @Override
    public void showData(List<Info> data) {
        getView().showInfoList(data);
    }

    @Override
    public void setContentVisibility(boolean visibility) {
        getView().setContentVisibility(visibility);
    }

    @Override
    public void setLoadingVisibility(boolean visibility) {
        getView().setLoadingVisibility(visibility);
    }

    @Override
    public void setEmptyScreenVisibility(boolean visibility) {
        getView().setEmptyScreenVisibility(visibility);
    }
}
