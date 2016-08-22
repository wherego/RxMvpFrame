package com.yangzheandroid.retrofitutils.utils;

import android.util.Log;

/**
 * 日志工具类
 * FIXME
 */
public final class LogUtil {

//    public static String TAG = LogUtil.class.getSimpleName();

    //日志打印开关
    public static boolean isLogEnable = true;

//    public static void i(String msg) {
//        i(TAG, msg);
//    }
//
//    public static void d(String msg) {
//        d(TAG, msg);
//    }
//
//    public static void w(String msg) {
//        w(TAG, msg);
//    }
//
//    public static void e(String msg) {
//        e(TAG, msg);
//    }
//
//    public static void v(String msg) {
//        v(TAG, msg);
//    }

    /**
     * Debug
     */
    public static void d(String tag, String msg) {
        if (isLogEnable) {
            Log.d(tag, msg);
        }
    }

    /**
     * Information
     */
    public static void i(String tag, String msg) {
        if (isLogEnable) {
            Log.i(tag, msg);
        }
    }

    /**
     * Verbose
     */
    public static void v(String tag, String msg) {
        if (isLogEnable) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isLogEnable) {
            Log.w(tag, msg);
        }
    }


    public static void e(String tag, String msg) {
        if (isLogEnable) {
            Log.e(tag, msg);
        }
    }


}
