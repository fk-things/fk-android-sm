package com.things.fk.library.http;

import com.google.common.base.Preconditions;
import com.things.fk.library.http.data.HttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Response utils
 *
 * @author tic
 *         created on 18-4-2
 */

public class RespUtils {

    static HttpResponse success(int code, Response<?> response) {
        return create(code, response.message(), response.body().toString());
    }

    /**
     * handle response failed
     *
     * @param resp okhttp Response
     * @return HttpResponse
     * @throws IOException
     */
    static HttpResponse failed(Response<?> resp) throws IOException {
        HttpResponse httpResp = null;
        switch (resp.code()) {
            case 503:
                // cache失败,retry
                httpResp = exception(resp.code(), "retry again!");
                break;
            default:
                httpResp = exception(resp.code(), resp.errorBody());
                break;
        }

        return httpResp;
    }

    /**
     * wrap Respons exception
     *
     * @param code
     * @param message
     * @return HttpResponse
     */
    static HttpResponse exception(int code, String message) {
        return create(code, message, null);
    }

    static HttpResponse exception(int code, ResponseBody body) throws IOException {
        return create(code, "bad connection", new String(body.bytes()));
    }

    /**
     * response is correct
     *
     * @param response
     * @return
     */
    public static boolean isSuccess(HttpResponse response) {
        return response != null && response.isSuccess();
    }

    /**
     * data format is correct
     *
     * @param data
     * @return
     */
    public static boolean isDataOk(Map<String, Object> data) {
        if (data == null) {
            return false;
        }

        Preconditions.checkNotNull(data.get("ret"), "exception when data has no ret value "
                + Arrays.toString(data.values().toArray()));

        return (Integer) data.get("ret") == 0;
    }

    private static HttpResponse create(int code, String message, String body) {
        return new HttpResponse.Builder()
                .code(code)
                .message(message)
                .result(body)
                .build();
    }

}
