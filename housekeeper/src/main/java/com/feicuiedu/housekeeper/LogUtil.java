package com.feicuiedu.housekeeper;

import android.util.Log;

/**
 * Created by 张超 on 2016/11/4.
 */

public class LogUtil {

    public static boolean isOpenDebug = true;
    public static boolean isOpenWarn = true;

    public static void d(String tag, String msg) {
        if (isOpenDebug) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isOpenWarn) {
            Log.w(tag, msg);
        }
    }
}
