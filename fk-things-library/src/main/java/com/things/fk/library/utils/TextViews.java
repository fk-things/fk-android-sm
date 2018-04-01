package com.things.fk.library.utils;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * TextView utils
 *
 * @author tic
 */
public class TextViews {

    public static void setTextSafely(TextView textView, CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }

}
