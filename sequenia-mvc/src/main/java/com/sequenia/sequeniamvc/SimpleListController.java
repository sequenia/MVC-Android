package com.sequenia.sequeniamvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация контроллера для списка элементов.
 *
 * Все происходит автоматически:
 * - Восстановление экрана после пересоздания (Отображение данных, если они уже есть)
 * - Фоновое обновление данных при пересоздании
 * - Если загрузка данных началась, а экран пересоздался, данные повторно не грузятся.
 * - Скрытие-показ индикатора загрузки и пустого экрана
 *
 * Created by chybakut2004 on 15.07.16.
 */

public abstract class SimpleListController<O, T extends ListView<O>> extends ControllerWithView<T>
        implements ListController<O, T> {

    protected List<O> items;    // Список элеменов
    protected boolean loading;  // Индикатор загрузки
    protected boolean hideList; // Флаг, показывающий, нужно ли отображать индикатор при начале загрузки

    public SimpleListController() {
        loading = false;
        hideList = true;
    }

    @Override
    public void onTakeView(T view, boolean firstTime) {
        super.onTakeView(view, firstTime);

        resetView();
        showDataOnTakeView();
    }

    /**
     * Показ данных при открытии экрана и начало загрузки данных
     */
    protected void showDataOnTakeView() {
        // Показать данные, если они уже есть.
        // true означает, что нужно показывать пустой экран, если данные уже есть
        showData(true);
        // Начать загрузу данных
        loadDataOnTakeView();
    }

    /**
     * Загрузка данных при открытии экрана
     */
    protected void loadDataOnTakeView() {
        if(loading) {
            // Если идет загрузка - нужно ее показать
            showLoading(hideList);
        } else {
            // Если загрузка не идет - нужно начать новую.
            // Если данные уже есть, то индикатор загрузки не показывается (передается false).
            // Если передать true, то индкатор загрузки будет показан в любом случае.
            loadData(false);
        }
    }

    /**
     * Скрывает все на экране
     */
    protected void resetView() {
        getView().setListVisibility(false);
        getView().setEmptyScreenVisibility(false);
        getView().setLoadingVisibility(false);
    }

    @Override
    public void loadData(boolean hideList) {
        // Перед загрузкой данных нужно запомнить, что идет загрузка,
        // а так же указать, нужно ли показывать индикатор загрузки.
        // Его не нужно показывать при фоновой загрузке.
        this.hideList = hideList;
        this.loading = true;

        // Показ индикатора загрузки, если необходимо
        showLoading(hideList);

        // Загрузка данных
        loadList();
    }

    @Override
    public void onListLoaded(List<O> list) {
        // После успешной загрузки нужно отобразить данные и запомнить, что загрузка кончилась.
        loading = false;
        this.items = list;
        showData(true);
    }

    @Override
    public void onListLoadingError() {
        // При ошибке загрузки нужно указать, что она законилась.
        loading = false;
        if(isViewAttached()) {
            getView().setLoadingVisibility(false);
        }
    }

    /**
     * Отображает данные на экране
     * @param needsShowEmptyScreen нужно ли показывать пустой экран, если пришел пустой список
     */
    protected void showData(boolean needsShowEmptyScreen) {
        if(isViewAttached()) {
            // Нужно все скрыть, если данных нет,
            // Показать пустой экран, если данные есть, но список пустой,
            // Показать список, если он не пустой
            if (items == null) {
                getView().setList(new ArrayList<O>());
                getView().setListVisibility(false);
                getView().setEmptyScreenVisibility(false);
            } else if (items.size() == 0 && needsShowEmptyScreen) {
                getView().setList(items);
                getView().setListVisibility(false);
                getView().setEmptyScreenVisibility(true);
            } else {
                getView().setList(items);
                getView().setListVisibility(true);
                getView().setEmptyScreenVisibility(false);
            }

            // Если показали данные - нужно скрыть загрузку
            getView().setLoadingVisibility(false);
        }
    }

    protected void showLoading(boolean hideList) {
        if(isViewAttached()) {
            // Показывать индикатор загрузки нужно только если:
            // - сказано об этом или
            // - Данные не загружены.

            // Если какие-то данные уже есть, загрузка должна проходить в фоне
            if (hideList || items == null) {
                getView().setListVisibility(false);
                getView().setEmptyScreenVisibility(false);
                getView().setLoadingVisibility(true);
            }
        }
    }

    @Override
    public boolean isLoading() {
        return loading;
    }
}
