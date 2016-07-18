package com.sequenia.mvc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sequenia.mvc.R;
import com.sequenia.mvc.fragments.InfoListWithCacheFragment;

/**
 * Экран со списком, который закеширован в памяти устройства
 *
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListWithCacheActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "Fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_with_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if(fragment == null) {
            fragment = new InfoListWithCacheFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, fragment, FRAGMENT_TAG)
                    .commit();
        }
    }
}
