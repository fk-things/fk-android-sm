package com.things.fk.sm;

import com.things.fk.library.BaseApplication;
import com.things.fk.library.database.Realms;
import com.things.fk.library.http.Retrofits;

/**
 * @author tic
 *         created on 18-3-29
 */

public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofits.init(Injection.provideRetrofitsClient());
        Realms.init(this);
    }
}
