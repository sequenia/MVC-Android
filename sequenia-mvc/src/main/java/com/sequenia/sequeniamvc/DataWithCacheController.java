package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Created by chybakut2004 on 17.07.16.
 */

public interface DataWithCacheController<O, T extends MVC.View> extends DataController<O, T> {

    void loadDataFromCache();

    void onDataFromCacheLoaded(O cachedData);

    boolean dataFromCacheLoaded();
}
