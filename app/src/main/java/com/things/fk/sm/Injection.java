package com.things.fk.sm;

import com.things.fk.library.http.DefaultHttpClient;
import com.things.fk.library.http.RetrofitsClient;
import com.things.fk.sm.http.Api;

/**
 * @author tic
 *         created on 18/4/1.
 */

public class Injection {

    public static RetrofitsClient provideRetrofitsClient() {
        return new DefaultHttpClient(Api.BASE_URL_LOCALHOST);
    }

}
