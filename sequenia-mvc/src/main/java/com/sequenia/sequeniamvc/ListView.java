package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Операции для экрана со списком
 *
 * Created by chybakut2004 on 15.07.16.
 */

public interface ListView<T> extends MVC.View {

    /**
     * Задает список, который отобразится на экране
     * @param items список
     */
    void setList(List<T> items);

    void setListVisibility(boolean visibility);

    void setLoadingVisibility(boolean visibility);

    void setEmptyScreenVisibility(boolean visibility);
}
