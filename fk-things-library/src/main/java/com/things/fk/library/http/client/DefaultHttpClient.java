package com.things.fk.library.http.client;

import android.text.TextUtils;

import com.google.common.base.Preconditions;
import com.things.fk.library.BuildConfig;
import com.things.fk.library.http.converter.FastJsonConverterFactory;
import com.things.fk.library.utils.Files;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;

/**
 * Default Http client for Retrofit or else
 *
 * @author tic
 */
public class DefaultHttpClient implements RetrofitsClient {

    private String baseUrl;
    private OkHttpClient okHttpClient;

    public DefaultHttpClient(String url) {
        baseUrl = Preconditions.checkNotNull(url, "the base url cannot be null");
        okHttpClient = init();
    }

    /**
     * init OkHttpClient
     *
     * @return OkHttpClient
     */
    private OkHttpClient init() {
        OkHttpClient.Builder okHttp = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            okHttp.addNetworkInterceptor(loggingInterceptor());
        }
        // timeout
        okHttp.connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        // cache control
        // okHttp.cache(createCache(cacheDir()));

        return okHttp.build();
    }

    /**
     * cache dir for overriding
     *
     * @return
     */
    protected String cacheDir() {
        String sdcard = Files.sdcardDir();
        if (!TextUtils.isEmpty(sdcard)) {
            return sdcard + File.separator + BuildConfig.APPLICATION_ID;
        }
        return null;
    }

    public Cache createCache(String dir) {
        File cacheFile = new File(dir, "cache");
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(cacheFile, cacheSize);
    }

    /**
     * cache
     *
     * @param maxAge
     * @param timeUnit
     * @return
     */
    public CacheControl cacheStrategy(int maxAge, TimeUnit timeUnit) {
        return new CacheControl.Builder()
                .maxAge(maxAge, timeUnit)
                .build();
    }

    /**
     * log interceptor
     *
     * @return
     */
    protected HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Override
    public String baseUrl() {
        return baseUrl;
    }

    @Override
    public OkHttpClient client() {
        return okHttpClient;
    }

    @Override
    public Converter.Factory dataConverter() {
        return FastJsonConverterFactory.create();
    }
}
