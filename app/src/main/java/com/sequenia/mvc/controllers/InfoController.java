package com.sequenia.mvc.controllers;

import com.sequenia.mvc.models.InfoModel;
import com.sequenia.mvc.models.TestInfoModel;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoView;
import com.sequenia.sequeniamvc.MVC;
import com.sequenia.sequeniamvc.SimpleDataController;

/**
 * Контроллер, который оперирует данными типа Info и отображает их на экране.
 *
 * Унаследован от контроллера с какими-то данными.
 *
 * Для работы нужно реализовать метод загрузки данных и указать,
 * через какие методы контроллер будет общаться с View.
 *
 * Created by chybakut2004 on 17.07.16.
 */

public class InfoController extends SimpleDataController<Info, InfoView> {

    private InfoModel infoModel;

    private int tryIndex;

    public InfoController() {
        infoModel = new TestInfoModel();
        tryIndex = 0;
    }

    @Override
    public void onTakeView(InfoView view, boolean firstTime) {
        super.onTakeView(view, firstTime);

        getView().setRefreshButtonEnabled(!isLoading());
    }

    @Override
    public void loadData() {
        getView().setRefreshButtonEnabled(false);

        infoModel.getInfo(tryIndex, new InfoModel.InfoListener() {
            @Override
            public void onInfoLoaded(Info info) {
                tryIndex += 1;
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                }
                onDataLoaded(info);
            }
        }, new MVC.Model.ModelErrorListener() {
            @Override
            public void onError(MVC.Model.ModelError error) {
                tryIndex += 1;
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                    getView().showMessage(error.getMessage());
                }
                onDataLoadingError();
            }
        });
    }

    @Override
    public void showData(Info data) {
        getView().showInfo(data);
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
