package com.things.fk.library.http.converter;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author tic
 *         created on 18-3-28
 */

public class FastJsonConverterFactory extends Converter.Factory {

    static final String TAG = "fashJson";

    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    /**
     * 需要重写父类中responseBodyConverter，该方法用来转换服务器返回数据
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Log.e(TAG, "responseBodyConverter:" + type.getClass().getSimpleName());
        return new FastJsonResponseBodyConverter<>(type);
    }

    /**
     * 需要重写父类中responseBodyConverter，该方法用来转换发送给服务器的数据
     */
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        Log.e(TAG, "requestBodyConverter:" + type.getClass().getSimpleName());
        return new FastJsonRequestBodyConverter<>(type);
    }

}
