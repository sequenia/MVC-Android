package com.sequenia.sequeniamvc;

/**
 * Реализация контроллера для экрана с данными, которые при старте отображаются из кеша.
 *
 * Created by chybakut2004 on 17.07.16.
 */

public abstract class SimpleDataWithCacheController<O, T extends MVC.View> extends SimpleDataController<O, T>
            implements DataWithCacheController<O, T> {

    protected boolean dataFromCacheLoaded;

    public SimpleDataWithCacheController() {
        this.dataFromCacheLoaded = false;
    }

    @Override
    protected void showDataAndStartLoading() {
        // Если экран создан первый раз, или данные из кеша не загрузились -
        // нужно все скрыть и показать диалог загрузки
        if(createdFirstTime() || !dataFromCacheLoaded()) {
            setContentVisibility(false);
            setEmptyScreenVisibility(false);
            setLoadingVisibility(true);
        }

        if(createdFirstTime()) {
            // При первом старте экрана нужно взять данные из кеша
            loadDataFromCache();
        } else {
            // Однако если экран пересоздался, и данные из кеша взяты -
            // нужно отобразить данные на экране и начать загрузку новых
            if(dataFromCacheLoaded) {
                showDataOnScreen();
                loadDataIfNotLoading();
            }
        }
    }

    @Override
    public void onDataFromCacheLoaded(O cachedData) {
        // Если данные из кеша загружены - нужно это запомнить,
        // и запомнить данные.
        dataFromCacheLoaded = true;
        data = cachedData;
        // Если данные из кеша достались - указываем, что данные хотя бы раз загружены.
        // При этом загрузка данных начнется в фоне.
        onceLoaded = !dataIsNull() && !dataIsEmpty();
        // После этого - отобразить их и начать фоновую загрузку
        showDataOnScreen();
        loadDataIfNotLoading();
    }

    @Override
    public boolean dataFromCacheLoaded() {
        return dataFromCacheLoaded;
    }
}
