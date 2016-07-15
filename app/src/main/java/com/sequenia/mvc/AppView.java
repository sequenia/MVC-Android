package com.sequenia.mvc;

import com.sequenia.sequeniamvc.MVC;

/**
 * Интерфейс, описывающий все операции, которые может делать любой экран приложения.
 * Должен реализовывать главный интерфейс View, чтобы считаться экраном.
 * Created by chybakut2004 on 14.07.16.
 */

public interface AppView extends MVC.View {

    /**
     * Показывает диалог с индикатором загрузки со стандартным сообщением
     */
    void showLoadingDialog(String text);

    /**
     * Закрывает диалог загрузки
     */
    void hideLoadingDialog();
}
