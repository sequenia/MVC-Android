package com.sequenia.mvc.views;

import com.sequenia.mvc.objects.Info;

import java.util.List;

/**
 * Created by chybakut2004 on 20.07.16.
 */

public interface InfoPaginationListView extends InfoListView {

    void showInfoPage(List<Info> page);

    void bindPagination();

    void unbindPagination();

    void setPaginationLoadingVisibility(boolean visibility);
}
