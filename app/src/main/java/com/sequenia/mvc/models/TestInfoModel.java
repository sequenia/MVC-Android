package com.sequenia.mvc.models;

import android.os.Handler;

import com.sequenia.mvc.objects.Info;
import com.sequenia.sequeniamvc.MVC;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void getInfoList(final int tryIndex, final  InfoListListener infoListListener, final MVC.Model.ModelErrorListener errorListener) {
        // Эмуляция загрузки данных с сервера. (Задержка в 5 секунд)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Генерация данных или ошибки в зависимости от попытки.
                if(tryIndex % 3 == 0) {
                    List<Info> infoList = new ArrayList<>();
                    for(int i = 0; i < 50; i++) {
                        infoList.add(new Info("Имя " + i, "Фамилия " + i));
                    }
                    infoListListener.onInfoListLoaded(infoList);
                } else if(tryIndex % 3 == 1) {
                    infoListListener.onInfoListLoaded(new ArrayList<Info>());
                } else {
                    errorListener.onError(new MVC.Model.ModelError(0, "Ошибка"));
                }
            }
        }, 5000);
    }

    @Override
    public void getInfoListFromCache(final InfoListListener infoListListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Info> infoList = new ArrayList<>();
                for(int i = 3; i < 100; i++) {
                    infoList.add(new Info("Имя " + i, "Фамилия " + i));
                }
                infoListListener.onInfoListLoaded(infoList);
            }
        }, 5000);
    }

    @Override
    public void getInfoPage(int tryIndex, final int page, final InfoListListener infoListListener, MVC.Model.ModelErrorListener errorListener) {
        System.out.println("PAGE " + page);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Генерация данных или ошибки в зависимости от попытки.
                List<Info> infoList = new ArrayList<>();
                for(int i = page * 50; i < page * 50 + 50; i++) {
                    infoList.add(new Info("Имя " + i, "Фамилия " + i));
                }
                infoListListener.onInfoListLoaded(infoList);
            }
        }, 3000);
    }
}
