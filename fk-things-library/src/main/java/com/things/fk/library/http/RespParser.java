package com.things.fk.library.http;

import java.util.Map;

/**
 * @author tic
 *         created on 18-4-2
 */

public interface RespParser {

    String KEY_MESSAGE = "message";

    String KEY_RET = "ret";


    /**
     * parse HttpResponse body data
     *
     * @param json
     * @return
     */
    Map<String, Object> parse(String json);
}
