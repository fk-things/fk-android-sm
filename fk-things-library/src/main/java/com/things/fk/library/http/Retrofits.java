package com.things.fk.library.http;

import com.things.fk.library.http.converter.FastJsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author tic
 *         created on 18-3-28
 */

public class Retrofits<T> {

    private Retrofit retrofit;
    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    public void init(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }

    public T createService(Class<T> service) {
        return retrofit.create(service);
    }

}
