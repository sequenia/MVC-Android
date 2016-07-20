package com.sequenia.mvc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sequenia.mvc.controllers.InfoListController;
import com.sequenia.mvc.controllers.InfoListWithCacheController;
import com.sequenia.mvc.objects.Info;
import com.sequenia.mvc.views.InfoListView;
import com.sequenia.sequeniamvc.DataController;

import java.util.List;

/**
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListWithCacheFragment extends InfoListFragmentWithoutLogic {

    private InfoListWithCacheController<InfoListView> controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new InfoListWithCacheController<>();
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
