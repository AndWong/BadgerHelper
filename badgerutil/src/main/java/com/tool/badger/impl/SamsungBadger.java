package com.tool.badger.impl;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.tool.badger.base.BaseBadger;

import java.util.Arrays;
import java.util.List;

/**
 * 三星机型
 * Created by wong on 17-3-16.
 */
public class SamsungBadger extends BaseBadger {
    @Override
    public void executeBadger(Context context, int badgeCount) {
        //获取当前的应用
        String launcherClassName = getLauncherClassName(context);
        if (TextUtils.isEmpty(launcherClassName)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", badgeCount);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    @Override
    public void resetBadger(Context context) {
        executeBadger(context,0);
    }

    @Override
    public List<String> getSupporLaucners() {
        return Arrays.asList(
                "com.sec.android.app.launcher",
                "com.sec.android.app.twlauncher"
        );
    }
}
