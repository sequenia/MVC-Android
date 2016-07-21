package com.sequenia.sequeniamvc;

import java.util.List;

/**
 * I - тип данных, которыми определяется страница.
 *
 * Это может быть целое число для пагинации по номеру страницы.
 * Это может быть id данных, с которых начинается следующая страница.
 *
 * Created by chybakut2004 on 20.07.16.
 */

public interface PaginationController<I, O, T extends MVC.View> extends DataController<List<O>, T> {

    /**
     * Показ страницы с данными.
     * Этот метод должен быть вызван в методе showData, чтобы не вводить в заблуждение программистов
     * @param page страница с данными
     */
    void showPage(List<O> page);

    void showAllData(List<O> allData);

    /**
     * Загрузка страницы с данными.
     * Этот метод должен быть вызван в меоде loadData. Пользователь должен перекрывать именно loadPage,
     * чтобы не входить в ступор
     */
    void loadPage(I page);

    /**
     * Вызывается после успешной загрузки страницы для увеличения счетчика страниц
     */
    I incrementPage(I current);

    /**
     * @return начальная страница
     */
    I getStartPage();

    /**
     * Настройка слушателя пагинации, который при далекой прокрутке загружает новую страницу
     */
    void bindPagination();

    /**
     * Сброс слушателя пагинации
     */
    void unbindPagination();

    /**
     * Настройка видимости индикатор загрузки внизу списка
     * @param visibility видимость
     */
    void setPageLoadingVisibility(boolean visibility);

    void setAllLoadingVisibility(boolean visibility);

    void loadData(boolean showLoading, boolean hideContent, boolean clearList);

    boolean hasMore();
}
