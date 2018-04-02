package com.things.fk.sm.core.data.source.remote;

import com.things.fk.library.http.RespUtils;
import com.things.fk.library.http.Retrofits;
import com.things.fk.library.http.data.HttpResponse;
import com.things.fk.sm.core.data.parser.UserParser;
import com.things.fk.sm.core.data.service.UserService;

import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * @author tic
 *         created on 18-3-28
 */

public class UserRemoteTask {

    private final UserService service;
    private final Retrofits mRetrofit;

    public UserRemoteTask() {
        mRetrofit = Retrofits.getInstance();
        service = mRetrofit.createService(UserService.class);
    }

    public Flowable<Map<String, Object>> login(String userName, String password) {
        return Flowable.create(e -> {
            HttpResponse result = mRetrofit.execute(service.login(userName, password));
            if (RespUtils.isSuccess(result)) {
                Map<String, Object> data = new UserParser().parse(result.data());
                e.onNext(data);
                e.onComplete();
            } else {
                e.onError(new Throwable(result.error()));
            }
        }, BackpressureStrategy.DROP);
    }

}
