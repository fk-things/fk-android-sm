package com.things.fk.sm.core.data.source.remote;

import com.things.fk.library.http.Retrofits;
import com.things.fk.sm.core.data.service.LoginService;
import com.things.fk.sm.http.Api;
import com.things.fk.sm.core.data.User;

/**
 * @author tic
 *         created on 18-3-28
 */

public class UserRemoteTask {

    private final Retrofits<LoginService> mRetrofit;

    public UserRemoteTask() {
        mRetrofit = new Retrofits<>();
        mRetrofit.init(Api.BASE_URL_LOCALHOST);
    }

    public void login(User user) {
        LoginService service = mRetrofit.createService(LoginService.class);
        service.login(user);
    }

}
