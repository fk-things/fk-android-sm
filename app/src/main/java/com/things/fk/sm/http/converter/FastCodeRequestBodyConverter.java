package com.things.fk.sm.http.converter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.things.fk.library.http.data.ContentType;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @author tic
 *         created on 18-4-10
 */

class FastCodeRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private final Type type;
    private final int contentType;

    public FastCodeRequestBodyConverter(Type type, int contentType) {
        this.type = type;
        this.contentType = contentType;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Log.i("Converte", "request中传递的json数据：" + value.getClass().getSimpleName() + "-----" + value.toString());
        String mediaType = null;
        String content = null;
        byte[] data;
        switch (contentType) {
            case ContentType.JSON:
                mediaType = ContentType.APPLICATION_JSON;
                content = JSON.toJSONString(value, true);
                break;
            case ContentType.CRYPTO_JSON:
                mediaType = ContentType.APPLICATON_CRYPTO_JSON;
                // CryptUtil.encrypt(CryptUtil.deflate(value.getBytes(ENCODE)), mCryptKey.getBytes());
                break;
            case ContentType.X_CRYPTO_JSON:
                mediaType = ContentType.APPLICATION_X_CRYPTO_JSON;
                break;
            case ContentType.RSA_JSON:
                mediaType = ContentType.APPLICATION_RSA_JSON;
                break;
            default:
                mediaType = ContentType.APPLICATION_JSON;
                break;
        }
        MediaType mt = MediaType.parse(mediaType);
        Log.i("Converte", "转化后的数据：" + content);
        return RequestBody.create(mt, content);
    }
}
