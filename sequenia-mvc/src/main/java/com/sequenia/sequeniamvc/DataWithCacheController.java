package com.sequenia.sequeniamvc;

/**
 * Добавляет функционал отображения данныз из кеша при заходе на экран.
 *
 * Created by chybakut2004 on 17.07.16.
 */

public interface DataWithCacheController<O, T extends MVC.View> extends DataController<O, T> {

    /**
     * В этом метода нужно реализовать загрузку данных из кеша.<br/>
     * В конце загрузки нужно вызвать метод onDataFromCacheLoaded.
     */
    void loadDataFromCache();

    /**
     * Должен быть вызван после окончания загрузки данных из кеша.
     * @param cachedData данные из кеша
     */
    void onDataFromCacheLoaded(O cachedData);

    /**
     * @return true, если данные из кеша загружены
     */
    boolean dataFromCacheLoaded();
}
