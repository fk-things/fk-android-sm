package com.things.fk.library.utils;

import android.text.TextUtils;

import java.util.Locale;

/**
 * @author tic
 *         created on 18-3-27
 */

public class DbUtils {

    /**
     * 创建范围条件查询语句
     *
     * @param columnName
     * @param values
     * @return
     */
    public static String createDbSelectionQuery(String columnName, Iterable<?> values) {
        return String.format(Locale.ENGLISH, "%s IN (%s)", columnName, TextUtils.join(", ", values));
    }

}
