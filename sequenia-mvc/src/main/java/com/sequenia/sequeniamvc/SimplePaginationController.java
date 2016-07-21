package com.sequenia.sequeniamvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chybakut2004 on 20.07.16.
 */

public abstract class SimplePaginationController<I, O, T extends MVC.View> extends SimpleListController<O, T>
            implements PaginationController<I, O, T> {

    protected I currentPage;
    protected List<O> currentPageData;
    protected boolean bound;

    public SimplePaginationController() {
        bound = false;
        currentPage = getStartPage();
    }

    @Override
    public void loadData() {
        loadPage(currentPage);
    }

    @Override
    public void onDataLoaded(List<O> data) {
        // При успехе нужно повысить номер текущей страницы
        currentPage = incrementPage(currentPage);
        super.onDataLoaded(data);
    }

    @Override
    protected void saveLoadedData(List<O> data) {
        // Процесс сохранения данных теперь сводится не к простому перетиранию,
        // а к добавлению к текущим данным.

        if(this.data == null) {
            this.data = new ArrayList<>();
        }

        if(!bound) {
            this.data.clear();
        }

        this.currentPageData = data;
        this.data.addAll(data);
    }

    @Override
    public void showData(List<O> data) {
        // Показ данных может быть двух типов:
        // - Показать все данные, которые есть (например, если это пересоздание экрана)
        // - Показать только новую страницу.

        if(bound) {
            showPage(currentPageData);
        } else {
            showAllData(data);
            bindPagination();
            bound = true;
        }
    }

    @Override
    protected boolean loadDataOnTakeView() {
        return createdFirstTime();
    }


    @Override
    public void setLoadingVisibility(boolean visibility) {
        if(bound) {
            setPageLoadingVisibility(visibility);
            setAllLoadingVisibility(false);
        } else {
            setAllLoadingVisibility(visibility);
            setPageLoadingVisibility(false);
        }
    }

    @Override
    public void loadData(boolean showLoading, boolean hideContent, boolean clearList) {
        if(clearList) {
            unbindPagination();
            bound = false;
            currentPage = getStartPage();
        }
        loadData(showLoading, hideContent);
    }

    @Override
    public void onLossView() {
        super.onLossView();
        bound = false;
    }
}
