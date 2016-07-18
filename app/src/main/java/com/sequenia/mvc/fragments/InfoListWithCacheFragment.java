package com.sequenia.mvc.fragments;

import com.sequenia.mvc.controllers.InfoListWithCacheController;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoListView;
import com.sequenia.sequeniamvc.DataController;

import java.util.List;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListWithCacheFragment extends InfoListFragment {

    @Override
    protected DataController<List<Info>, InfoListView> createController() {
        return new InfoListWithCacheController<>();
    }
}
