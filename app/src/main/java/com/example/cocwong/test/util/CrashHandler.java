package com.example.cocwong.test.util;

import android.Manifest;
import android.app.Application;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.cocwong.test.activity.MainActivity;
import com.example.cocwong.test.base.App;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;

    public static CrashHandler getInstance() {
        if (instance == null) {
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    private CrashHandler() {

    }

    public void init() {
        if (PermissionHepler.hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        StringBuilder logBuilder = new StringBuilder();
        String dateTime = Calendar.getInstance().getTime().toString().trim().replace(" ", "_");
        File file = new File(FileHelper.getHomePath() + File.separator + "log", dateTime + ".txt");
        int sdkInt = Build.VERSION.SDK_INT;
        String brand = Build.BRAND;
        String model = Build.MODEL;
        logBuilder.append("[CRASH_TIME]--->\n\t\t")
                .append(dateTime)
                .append("\n[SDK_VERSION]--->\n\t\t")
                .append(sdkInt)
                .append("\n[DEVICE_MODEL]--->\n\t\t")
                .append(brand)
                .append("_")
                .append(model);
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        String errorInfo = writer.toString();
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.close();
        if (!TextUtils.isEmpty(errorInfo)) {
            logBuilder.append("\n[ERROR_INFO]--->\n\t\t")
                    .append(errorInfo);
            FileHelper.strToFile(logBuilder.toString(), file.getAbsolutePath());
        }
        AppHelper.shutDown();
    }
}
