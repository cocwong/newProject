package com.example.cocwong.test.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    public static App getInstance() {
        return instance;
    }
}
