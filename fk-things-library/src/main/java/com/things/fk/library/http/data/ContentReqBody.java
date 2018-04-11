package com.things.fk.library.http.data;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Custom RequestBody for different ContentType see {@link ContentType}
 *
 * @author tic
 *         created on 18-4-10
 */

public class ContentReqBody extends RequestBody {

    public ContentReqBody(RequestBody body, String contentType) {

    }

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
