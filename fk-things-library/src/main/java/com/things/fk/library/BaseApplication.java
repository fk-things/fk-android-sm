package com.things.fk.library;

import android.app.Application;

import com.things.fk.library.utils.Utilities;

/**
 * @author tic
 *         created on 18/4/1.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utilities.getContext();
    }

}
