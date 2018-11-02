package com.example.cocwong.test.base;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.cocwong.test.glide.GlideApp;
import com.example.cocwong.test.util.CrashHandler;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
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
        CrashHandler.getInstance().init();
        BlockCanary.install(this, new BlockCanaryContext() {
            private static final String TAG = "AppContext";

            @Override
            public String provideQualifier() {
                String qualifier = "";
                try {
                    PackageInfo info = App.this.getPackageManager()
                            .getPackageInfo(App.this.getPackageName(), 0);
                    qualifier += info.versionCode + "_" + info.versionName + "_YYB";
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(TAG, "provideQualifier exception", e);
                }
                return qualifier;
            }

            @Override
            public int provideBlockThreshold() {
                return 500;
            }

            @Override
            public boolean displayNotification() {
                return true;
            }

            @Override
            public boolean stopWhenDebugging() {
                return false;
            }
        }).start();
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GlideApp.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level > TRIM_MEMORY_MODERATE) {
            GlideApp.get(this).clearMemory();
        }
    }
}
