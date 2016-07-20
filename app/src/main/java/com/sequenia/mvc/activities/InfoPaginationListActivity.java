package com.sequenia.mvc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sequenia.mvc.R;
import com.sequenia.mvc.fragments.InfoFragment;
import com.sequenia.mvc.fragments.InfoPaginationListFragment;

/**
 * Created by chybakut2004 on 20.07.16.
 */

public class InfoPaginationListActivity extends SampleActivity {

    @Override
    public Fragment createFragment() {
        return new InfoPaginationListFragment();
    }
}
