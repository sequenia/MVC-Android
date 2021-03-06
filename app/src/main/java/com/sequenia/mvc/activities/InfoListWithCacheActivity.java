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

public class InfoListWithCacheActivity extends SampleActivity {

    @Override
    public Fragment createFragment() {
        return new InfoListWithCacheFragment();
    }
}
