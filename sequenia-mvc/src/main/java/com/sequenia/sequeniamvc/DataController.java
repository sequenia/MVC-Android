package com.sequenia.sequeniamvc;


/**
 * Контроллер для экрана с какими-то данными.<br/>
 * <br/>
 * O - тип данных на экране<br/>
 * T - View, которая отображает данные<br/>
 * <br/>
 * Реализация этого интерфейса должна предоставить готовый контроллер, который:<br/>
 * - Начнет загрузку данных при открытии экрана, показав индикатор загрузки.<br/>
 * - Корректно восстановит данные при пересоздании экрана.<br/>
 * - Начнет фоновую загрузку при возвращении на экран.<br/>
 * - Будет показывать индикаторы загрузки, скрывать и открывать контент, если нужно.<br/>
 * <br/>
 * Created by chybakut2004 on 17.07.16.
 */

public interface DataController<O, T extends MVC.View> extends MVC.Controller<T> {

    /**
     * Загрузка данных
     * @param showLoading true, если нужно показать индикатор загрузки.
     * @param hideContent true, если нужно скрыть весь контент во время загрузки.
     */
    void loadData(boolean showLoading, boolean hideContent);

    /**
     * Загрузка данных. Просто загрузка и все.<br/>
     * В этом метода нужно реализовать загрузку данных.<br/>
     * По окончанию загрузки нужно вызвать метод onDataLoaded.<br/>
     * При ошибки загрузки нужно вызвать метод onDataLoadingError.<br/>
     * Данные показывать не нужно, они покажутся сами, если реализован метод showData.<br/>
     */
    void loadData();

    /**
     * Должен быть вызван после окончания загрузки,<br/>
     * чтобы сохранить данные в оперативной памяти и отобразить их на экране.<br/>
     * @param data данные
     */
    void onDataLoaded(O data);

    /**
     * Должен быть вызван после окончания загрузки,<br/>
     * чтобы скрыть индикатор загрузки.<br/>
     */
    void onDataLoadingError();

    /**
     * @return true, если идет загрузка
     */
    boolean isLoading();

    /**
     * Здесь нужно вызвать метод View для показа данных
     * @param data данные
     */
    void showData(O data);

    /**
     * Показ/скрытие данных
     * @param visibility видимость
     */
    void setContentVisibility(boolean visibility);

    /**
     * Показ/скрытие загрузки
     * @param visibility видимость
     */
    void setLoadingVisibility(boolean visibility);

    /**
     * Показ/скрытие пустого экрана
     * @param visibility видимость
     */
    void setEmptyScreenVisibility(boolean visibility);
}
