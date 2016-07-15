package com.sequenia.mvc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoView;
import com.sequenia.mvc.controllers.MainViewController;
import com.sequenia.mvc.R;

/**
 * Фрагмент главного экрана. Создан для сохранения состояния.
 *
 * На экране отображается загруженная с сервера информация: Имя и Фамилия.
 * В процессе загрузки отображается диалог с индикатором.
 *
 * Если в ответ пришли пустые данные - отображается экран с текстом, что данных пока нет.
 * Если возникла ошибка при загрузке - отображается сообщение об ошибке.
 *
 * Есть кнопка обновить, которая повторяет загрузку.
 *
 * (Для теста первая загрузка - успех, вторая загрузка - пустые данные, третья загрузка - ошибка).
 *
 * Created by chybakut2004 on 14.07.16.
 */

public class InfoFragment extends AppFragment implements InfoView {

    // Текстовые поля для отображения загруженных данных
    private TextView firstNameTextView;
    private TextView lastNameTextView;

    // Ссылки на части экрана, которые могут скрываться и показываться
    private View content;         // Форма с данными
    private View emptyScreen;     // Экран с сообщением об отсутствии данных

    private Button refreshButton; // Кнопка обновления

    // Контроллер, управляющий экраном
    private MainViewController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Для сохранения контроллера после пересоздания экрана
        setRetainInstance(true);

        // Контроллер создаем 1 раз за жизнь экрана
        controller = new MainViewController();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Инициализация разметки и сохранение ссылок на виджеты. Обработка нажатий по кнопке
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        firstNameTextView = (TextView) view.findViewById(R.id.first_name);
        lastNameTextView = (TextView) view.findViewById(R.id.last_name);

        content = view.findViewById(R.id.content);
        emptyScreen = view.findViewById(R.id.empty_screen);

        refreshButton = (Button) view.findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.getInfo();
            }
        });

        // Каждый раз, когда View создана, нужно передать ссылку на нее в контроллер,
        // чтобы он мог ей управлять.
        controller.onTakeView(this, savedInstanceState == null);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Нужно стереть ссылку на экран в контроллере, так как его разметка уничтожена.
        controller.onLossView();
    }

    @Override
    public void showInfo(Info info) {
        // Если пришли пустые данные - показываем соответствуюший экран.
        // Если данные есть - отображаем.
        if(info == null) {
            content.setVisibility(View.GONE);
            emptyScreen.setVisibility(View.VISIBLE);
        } else {
            content.setVisibility(View.VISIBLE);
            emptyScreen.setVisibility(View.GONE);

            firstNameTextView.setText(info.getFirstName());
            lastNameTextView.setText(info.getLastName());
        }
    }

    @Override
    public void hideInfo() {
        content.setVisibility(View.GONE);
        emptyScreen.setVisibility(View.GONE);
    }

    @Override
    public void setRefreshButtonEnabled(boolean enabled) {
        // В процессе загрузки на кнопку нельзя нажимать повторно.
        // Контроллер может вызывать этот метод для управления доступностью кнопки.
        refreshButton.setEnabled(enabled);
    }
}
