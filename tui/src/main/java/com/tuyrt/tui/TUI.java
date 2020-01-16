package com.tuyrt.tui;

import android.app.Application;
import android.content.Context;

import com.tuyrt.tui.config.TUIConfig;
import com.tuyrt.tui.logs.UILog;

public class TUI {

    private static volatile TUI singleton;

    private TUI() {
    }

    public static TUI getInstance() {
        if (singleton == null) {
            synchronized (TUI.class) {
                if (singleton == null) {
                    singleton = new TUI();
                }
            }
        }
        return singleton;
    }

    private static Application sContext;
    private static TUIConfig sTuiConfig;


    public static void init(Application application) {
        sContext = application;
    }


    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 XUI.init() 初始化！");
        }
    }
    //=======================配置===========================//

    public static TUIConfig getConfig() {
        if (sTuiConfig == null) {
            sTuiConfig  = new TUIConfig.Builder()
                    .isDebug(true)
                    .logTag("tuyrt7")
                    .build();
        }
        return sTuiConfig;
    }

    /**
     配置一些基础参数
     */
    public static void setDefaultConfig() {
        TUIConfig sTuiConfig = new TUIConfig.Builder()
                .isDebug(true)
                .logTag("tuyrt7")
                .build();

        setTUIConfig(sTuiConfig);
    }


    /**
     配置一些基础参数
     */
    public static void setTUIConfig(TUIConfig tuiConfig) {
        sTuiConfig = tuiConfig;
        //==========日志调试============//
        initLog();
    }

    private static void initLog() {
        UILog.setDebug(getConfig().isDebug());
        if (getConfig().getLogTag() != null) {
            UILog.setTag(getConfig().getLogTag());
        }
    }


}