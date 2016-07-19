package com.sequenia.sequeniamvc;

/**
 * Реализация контроллера для экрана с данными.
 *
 * Created by chybakut2004 on 17.07.16.
 */

public abstract class SimpleDataController<O, T extends MVC.View> extends ControllerWithView<T>
            implements DataController<O, T> {

    protected O data;               // Данные
    protected boolean loading;      // True, если идет загрузка
    protected boolean loadingShown; // True, если индикатор загрузки показан.
    protected boolean contentHided; // True, если контент при загрузке скрыт.

    // True, если данные хотя бы раз загрузились.
    // Нужно на случай, когда данные загрузились, но равны null.
    // В этом случае проверка на null не работает.
    protected boolean onceLoaded;

    public SimpleDataController() {
        loading = false;      // Загрузки в начале нет
        onceLoaded = false;   // Данные в начале не загружены
        loadingShown = false;
        contentHided = false;
    }

    @Override
    public void onTakeView(T view, boolean firstTime) {
        super.onTakeView(view, firstTime);

        resetView();               // Сброс View
        showDataAndStartLoading(); // Показ имеющихся данных и загрузка новых
    }

    protected void showDataAndStartLoading() {
        showDataOnScreen();       // Показ имеющихся данных
        loadDataIfNotLoading();   // Загрузка новых данных, если они сейчас не загружаются
    }

    /**
     * Сброс параметров View
     */
    protected void resetView() {
        setContentVisibility(false);
        setEmptyScreenVisibility(false);
        setLoadingVisibility(false);
    }

    /**
     * Показывает имеющиеся данные на экране
     */
    protected void showDataOnScreen() {
        if(isViewAttached()) {
            // Если в данных пусто - показать пустой экран.
            // Если данных нет вообще - скрыть весь контент.
            // Если данные есть - показать их.
            if (dataIsEmpty()) {
                setContentVisibility(false);
                setEmptyScreenVisibility(true);
            } else if (dataIsNull()) {
                setContentVisibility(false);
                setEmptyScreenVisibility(false);
            } else {
                showData(data);
                setContentVisibility(true);
                setEmptyScreenVisibility(false);
            }

            // В любом случае - выключить загрузку
            setLoadingVisibility(false);
        }
    }

    /**
     * Загружает данные, если загрузка сейчас не идет.
     */
    protected void loadDataIfNotLoading() {
        // Если процесс загрузки идет - данные загружать не нужно. Нужно показать загрузку
        if(loading) {
            if(isViewAttached()) {
                // Но только его она была показана
                if(loadingShown) {
                    setLoadingVisibility(true);
                }

                // И скрывать контент при загрузке нужно только он скрывался
                if(contentHided) {
                    setContentVisibility(false);
                    setEmptyScreenVisibility(false);
                }
            }
        } else {
            // При захвате экрана данные нужно загрузить если:
            // - Экран создался первый раз или
            // - экран пересоздался, но данные не загружены или
            // - указано, что при пересоздании нужно заново загружать данные
            if(loadDataOnTakeView()) {
                // Показывать индикатор загрузки нужно только в первый раз,
                // так как загрузка повторно должна идти в фоне.
                // Скрыть контент при загрузке нужно только при первой загрузке.
                loadData(showLoadingOnTakeViewLoading(), hideContentOnTakeViewLoading());
            }
        }
    }

    /**
     * @return true, если нужно загрузить данные при захвате View.
     */
    protected boolean loadDataOnTakeView() {
        return createdFirstTime() || !createdFirstTime() && (!onceLoaded || loadDataOnRecreation());
    }

    /**
     * @return true, если нужно загружать данные заново при смене ориентации
     */
    protected boolean loadDataOnRecreation() {
        return false;
    }

    /**
     * @return указывает, нужно ли скрывать контент во время загрузки, которая начинается при захвате View
     */
    protected boolean hideContentOnTakeViewLoading() {
        // Показывать нужно только если данные еще не загружены.
        // Если загружены, загрузка идет в фоне.
        return !onceLoaded;
    }

    /**
     * @return указывает, нужно ли показывать индикатор загрузки, которая начинается при захвате View
     */
    protected boolean showLoadingOnTakeViewLoading() {
        // Показывать нужно, если данные не загружены.
        // Если загружены, то данные должны загружаться в фоне.
        return !onceLoaded;
    }

    @Override
    public void loadData(boolean showLoading, boolean hideContent) {
        this.loading = true;
        this.loadingShown = showLoading;
        this.contentHided = hideContent;

        if(isViewAttached()) {
            if(showLoading) {
                setLoadingVisibility(true);
            }

            if(hideContent) {
                setContentVisibility(false);
                setEmptyScreenVisibility(false);
            }
        }

        loadData();
    }

    @Override
    public void onDataLoaded(O data) {
        // При успешной загрузке нужно сбросить индикатор загрузки,
        // запомнить данные, сохранить, что они загружены
        // и отобразить на экране
        loading = false;
        this.data = data;
        onceLoaded = true;
        loadingShown = false;
        contentHided = false;
        showDataOnScreen();
    }

    @Override
    public void onDataLoadingError() {
        // При ошибке нужно сбросить индикатор загрузки
        // и скрыть его из View.
        loading = false;
        loadingShown = false;
        if(isViewAttached()) {
            setLoadingVisibility(false);
        }
    }

    /**
     * @return true, если данные не загружены
     */
    protected boolean dataIsNull() {
        return data == null;
    }

    /**
     * @return true, если данные загружены, но пустые
     */
    protected boolean dataIsEmpty() {
        return data == null;
    }

    @Override
    public boolean isLoading() {
        return loading;
    }
}
