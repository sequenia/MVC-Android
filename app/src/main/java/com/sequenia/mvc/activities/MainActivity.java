package com.sequenia.mvc.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sequenia.mvc.R;

/**
 * Главный экран
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button info = (Button) findViewById(R.id.info);
        if(info != null) {
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, InfoActivity.class));
                }
            });
        }

        Button infoList = (Button) findViewById(R.id.info_list);
        if(infoList != null) {
            infoList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, InfoListActivity.class));
                }
            });
        }

    }
}
