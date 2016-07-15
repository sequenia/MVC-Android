package com.sequenia.mvc.views;

import com.sequenia.mvc.objects.Info;
import com.sequenia.sequeniamvc.ListView;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public interface InfoListView extends ListView<Info> {

    void setRefreshButtonEnabled(boolean enabled);
}
