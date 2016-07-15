package com.sequenia.mvc;

/**
 * Интерфейс описывает операции, которые может выполнять главный экран приложения.
 *
 * Он унаследован от AppView, где описаны базовые операции всех экранов в приложении,
 * чтобы и этот экран умел их делать.
 *
 * Created by chybakut2004 on 14.07.16.
 */

public interface MainView extends AppView {

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

    /**
     * Скрывает всю информацию
     */
    void hideInfo();

    /**
     * Задает активность кнопки обновить
     * @param enabled true, если активна
     */
    void setRefreshButtonEnabled(boolean enabled);
}
