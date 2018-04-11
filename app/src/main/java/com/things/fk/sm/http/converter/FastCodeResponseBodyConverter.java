package com.things.fk.sm.http.converter;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * @author tic
 *         created on 18-4-10
 */

class FastCodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Type type;

    public FastCodeResponseBodyConverter(Type type, int contentType) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        Log.e("ResponseBodyConverter", tempStr);
        return JSON.parseObject(tempStr, type);
    }
}
