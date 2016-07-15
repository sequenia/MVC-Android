package com.sequenia.mvc;

import android.os.Handler;

import com.sequenia.sequeniamvc.MVC;

/**
 * Объекты этого класса занимаются получением данных.
 *
 * Данная реализация генерирует тестовые данные для приложения.
 *
 * Данные могут браться хоть откуда (с сервера, из базы и т.д.)
 *
 * Created by chybakut2004 on 14.07.16.
 */

public class TestInfoModel implements InfoModel {

    @Override
    public void getInfo(final int tryIndex, final InfoListener infoListener, final MVC.Model.ModelErrorListener errorListener) {
        // Эмуляция загрузки данных с сервера. (Задержка в 5 секунд)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Генерация данных или ошибки в зависимости от попытки.
                if(tryIndex % 3 == 0) {
                    Info info = new Info("Петр", "Базов");
                    infoListener.onInfoLoaded(info);
                } else if(tryIndex % 3 == 1) {
                    infoListener.onInfoLoaded(null);
                } else {
                    errorListener.onError(new MVC.Model.ModelError(0, "Ошибка"));
                }
            }
        }, 5000);
    }
}
