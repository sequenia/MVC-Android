package com.sequenia.mvc.views;

import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.AppView;

/**
 * Интерфейс описывает операции, которые может выполнять экран с информацией.
 *
 * Он унаследован от AppView, где описаны базовые операции всех экранов в приложении,
 * чтобы и этот экран умел их делать.
 *
 * Created by chybakut2004 on 14.07.16.
 */

public interface InfoView extends AppView {

    /**
     * Показывает информацию на экране.
     *
     * Этот метод будет вызывать контроллер, когда получит данные.
     *
     * Данные могут быть получены хоть откуда (SharedPreferences, Сервер и т.д.).
     * Это будет определять модель.
     *
     * @param info Информация
     */
    void showInfo(Info info);

    void setContentVisibility(boolean visibility);
    void setLoadingVisibility(boolean visibility);
    void setEmptyScreenVisibility(boolean visibility);


    /**
     * Задает активность кнопки обновить
     * @param enabled true, если активна
     */
    void setRefreshButtonEnabled(boolean enabled);
}
