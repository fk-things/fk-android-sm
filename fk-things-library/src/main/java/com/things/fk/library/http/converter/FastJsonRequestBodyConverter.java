package com.things.fk.library.http.converter;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @author tic
 *         created on 18-3-28
 */

public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Type type;

    public FastJsonRequestBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        byte[] json = JSON.toJSONBytes(value);
        Log.e("RequestBodyConverter", JSON.toJSONString(value));
        return RequestBody.create(MEDIA_TYPE, json);
    }
}
