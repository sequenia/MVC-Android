package com.sequenia.mvc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Главный экран
 */
public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if(fragment == null) {
            fragment = new MainActivityFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, fragment, FRAGMENT_TAG)
                    .commit();
        }
    }
}
