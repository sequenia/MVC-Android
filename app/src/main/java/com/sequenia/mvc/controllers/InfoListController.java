package com.sequenia.mvc.controllers;

import com.sequenia.mvc.models.InfoModel;
import com.sequenia.mvc.models.TestInfoModel;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoListView;
import com.sequenia.sequeniamvc.MVC;
import com.sequenia.sequeniamvc.SimpleListController;

import java.util.List;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListController<T extends InfoListView> extends SimpleListController<Info, T> {

    private InfoModel infoModel;
    private int tryIndex;

    public InfoListController() {
        this.infoModel = new TestInfoModel();
        this.tryIndex = 0;
    }

    @Override
    public void onTakeView(T view, boolean firstTime) {
        super.onTakeView(view, firstTime);

        getView().setRefreshButtonEnabled(!isLoading());
    }

    @Override
    public void loadList() {
        getView().setRefreshButtonEnabled(false);
        infoModel.getInfoList(tryIndex, new InfoModel.InfoListListener() {
            @Override
            public void onInfoListLoaded(List<Info> infoList) {
                tryIndex += 1;
                onListLoaded(infoList);
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                }
            }
        }, new MVC.Model.ModelErrorListener() {
            @Override
            public void onError(MVC.Model.ModelError error) {
                tryIndex += 1;
                onListLoadingError();
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                    getView().showMessage("Ошибка");
                }
            }
        });
    }
}
