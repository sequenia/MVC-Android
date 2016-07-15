package com.sequenia.mvc.models;

import com.sequenia.mvc.objects.Info;
import com.sequenia.sequeniamvc.MVC;

import java.util.List;

/**
 * Created by chybakut2004 on 14.07.16.
 */

public interface InfoModel {

    void getInfo(int tryIndex, InfoListener infoListener, MVC.Model.ModelErrorListener errorListener);

    void getInfoList(int tryIndex, InfoListListener infoListListener, MVC.Model.ModelErrorListener errorListener);

    void getInfoListFromCache(InfoListListener infoListListener);

    interface InfoListListener {
        void onInfoListLoaded(List<Info> infoList);
    }

    interface InfoListener {
        void onInfoLoaded(Info info);
    }
}
