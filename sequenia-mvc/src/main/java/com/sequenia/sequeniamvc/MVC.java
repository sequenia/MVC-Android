package com.sequenia.sequeniamvc;

/**
 * Главные интерфейсы для MVC<br/>
 * Created by chybakut2004 on 14.07.16.
 */

public interface MVC {

    /**
     * View - экран, умеющий выполнять различные действия
     */
    interface View {

        /**
         * Показывает сообщение
         */
        void showMessage(String message);
    }

    /**
     * Controller - объект, в котором заложена логика экрана<br/>
     * (Выполнение http запросов, бизнес-логика)<br/>
     * <br/>
     * T - Уточнение View, которым оперирует Controller.<br/>
     * Нужно для доступности всех методов View из Controller<br/>
     * <br/>
     * ВНИМАНИЕ: Объект Controller нужно сохранить после пересоздания экрана.<br/>
     * Самый легкий способ - использовать фрагменты с setRetainInstance(true).<br/>
     */
    interface Controller<T extends View> {

        /**
         * Получает View и сохраняет ссылку на нее<br/>
         * @param view view
         * @param firstTime true, если экран создан первы раз<br/>
         *                  false, если экран пересоздан (например, в результате поворота)
         */
        void onTakeView(T view, boolean firstTime);

        /**
         * Стирает ссылку на View
         */
        void onLossView();

        /**
         * @return true, если View доступна для работы (false, если экран закрылся)
         */
        boolean isViewAttached();

        /**
         * @return true, если экран создан первый раз. False, если пересоздан (например при повороте)
         */
        boolean createdFirstTime();

        /**
         * @return view
         */
        T getView();
    }

    /**
     * Model - объект, через который можно получить данные<br/>
     * (Например, из SharedPreferences или через http запросы)
     */
    interface Model {

        // Все методы определяются в зависимости от конкретной Model

        /**
         * Класс ошибки при загрузке данных в модели
         */
        class ModelError {

            private int code;        // Код ошибки
            private String message;  // Сообщение об ошибке

            public ModelError(int code, String message) {
                this.code = code;
                this.message = message;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }

        /**
         * Коллбэк при ошибке во время обращения к модели
         */
        interface ModelErrorListener {
            void onError(ModelError error);
        }
    }

}
