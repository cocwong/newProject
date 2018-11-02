package com.example.cocwong.test.util;

import android.content.Intent;
import android.os.Process;

import com.example.cocwong.test.base.App;
import com.example.cocwong.test.base.Constant;

public class AppHelper {
    public static void shutDown() {
        App.getInstance().sendBroadcast(new Intent(Constant.ACTION_ACTIVITY_FINISH));
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static int getScreenWidth() {
        return App.getInstance().getResources().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight(){
        return App.getInstance().getResources().getDisplayMetrics().heightPixels;
    }
}
