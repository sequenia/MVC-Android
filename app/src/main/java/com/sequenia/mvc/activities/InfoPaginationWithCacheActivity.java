package com.sequenia.mvc.activities;

import android.support.v4.app.Fragment;

import com.sequenia.mvc.fragments.InfoPaginationListFragment;
import com.sequenia.mvc.fragments.InfoPaginationWithCacheFragment;

/**
 * Created by chybakut2004 on 21.07.16.
 */

public class InfoPaginationWithCacheActivity extends SampleActivity {

    @Override
    public Fragment createFragment() {
        return new InfoPaginationWithCacheFragment();
    }
}