package com.things.fk.library.http.client;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Converter;

/**
 * RetrofitsClient for Retrofits
 * provider base data for Retrofit http client
 *
 * @author tic
 *         created on 18/4/1.
 */

public interface RetrofitsClient {

    /**
     * provider base url for http
     *
     * @return url
     */
    String baseUrl();

    /**
     * provide the okhttpclient
     *
     * @return okhttpclient
     */
    OkHttpClient client();

    /**
     * data converter factory
     *
     * @return Converter.Factory
     */
    Converter.Factory dataConverter();

    /**
     * retry time when connection failed
     *
     * @return times
     */
    int retryTimes();

    /**
     * cache strategy. disable when null
     *
     * @return Cache
     */
    Cache cacheStrategy();
}
