package com.things.fk.library.utils;

import android.os.Environment;

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

}
