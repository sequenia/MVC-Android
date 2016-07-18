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
}
