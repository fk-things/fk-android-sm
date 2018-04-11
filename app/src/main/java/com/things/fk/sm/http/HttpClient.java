package com.things.fk.sm.http;

import com.things.fk.library.http.client.DefaultHttpClient;
import com.things.fk.sm.http.converter.FastCodeConverterFactory;

import retrofit2.Converter;

/**
 * @author tic
 *         created on 18/4/1.
 */

public class HttpClient extends DefaultHttpClient {

    private int contentType;

    public HttpClient(String url, int contentType) {
        super(url);
        this.contentType = contentType;
    }

    @Override
    public Converter.Factory dataConverter() {
        return FastCodeConverterFactory.create(contentType);
    }
}
