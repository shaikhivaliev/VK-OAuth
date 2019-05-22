package com.webim.testshaikhivaliev;

import android.app.Application;

public class AppDelegate extends Application {

    public static AppDelegate instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppDelegate getInstance() {
        return instance;
    }

}

