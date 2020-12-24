package com.tuyrt.basic.utils;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.tuyrt.basic.BuildConfig;

import java.util.Collection;

public class TLog {

    private static boolean OPEN_LOG = BuildConfig.DEBUG;

    public static void isOpenLog(boolean isOpenLog) {
        OPEN_LOG = isOpenLog;
    }

    public static void v(Object... message) {
        if (OPEN_LOG) {
            log(Log.VERBOSE, formatMessage(message));
        }
    }

    public static void d(Object... message) {
        if (OPEN_LOG) {
            log(Log.DEBUG, formatMessage(message));
        }
    }

    public static void i(Object... message) {
        if (OPEN_LOG) {
            log(Log.INFO, formatMessage(message));
        }
    }

    public static void w(Object... message) {
        log(Log.WARN, formatMessage(message));
    }

    public static void e(Object... message) {
        log(Log.ERROR, formatMessage(message));
    }

    public static void json(String json) {
        json = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(new JsonParser().parse(json));
        log(Log.DEBUG, formatMessage(json));
    }

    /**
     * 根据type输出日志消息，包括方法名，方法行数，Message
     *
     * @param type    日志类型，如Log.INFO
     * @param message 日志内容
     */
    private static void log(int type, String message) {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4]; //核心一，获取到执行log方法的代码位置
        String className = stackTrace.getClassName();
        String tag = className.substring(className.lastIndexOf('.') + 1); //不必须的tag
        StringBuilder sb = new StringBuilder();

        sb.append("日志")
                .append("(") //核心二，通过(fileName:lineNumber)格式，即可在点击日志后跳转到执行log方法的代码位置
                .append(stackTrace.getFileName())//文件名
                .append(":")
                .append(stackTrace.getLineNumber())//行号
                .append(")")
                .append("_") //后面的属于不必须的信息
                .append(stackTrace.getMethodName())//方法名
                .append("【")
                .append(message)//消息
                .append("】");

        switch (type) {
            case Log.DEBUG:
                Log.d(tag, sb.toString());
                break;
            case Log.INFO:
                Log.i(tag, sb.toString());
                break;
            case Log.WARN:
                Log.w(tag, sb.toString());
                break;
            case Log.ERROR:
                Log.e(tag, sb.toString());
                break;
            case Log.VERBOSE:
                Log.v(tag, sb.toString());
                break;
        }
    }

    private static String formatMessage(Object... messages) {
        StringBuilder sb = new StringBuilder();
        for (Object mobj : messages) {
            sb.append(objToString(mobj, -1)).append("\n");
        }
        return sb.substring(0, sb.length() - 1).trim();
    }

    //******************************************************************************************

    private static String objToString(Object obj, int currentLvel) {
        if (obj == null) {
            return "null";
        }
        currentLvel++;
        StringBuilder sb = new StringBuilder();
        if (obj instanceof String) {//字符串
            sb.append(getSpace(currentLvel)).append(obj);
        } else if (obj instanceof Object[]) {//数组
            sb.append("数组：").append("\n");
            for (Object mobj : (Object[]) obj) {
                sb.append(objToString(mobj, currentLvel)).append("\n");//递归
            }
        } else if (obj instanceof Collection) {//集合
            sb.append("集合：").append("\n");
            for (Object mobj : (Collection) obj) {
                sb.append(objToString(mobj, currentLvel)).append("\n");//递归
            }
        } else {//其他对象
            sb.append(getSpace(currentLvel)).append(obj.toString());
        }
        return sb.toString();
    }

    /***
     * 格式化目录
     *
     * @param level 目录层次，也即"| _ _"的个数
     */
    private static String getSpace(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("|__");
        }
        return sb.toString();
    }
}

