package com.things.fk.library.http.data;

/**
 * @author tic
 *         created on 18-4-2
 */

public class HttpResponse {

    private Builder builder;

    public HttpResponse(Builder builder) {
        this.builder = builder;
    }

    public boolean isSuccess() {
        return builder.code == 200;
    }

    public String data() {
        return builder.data;
    }

    public String error() {
        return "code:" +
                builder.code +
                " message:" +
                builder.message +
                " data:" +
                builder.data;
    }

    public static class Builder {

        /**
         * code
         */
        private int code;
        private String message;
        private String data;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder result(String body) {
            this.data = body;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }
    }

}
