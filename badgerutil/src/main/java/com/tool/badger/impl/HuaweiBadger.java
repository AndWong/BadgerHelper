package com.tool.badger.impl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.tool.badger.base.BaseBadger;

import java.util.Arrays;
import java.util.List;

/**
 * 华为
 * Created by wong on 17-3-16.
 */
public class HuaweiBadger extends BaseBadger {
    @Override
    public void executeBadger(Context context, int badgeCount) {
        String launcherClassName = getLauncherClassName(context);
        if (TextUtils.isEmpty(launcherClassName)) {
            return;
        }
        Bundle localBundle = new Bundle();
        localBundle.putString("package", context.getPackageName());
        localBundle.putString("class", launcherClassName);
        localBundle.putInt("badgenumber", badgeCount);
        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, localBundle);
    }

    @Override
    public void resetBadger(Context context) {
        executeBadger(context, 0);
    }

    @Override
    public List<String> getSupporLaucners() {
        return Arrays.asList(
                "com.huawei.android.launcher"
        );
    }
}
