package com.things.fk.library.utils;

import android.os.Environment;
import android.text.TextUtils;

import com.things.fk.library.BuildConfig;

import java.io.File;

/**
 * Created by tic on 18-1-3.
 */

public class Files {

    /**
     * sdcard mounted ?
     * @return
     */
    public static boolean isMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String sdcardDir() {
        if (isMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

}
