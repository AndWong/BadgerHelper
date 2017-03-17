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
                int badgeCount = 1;
//                ShortcutBadger.applyCount(MainActivity.this, badgeCount); //for 1.1.4+

                try {
                    BadgerUtil.createBadger(MainActivity.this,badgeCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },2000);

    }
}
