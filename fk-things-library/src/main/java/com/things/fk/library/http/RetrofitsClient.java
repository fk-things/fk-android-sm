package com.things.fk.library.http;

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
     *
     * @return
     */
    String baseUrl();

    OkHttpClient client();

    Converter.Factory dataConverter();

}
