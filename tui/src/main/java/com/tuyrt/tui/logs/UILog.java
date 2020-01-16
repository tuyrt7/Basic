package com.tuyrt.tui.logs;

import android.util.Log;

public class UILog {
    /**
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     */
    private static boolean isDebug = true;
    private static String TAG = "aaaa";

    private UILog() {
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    //**********************************************下面四个是默认tag的函数  ********************************************
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    //******************************************下面是传入自定义tag的函数  ************************************************
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }
}
