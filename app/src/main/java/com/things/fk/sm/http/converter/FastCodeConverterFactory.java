package com.things.fk.sm.http.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 加密 ConverterFactory
 *
 * @author tic
 *         created on 18-4-10
 */

public class FastCodeConverterFactory extends Converter.Factory {
    private int contentType;

    public FastCodeConverterFactory(int contentType) {
        this.contentType = contentType;
    }

    public static FastCodeConverterFactory create(int contentType) {
        return new FastCodeConverterFactory(contentType);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastCodeRequestBodyConverter<>(type, contentType);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastCodeResponseBodyConverter<>(type, contentType);
    }
}
