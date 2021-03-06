package com.tool.badger.impl;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.tool.badger.base.BaseBadger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wong on 17-3-16.
 */
public class NovaBadger extends BaseBadger {
    @Override
    public void executeBadger(Context context, int badgeCount) {
        String launcherClassName = getLauncherClassName(context);
        if (TextUtils.isEmpty(launcherClassName)) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", context.getPackageName() + "/" + launcherClassName);
        contentValues.put("count", badgeCount);
        context.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), contentValues);
    }

    @Override
    public void resetBadger(Context context) {
        executeBadger(context, 0);
    }

    @Override
    public List<String> getSupporLaucners() {
        return Arrays.asList("com.teslacoilsw.launcher");
    }
}
