package com.tool.badger.impl;

import android.content.Context;
import android.content.Intent;

import com.tool.badger.base.BaseBadger;

import java.util.Arrays;
import java.util.List;

/**
 * VIVO
 * Created by wong on 17-3-16.
 */
public class VivoBadger extends BaseBadger{
    @Override
    public void executeBadger(Context context, int badgeCount) {
        Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
        intent.putExtra("packageName", context.getPackageName());
        intent.putExtra("className", getLauncherClassName(context));
        intent.putExtra("notificationNum", badgeCount);
        context.sendBroadcast(intent);
    }

    @Override
    public void resetBadger(Context context) {
        executeBadger(context,0);
    }

    @Override
    public List<String> getSupporLaucners() {
        return Arrays.asList("com.vivo.launcher");
    }
}
