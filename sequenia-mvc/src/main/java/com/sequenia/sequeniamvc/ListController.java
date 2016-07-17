package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * Операции для контроллера со списком элементов.<br/>
 * <br/>
 * O - тип данных, отображаемый в списке.<br/>
 * T - Экран, отображающий список<br/>
 * <br/>
 * Created by chybakut2004 on 15.07.16.
 */

public interface ListController<O, T extends MVC.View> extends MVC.Controller<T> {

    /**
     * Загружает список с данными<br/>
     * @param hideList true, если список нужно скрыть во время загрузки
     */
    void loadData(boolean hideList);

    /**
     * В данном методе нужно организовать загрузку данных.<br/>
     * После загрузки должен быть вызван метод onListLoaded.<br/>
     * Если произошла ошибка, должен быть вызван метод onListLoadingError.<br/>
     */
    void loadList();

    /**
     * Метод должен быть вызван при успешной загрузке данных
     * @param list список с данными
     */
    void onListLoaded(List<O> list);

    /**
     * Метод должен быть вызван при ошибке загрузки данных
     */
    void onListLoadingError();

    /**
     * @return true, если идет загрузка
     */
    boolean isLoading();

    void showList(List<O> items);

    void setListVisibility(boolean visibility);

    void setLoadingVisibility(boolean visibility);

    void setEmptyScreenVisibility(boolean visibility);
}
