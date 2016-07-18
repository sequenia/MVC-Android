package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Created by chybakut2004 on 18.07.16.
 */

public abstract class SimpleListController<O, T extends MVC.View> extends SimpleDataController<List<O>, T> {

    @Override
    protected boolean dataIsEmpty() {
        return data != null && data.size() == 0;
    }
}
