package com.sequenia.sequeniamvc;

import java.util.List;

/**
 *
 * Created by chybakut2004 on 17.07.16.
 */

public interface DataController<O, T extends MVC.View> extends MVC.Controller<T> {

    void loadData(boolean showLoading, boolean hideContent);

    void loadData();

    void onDataLoaded(O data);

    void onDataLoadingError();

    boolean isLoading();

    void showData(O data);

    void setContentVisibility(boolean visibility);

    void setLoadingVisibility(boolean visibility);

    void setEmptyScreenVisibility(boolean visibility);
}
