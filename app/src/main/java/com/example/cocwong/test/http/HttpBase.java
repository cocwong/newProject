package com.example.cocwong.test.http;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.example.cocwong.test.base.App;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpBase {
    protected Interceptor getHeaders() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
//                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("User-Agent", generateUserAgent())
//                        .addHeader("User-Agent",WebSettings.getDefaultUserAgent(App.getInstance()))
                        .addHeader("appVersion", getAppVersion())
                        .addHeader("channel", getChannel())
                        .addHeader("device", getDevice())
                        .addHeader("deviceId", getDeviceId())
                        .addHeader("id", "171631")
                        .addHeader("nonce", "97004")
                        .addHeader("platform", "ANDROID")
                        .addHeader("osVersion", getOsVersion())
                        .addHeader("signature", "d71e7db7f864605053b5602d50adfd49")
                        .addHeader("signId", "dMn/PwcvrB4=")
                        .addHeader("timestamp", " 1540618108497")
                        .addHeader("version", "1.2").build();
                return chain.proceed(request);
            }
        };
    }

    private static String generateUserAgent() {
        return "Dalvik/2.1.0 (Linux; U; Android 5.1; MX5 Build/LMY47I)";
    }

    private static String getAppVersion() {
        try {
            return App.getInstance().getPackageManager().getPackageInfo(App.getInstance().getPackageName(), PackageManager.GET_CONFIGURATIONS).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    private static String getChannel() {
        return Build.MANUFACTURER.toLowerCase();
    }

    private static String getDevice() {
        return Build.BRAND +"_"+ Build.MODEL;
    }

    private static String getDeviceId() {
        TelephonyManager systemService = (TelephonyManager) App.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(App.getInstance(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return systemService.getDeviceId();
        }
        return "";
    }

    private static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    private String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
