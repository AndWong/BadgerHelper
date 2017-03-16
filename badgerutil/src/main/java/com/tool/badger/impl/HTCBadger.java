package com.tool.badger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.tool.badger.base.BaseBadger;

import java.util.Arrays;
import java.util.List;

/**
 * HTC
 * Created by wong on 17-3-16.
 */
public class HTCBadger extends BaseBadger {
    @Override
    public void executeBadger(Context context, int badgeCount) {
        ComponentName componentName = getComponentName(context);
        if (null == componentName) {
            return;
        }
        Intent intentNotification = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        intentNotification.putExtra("com.htc.launcher.extra.COMPONENT", componentName.flattenToShortString());
        intentNotification.putExtra("com.htc.launcher.extra.COUNT", badgeCount);
        context.sendBroadcast(intentNotification);

        Intent intentShortcut = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        intentShortcut.putExtra("packagename", context.getPackageName());
        intentShortcut.putExtra("count", badgeCount);
        context.sendBroadcast(intentShortcut);
    }

    @Override
    public void resetBadger(Context context) {
        executeBadger(context, 0);
    }

    @Override
    public List<String> getSupporLaucners() {
        return Arrays.asList("com.htc.launcher");
    }
}
