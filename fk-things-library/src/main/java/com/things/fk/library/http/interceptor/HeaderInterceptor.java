package com.things.fk.library.http.interceptor;

import android.support.v4.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tic
 *         created on 18/4/1.
 */
public class HeaderInterceptor implements Interceptor {
    private List<Pair<String, String>> mHeaders;

    public HeaderInterceptor addHeader(String name, String value) {
        mHeaders.add(new Pair<>(name, value));
        return this;
    }

    public HeaderInterceptor addHeader(List<Pair<String, String>> headers) {
        mHeaders.addAll(headers);
        return this;
    }

    public HeaderInterceptor setHeader(List<Pair<String, String>> headers) {
        mHeaders.clear();
        mHeaders = headers;
        return this;
    }

    private HeaderInterceptor() {
        mHeaders = new ArrayList<>();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();

        if (mHeaders != null) {
            for (Pair<String, String> header : mHeaders) {
                builder.addHeader(header.first, header.second);
            }
        }

        return chain.proceed(builder.build());
    }
}
