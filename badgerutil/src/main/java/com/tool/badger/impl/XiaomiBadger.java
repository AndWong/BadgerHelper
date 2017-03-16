package com.tool.badger.impl;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.tool.badger.base.BaseBadger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 小米机型
 * Created by wong on 17-3-16.
 */
public class XiaomiBadger extends BaseBadger {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void executeBadger(Context context, int badgeCount) {
        //小米系统本身只支持角标的消失
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle("title")
                .setContentText("text");
        //.setSmallIcon(R.drawable.icon);
        Notification notification = builder.build();
        try {
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, badgeCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNotificationManager.notify(0, notification);
    }

    @Override
    public void resetBadger(Context context) {
        executeBadger(context, 0);
    }

    @Override
    public List<String> getSupporLaucners() {
        return Arrays.asList(
                "com.miui.miuilite",
                "com.miui.home",
                "com.miui.miuihome",
                "com.miui.miuihome2",
                "com.miui.mihome",
                "com.miui.mihome2",
                "com.i.miui.launcher"
        );
    }
}
