package com.tool.badger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    BadgerUtil.createBadger(MainActivity.this,1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },2000);

    }
}
