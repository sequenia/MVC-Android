package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public interface ListWithCacheController<O, T extends ListView<O>> extends ListController<O, T> {

    /**
     * Здесь нужно реализовать доставание данных из кеша
     */
    void loadListFromCache();

    /**
     * Этот метод нужно вызвать после того, как данные загрузились из кеша
     * @param cachedList данные из кеша
     */
    void onListFromCacheLoaded(List<O> cachedList);

    boolean dataFromCacheLoaded();
}
