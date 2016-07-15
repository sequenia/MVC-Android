package com.sequenia.mvc;

import com.sequenia.sequeniamvc.MVC;

/**
 * Created by chybakut2004 on 14.07.16.
 */

public interface InfoModel {

    void getInfo(int tryIndex, InfoListener infoListener, MVC.Model.ModelErrorListener errorListener);

    interface InfoListener {
        void onInfoLoaded(Info info);
    }
}
