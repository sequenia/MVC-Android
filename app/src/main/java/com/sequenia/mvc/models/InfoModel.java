package com.sequenia.mvc.models;

import com.sequenia.mvc.objects.Info;
import com.sequenia.sequeniamvc.MVC;

import java.util.List;

/**
 * Описание модели для загрузки каких-то данных (Info)
 *
 * Created by chybakut2004 on 14.07.16.
 */

public interface InfoModel {

    /**
     * Получить данные
     * @param tryIndex номер попытки
     * @param infoListener успех
     * @param errorListener ошибка
     */
    void getInfo(int tryIndex, InfoListener infoListener, MVC.Model.ModelErrorListener errorListener);

    /**
     * Получить список данных
     * @param tryIndex номер попытки
     * @param infoListListener успех
     * @param errorListener ошибка
     */
    void getInfoList(int tryIndex, InfoListListener infoListListener, MVC.Model.ModelErrorListener errorListener);

    /**
     * Получить данные из кеша
     * @param infoListListener успех
     */
    void getInfoListFromCache(InfoListListener infoListListener);

    interface InfoListListener {
        void onInfoListLoaded(List<Info> infoList);
    }

    interface InfoListener {
        void onInfoLoaded(Info info);
    }
}
