package com.things.fk.sm;

import android.app.Application;

import com.things.fk.library.database.Realms;

/**
 * @author tic
 *         created on 18-3-29
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realms.init(this);
    }
}
