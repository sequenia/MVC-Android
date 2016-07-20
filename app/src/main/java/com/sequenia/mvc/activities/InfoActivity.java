package com.sequenia.mvc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sequenia.mvc.R;
import com.sequenia.mvc.fragments.InfoFragment;

/**
 * Экран с какой-то информацией
 *
 * Внешний вид и логика находится во фрагменте, в котором выставлен setRetainInstance(true)
 *
 * Created by chybakut2004 on 15.07.16.
 */

public class InfoActivity extends SampleActivity {

    @Override
    public Fragment createFragment() {
        return new InfoFragment();
    }
}
