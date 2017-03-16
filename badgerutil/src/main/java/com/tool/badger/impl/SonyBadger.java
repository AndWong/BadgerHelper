package com.tool.badger.impl;

import android.content.Context;
import android.content.Intent;

import com.tool.badger.base.BaseBadger;

import java.util.Arrays;
import java.util.List;

/**
 * 索尼
 * Created by wong on 17-3-16.
 */
public class SonyBadger extends BaseBadger{
    @Override
    public void executeBadger(Context context, int badgeCount) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        boolean isShow = true;
        if (badgeCount == 0) {
            isShow = false;
        }
        Intent localIntent = new Intent();
        localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);//是否显示
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", launcherClassName);//启动页
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(badgeCount));//数字
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());//包名
        context.sendBroadcast(localIntent);
    }

    @Override
    public void resetBadger(Context context) {
        executeBadger(context,0);
    }

    @Override
    public List<String> getSupporLaucners() {
        return Arrays.asList("com.sonyericsson.home", "com.sonymobile.home");
    }
}
