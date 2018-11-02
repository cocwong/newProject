package com.example.cocwong.test.util;

import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.example.cocwong.test.base.App;

public class PermissionHepler {
    public static boolean hasPermission(String permission) {
        int selfPermission = ContextCompat.checkSelfPermission(App.getInstance(), permission);
        return selfPermission == PackageManager.PERMISSION_GRANTED;
    }
}
