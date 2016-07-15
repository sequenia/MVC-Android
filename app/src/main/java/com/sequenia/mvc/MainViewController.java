package com.sequenia.mvc;

import com.sequenia.sequeniamvc.ControllerWithView;
import com.sequenia.sequeniamvc.MVC;

/**
 * Контроллер, управляющий главным экраном приложения
 *
 * Здесь стартует загрузка данных,
 * Происходит восстановление данных после пересоздания экрана,
 * а так же решается, какие элементы экрана делать доступными и когда.
 *
 * Created by chybakut2004 on 14.07.16.
 */

public class MainViewController extends ControllerWithView<MainView> {

    private InfoModel infoModel;    // Ссылка на модель, через которую идет получение данных.

    // Загруженные данные
    private Info info;

    // Индикатор загрузки. Нужен, чтобы восстановить диалог загрузки после пересоздания экрана
    private boolean loading;

    // Индикатор того, что данные загружены. От сервера может прийти null, но данные считаются загруженными.
    private boolean loaded;

    // Количество загрузок. Нужно для теста, чтобы в зависимости от попытки возвращать разные данные.
    private int tryIndex;

    public MainViewController() {
        // При создании контроллера нужно создать реализацию модели,
        // а так же задать начальные значения полям.
        infoModel = new TestInfoModel();
        loading = false;
        loaded = false;
        tryIndex = 0;
    }

    @Override
    public void onTakeView(MainView view, boolean firstTime) {
        super.onTakeView(view, firstTime);

        // Здесь контроллер получает ссылку на экран.

        // Если какие-то данные уже были загружены на момент получения ссылки на экран -
        // их нужно отобразить
        if(info == null) {
            if(loaded) {
                getView().showInfo(info);
            } else {
                getView().hideInfo();
            }
        } else {
            getView().showInfo(info);
        }

        if(createdFirstTime()) {
            // Если экран создан первый раз - нужно начать автоматическую загрузку данных.
            getInfo();
        } else {
            // Если при пересоздании экрана идет загрузка - нужно отобразить индикатор загрузки
            // и настроить доступность кнопки обновить.
            if(loading) {
                getView().showLoadingDialog("Загрузка");
                getView().setRefreshButtonEnabled(false);
            } else {
                getView().setRefreshButtonEnabled(true);
            }
        }
    }

    public void getInfo() {
        // Перед началом загрузки нужно отобразить индикатор в интерфейсе,
        // а так же настроить элементы управления
        getView().showLoadingDialog("Загрузка");
        getView().setRefreshButtonEnabled(false);
        loading = true;

        // Загрузка данных идет через модель.
        // Процесс получения данных можно легко поменять во всем приложении,
        // просто сменив реализацию модели или подправив методы текущей.
        infoModel.getInfo(tryIndex, new InfoModel.InfoListener() {
            @Override
            public void onInfoLoaded(Info info) {
                // При успехе нужно сбросить все служебные переменные...
                loading = false;
                loaded = true;
                tryIndex += 1;
                // ... сохранить данные в оперативной памяти...
                MainViewController.this.info = info;
                // ... И ЕСЛИ ЭКРАН ДОСТУПЕН, отобразить данные на экране.
                if(isViewAttached()) {
                    getView().hideLoadingDialog();
                    getView().setRefreshButtonEnabled(true);
                    getView().showInfo(info);
                }
            }
        }, new MVC.Model.ModelErrorListener() {
            @Override
            public void onError(MVC.Model.ModelError error) {
                // Если произошла ошибка - нужно сообщить о ней пользователю

                loading = false;
                tryIndex += 1;
                if(isViewAttached()) {
                    getView().hideLoadingDialog();
                    getView().setRefreshButtonEnabled(true);
                    getView().showMessage(error.getMessage());
                }
            }
        });
    }
}
