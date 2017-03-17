package com.tool.badger;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

import com.tool.badger.base.BaseBadger;
import com.tool.badger.impl.HuaweiBadger;
import com.tool.badger.impl.LGBadger;
import com.tool.badger.impl.OPPOBadger;
import com.tool.badger.impl.SamsungBadger;
import com.tool.badger.impl.VivoBadger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wong on 17-3-16.
 */
public class BadgerUtil {
    private static final List<Class<? extends BaseBadger>> sBADGERList = new LinkedList<>();

    static {
        sBADGERList.add(HuaweiBadger.class);
        sBADGERList.add(VivoBadger.class);
        sBADGERList.add(OPPOBadger.class);
        sBADGERList.add(SamsungBadger.class);
        sBADGERList.add(LGBadger.class);
    }

    private static BaseBadger sBadger;

    public static void createBadger(Context context, int count) throws Exception {
        if (null == sBadger) {
            initBadger(context);
        }
        if (null != sBadger) {
            sBadger.executeBadger(context, count);
        }
    }

    public static void removeBadger(Context context) throws Exception {
        if (null == sBadger) {
            initBadger(context);
        }
        if (null != sBadger) {
            sBadger.resetBadger(context);
        }
    }

    private static void initBadger(Context context) {
        for (Class<? extends BaseBadger> badger : sBADGERList) {
            BaseBadger baseBadger = null;
            try {
                baseBadger = badger.newInstance();
            } catch (Exception e) {
            }
            if (baseBadger != null && baseBadger.getSupporLaucners().contains(getHomePackageName(context))) {
                sBadger = baseBadger;
                break;
            }
        }
        if (null == sBadger) {
            if (Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) {
                //sBadger = new XiaomiBadger();
            } else if (Build.MANUFACTURER.equalsIgnoreCase("sony")) {
                //sBadger = new SonyBadger();
            } else if (Build.MANUFACTURER.toLowerCase().contains("htc")) {
                //sBadger = new HTCBadger();
            } else if (Build.MANUFACTURER.toLowerCase().contains("nova")) {
                //sBadger = new NovaBadger();
            } else if (Build.MANUFACTURER.toLowerCase().contains("samsung")) {
                sBadger = new SamsungBadger();
            } else if (Build.MANUFACTURER.toLowerCase().contains("lg")) {
                sBadger = new LGBadger();
            } else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
                sBadger = new OPPOBadger();
            } else if (Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
                sBadger = new VivoBadger();
            } else if (Build.MANUFACTURER.equalsIgnoreCase("huawei")) {
                sBadger = new HuaweiBadger();
            }
        }
    }

    private static String getHomePackageName(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo == null || null == resolveInfo.activityInfo
                || resolveInfo.activityInfo.name.toLowerCase().contains("resolver")) {
            return "";
        }
        return resolveInfo.activityInfo.packageName;
    }
}
