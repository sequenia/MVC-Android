package com.sequenia.mvc.controllers;

import com.sequenia.mvc.models.InfoModel;
import com.sequenia.mvc.models.TestInfoModel;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoListView;
import com.sequenia.sequeniamvc.MVC;
import com.sequenia.sequeniamvc.SimpleListController;

import java.util.List;

/**
 * Контроллер для списка с информацией.
 *
 * Унаследован от контроллера для списка чего-либо.
 *
 * Здесь указан лишь метод для загрузки данных.
 *
 * Все остальное происходит автоматически:
 * - Восстановление экрана после пересоздания
 * - Фоновое обновление данных при пересоздании
 * - Если загрузка данных началась, а экран пересоздался, данные повторно не грузятся.
 *
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
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                }
                onListLoaded(infoList);
            }
        }, new MVC.Model.ModelErrorListener() {
            @Override
            public void onError(MVC.Model.ModelError error) {
                tryIndex += 1;
                if(isViewAttached()) {
                    getView().setRefreshButtonEnabled(true);
                    getView().showMessage("Ошибка");
                }
                onListLoadingError();
            }
        });
    }

    @Override
    public void showList(List<Info> items) {
        getView().setList(items);
    }

    @Override
    public void setListVisibility(boolean visibility) {
        getView().setListVisibility(visibility);
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
