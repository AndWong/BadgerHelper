package com.tool.badger.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by wong on 17-3-16.
 */
public abstract class BaseBadger {
    public abstract void executeBadger(Context context, int badgeCount);

    public abstract void resetBadger(Context context);

    public abstract List<String> getSupporLaucners();

    protected String getLauncherClassName(Context context) {
        ComponentName componentName = getComponentName(context);
        if (null == componentName) {
            return "";
        }
        return componentName.getClassName();
    }

    protected String getLauncherPackageName(Context context) {
        ComponentName componentName = getComponentName(context);
        if (null == componentName) {
            return "";
        }
        return componentName.getPackageName();
    }

    protected ComponentName getComponentName(Context context){
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (null == launchIntent) {
            return null;
        }
        return launchIntent.getComponent();
    }
}
