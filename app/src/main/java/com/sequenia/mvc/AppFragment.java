package com.sequenia.mvc;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Базовый класс для всех фрагментов в приложении.
 *
 * Реализует интерфейс, в котором описаны все действия, которые может делать любой экран приложения.
 *
 * Если во всем приложении нужно сменить вид диалоговых сообщений, это можно сделать единожды здесь.
 *
 * Created by chybakut2004 on 14.07.16.
 */

public class AppFragment extends Fragment implements AppView {

    // Ссылка на показанный диалог
    private ProgressDialog progressDialog;

    @Override
    public void showLoadingDialog(String text) {
        // А данном проекте диалог загрузки показывается через ProgressDialog.

        if(progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(text);
            progressDialog.setCancelable(true);
            progressDialog.show();
        } else {
            progressDialog.setMessage(text);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        hideLoadingDialog();
    }

    @Override
    public void hideLoadingDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showMessage(String message) {
        // Тут может быть любая реализация показа сообщений,
        // например, через DialogFragment.
        Toast.makeText(getContext().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
