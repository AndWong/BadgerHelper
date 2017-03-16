package com.tool.badger.base;

import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * Created by wong on 17-3-16.
 */
public abstract class BaseBadger {
    public abstract void executeBadger(Context context, int badgeCount);

    public abstract void resetBadger(Context context);

    public abstract List<String> getSupporLaucners();

    protected String getLauncherClassName(Context context) {
        //方式一
        /*PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (info == null) {
            info = packageManager.resolveActivity(intent, 0);
        }
        return info.activityInfo.name;*/

        //方式二
        ComponentName componentName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent();
        return componentName.getClassName();
    }
}
