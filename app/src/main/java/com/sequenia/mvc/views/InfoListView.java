package com.sequenia.mvc.views;

import com.sequenia.mvc.objects.Info;

import java.util.List;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public interface InfoListView extends AppView {

    void showInfoList(List<Info> infoList);

    void setContentVisibility(boolean visibility);
    void setLoadingVisibility(boolean visibility);
    void setEmptyScreenVisibility(boolean visibility);

    void setRefreshButtonEnabled(boolean enabled);
}
