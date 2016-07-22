package com.sequenia.sequeniamvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chybakut2004 on 21.07.16.
 */

public abstract class SimplePaginationWithCacheController<I, O, T extends MVC.View> extends SimpleListWithCacheController<O, T>
        implements PaginationController<I, O, T> {

    protected I currentPage;
    protected List<O> currentPageData;
    protected boolean bound;
    protected boolean firstPageShown;

    public SimplePaginationWithCacheController() {
        bound = false;
        firstPageShown = false;
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
        firstPageShown = true;
    }

    @Override
    protected void saveLoadedData(List<O> data) {
        // Процесс сохранения данных теперь сводится не к простому перетиранию,
        // а к добавлению к текущим данным.

        if(this.data == null) {
            this.data = new ArrayList<>();
        }

        if(!bound || !firstPageShown) {
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
            if(firstPageShown) {
                showPage(currentPageData);
            } else {
                showAllData(data);
            }
        } else {
            showAllData(data);
            if(dataFromCacheLoaded) {
                bindPagination();
                bound = true;
            }
        }
    }


    @Override
    public void setLoadingVisibility(boolean visibility) {
        if(bound && !contentHided) {
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
            firstPageShown = false;
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
