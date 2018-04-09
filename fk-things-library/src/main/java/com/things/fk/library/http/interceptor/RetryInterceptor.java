package com.things.fk.library.http.interceptor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.things.fk.library.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 重连拦截
 *
 * @author tic
 *         created on 18-4-9
 */

public class RetryInterceptor implements Interceptor {

    private int maxRetry;
    private int retryNum = 0;

    public RetryInterceptor(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && retryNum < maxRetry) {
            retryNum++;
            response = chain.proceed(request);

            if (BuildConfig.DEBUG) {
                Log.e("RetryInterceptor", "异常重连" + retryNum);
            }
        }
        return response;
    }
}
