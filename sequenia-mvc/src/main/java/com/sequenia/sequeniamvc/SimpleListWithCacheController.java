package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public abstract class SimpleListWithCacheController<O, T extends ListView<O>> extends SimpleListController<O, T>
            implements ListWithCacheController<O, T> {

    private boolean dataFromCacheLoaded;

    public SimpleListWithCacheController() {
        this.dataFromCacheLoaded = false;
    }

    @Override
    protected void showDataOnTakeView() {
        if(createdFirstTime()) {
            // Если экран создан первый раз - нужно взять данные из кеша.
            // Перед этим нужно показать индикатор загрузки.
            showLoading(true);
            loadListFromCache();
        } else {
            // Если экран пересоздан, нужно просто отобразить их, как делалось раньше.
            // Однако, если данные из кеша еще не получены - нужно показать загрузку
            if(dataFromCacheLoaded) {
                super.showDataOnTakeView();
            } else {
                showLoading(true);
            }
        }
    }

    @Override
    public void onListFromCacheLoaded(List<O> cachedList) {
        // После загрузки данных из кеша нужно запомнить, что они загружены,
        // сохранить список,
        // отобразить его,
        // И начать скачивать новые данные
        dataFromCacheLoaded = true;
        if(cachedList != null && cachedList.size() == 0){
            items = null;
        } else {
            items = cachedList;
        }
        super.showDataOnTakeView();
    }

    @Override
    public boolean dataFromCacheLoaded() {
        return dataFromCacheLoaded;
    }
}
