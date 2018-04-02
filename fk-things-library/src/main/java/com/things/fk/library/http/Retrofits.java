package com.things.fk.library.http;

import com.google.common.base.Preconditions;
import com.things.fk.library.http.client.RetrofitsClient;
import com.things.fk.library.http.data.HttpResponse;

import java.io.IOException;
import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author tic
 *         created on 18-3-28
 */

public class Retrofits {

    private static final String TAG = Retrofits.class.getSimpleName();
    private Retrofit retrofit;

    private static Retrofits INSTANCE;
    private static RetrofitsClient httpProvider;

    private Retrofits(Retrofit.Builder builder) {
        retrofit = builder.build();
    }

    public static void init(RetrofitsClient provider) {
        httpProvider = provider;
    }

    /**
     * single instance for normal scenes
     *
     * @return Retrofits
     */
    public static Retrofits getInstance() {
        if (INSTANCE == null) {
            synchronized (TAG) {
                if (INSTANCE == null) {
                    Preconditions.checkNotNull(httpProvider,
                            "please ensure call init before use this");

                    INSTANCE = new Retrofits(createBuilder(httpProvider));
                }
            }
        }
        return INSTANCE;
    }

    /**
     * new instance for custom scenes
     *
     * @param provider
     * @return
     */
    public static Retrofits newInstance(RetrofitsClient provider) {
        return new Retrofits(createBuilder(provider));
    }

    private static Retrofit.Builder createBuilder(RetrofitsClient provider) {
        Preconditions.checkNotNull(provider.client(), "provider should provide a OkHttpClient");
        Preconditions.checkNotNull(provider.baseUrl(), "provider should provide a base url");
        Preconditions.checkNotNull(provider.dataConverter(), "provider should provide a data converter");

        return new Retrofit.Builder()
                .baseUrl(provider.baseUrl())
                .client(provider.client())
                .addConverterFactory(provider.dataConverter())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    /**
     * create the API service
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T createService(final Class<T> service) {
        Preconditions.checkNotNull(retrofit, "Retrofits init failed");
        return retrofit.create(service);
    }

    /**
     * execute exactly request call
     *
     * @param caller
     */
    public HttpResponse execute(Call<?> caller) {
        HttpResponse httpResp = null;
        try {
            Response<?> resp = caller.execute();
            if (resp.isSuccessful()) {
                httpResp = RespUtils.success(0, resp);
            } else {
                switch (resp.code()) {
                    case 503:
                        // cache失败,retry

                        break;
                    default:
                        httpResp = RespUtils.failed(resp.code(), resp.errorBody());
                        break;
                }
            }
        } catch (ConnectException e) {
            httpResp = RespUtils.failed(404,
                    "Failed to connect to " + caller.request().url());
            e.printStackTrace();
        } catch (IOException e) {
            httpResp = RespUtils.failed(404, e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if (httpResp == null) {
                httpResp = RespUtils.failed(404, "unknown exception");
            }
        }
        return httpResp;
    }
}
