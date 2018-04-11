package com.things.fk.library.http.data;

/**
 * Http request content type
 *
 * @author tic
 *         created on 18-4-10
 */

public interface ContentType {

    int JSON = 1;
    int CRYPTO_JSON = 2;
    int X_CRYPTO_JSON = 3;
    int RSA_JSON = 4;

    String APPLICATION_JSON = "application/json; charset=UTF-8";

    String APPLICATON_CRYPTO_JSON = "application/crypto-json";

    String APPLICATION_X_CRYPTO_JSON = "application/x-crypto-json";

    String APPLICATION_RSA_JSON = "application/rsa-json";
}
