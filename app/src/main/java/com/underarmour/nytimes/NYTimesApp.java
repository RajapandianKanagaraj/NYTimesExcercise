package com.underarmour.nytimes;

import android.app.Application;

public class NYTimesApp extends Application {

    private static NYTimesApp appInstance;

    public static NYTimesApp getInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }
}
