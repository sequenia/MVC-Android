package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Created by chybakut2004 on 17.07.16.
 */

public abstract class SimpleListWithCacheController<O, T extends MVC.View> extends SimpleDataWithCacheController<List<O>, T> {

    @Override
    protected boolean dataIsEmpty() {
        return data != null && data.size() == 0;
    }

    @Override
    public void onDataFromCacheLoaded(List<O> cachedData) {
        dataFromCacheLoaded = true;
        if(data != null && data.size() == 0){
            data = null;
        } else {
            data = cachedData;
        }
        showDataOnScreen();
        loadDataIfNotLoading();
    }
}
