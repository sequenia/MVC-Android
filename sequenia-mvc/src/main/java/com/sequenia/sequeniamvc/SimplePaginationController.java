package com.sequenia.sequeniamvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chybakut2004 on 20.07.16.
 */

public abstract class SimplePaginationController<I, O, T extends MVC.View> extends SimplePaginationWithCacheController<I, O, T> {

    @Override
    public void loadDataFromCache() {
        onDataFromCacheLoaded(new ArrayList<O>());
    }

}
