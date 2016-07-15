package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Операции для контроллера со списком элементов
 *
 * Created by chybakut2004 on 15.07.16.
 */

public interface ListController<O, T extends ListView<O>> extends MVC.Controller<T> {

    /**
     * Загружает список с данными
     * @param hideList true, если список нужно скрыть во время загрузки
     */
    void loadData(boolean hideList);

    void loadList();
    void onListLoaded(List<O> list);
    void onListLoadingError();

    boolean isLoading();
}
