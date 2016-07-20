package com.sequenia.mvc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sequenia.mvc.controllers.InfoListController;
import com.sequenia.mvc.views.InfoListView;

/**
 * Created by chybakut2004 on 20.07.16.
 */

public class InfoListFragment extends InfoListFragmentWithoutLogic {

    private InfoListController<InfoListView> controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new InfoListController<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        controller.onTakeView(this, savedInstanceState == null);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.loadData(true, true);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        controller.onLossView();
    }
}
