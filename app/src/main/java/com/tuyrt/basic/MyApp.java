package com.tuyrt.basic;

import android.app.Application;

import com.tuyrt.tui.TUI;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TUI.init(this);
    }
}
