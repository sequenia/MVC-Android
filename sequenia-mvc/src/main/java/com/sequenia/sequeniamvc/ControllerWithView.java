package com.sequenia.sequeniamvc;

import java.lang.ref.WeakReference;

/**
 * Реализация Controller, в котором сохраняется ссылка на View
 *
 * Created by chybakut2004 on 14.07.16.
 */

public class ControllerWithView<T extends MVC.View> implements MVC.Controller<T> {

    /**
     * Ссылка на view слабая, на случай, чтобы при закрытии экрана не было утечки памяти.
     * (При закрытии экрана нужно вызвать метод onLossView, но вдруг этого не произойдет).
     */
    private WeakReference<T> view;
    private boolean firstTime;

    @Override
    public void onTakeView(T view, boolean firstTime) {
        this.view = new WeakReference<T>(view);
        this.firstTime = firstTime;
    }

    @Override
    public void onLossView() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    @Override
    public boolean createdFirstTime() {
        return firstTime;
    }

    @Override
    public T getView() {
        return view == null ? null : view.get();
    }
}
