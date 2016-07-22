package com.sequenia.mvc.activities;

import android.support.v4.app.Fragment;

import com.sequenia.mvc.fragments.InfoListFragment;
import com.sequenia.mvc.fragments.InfoListFragmentWithoutLogic;

/**
 * Экран со списоком.
 *
 * Все зашито во фрагмент, смотрите его.
 *
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoListActivity extends SampleActivity {

    @Override
    public Fragment createFragment() {
        return new InfoListFragment();
    }
}
