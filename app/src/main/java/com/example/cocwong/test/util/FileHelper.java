package com.example.cocwong.test.util;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class FileHelper {
    public static void strToFile(final String str, String fileName) {
        final File dstFile = new File(fileName);
        if (!dstFile.getParentFile().exists()) {
            if (!dstFile.getParentFile().mkdirs()) return;
        }
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(dstFile));
            br.write(str);
            br.flush();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHomePath() {
        return Environment.getExternalStorageDirectory()
                + File.separator
                + "GooseTalk"
                + File.separator;
    }
}
