package com.things.fk.library.view;

import android.annotation.SuppressLint;
import android.support.annotation.ColorRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.things.fk.library.R;
import com.things.fk.library.utils.Utilities;

/**
 * Snackbar utils
 * @author tic
 *         created on 18-4-9
 */

public class Snackbars {

    public static void make(View view, CharSequence text) {
        make(view, text, null, null, null);
    }

    public static void make(View view, CharSequence text, BaseTransientBottomBar.BaseCallback<Snackbar> callback) {
        make(view, text, null, null, callback);
    }

    @SuppressLint("ResourceType")
    public static void make(View view, CharSequence text, CharSequence actionText, View.OnClickListener actionCallback,
                            BaseTransientBottomBar.BaseCallback<Snackbar> callback) {
        make(view, text, actionText, actionCallback, ResourcesCompat
                        .getColor(Utilities.getContext().getResources(), R.color.colorAccent, null),
                Snackbar.LENGTH_LONG, callback);
    }

    @SuppressLint("ResourceAsColor")
    private static void make(View view, CharSequence text, CharSequence actionText, View.OnClickListener actionCallback,
                             @ColorRes int actionTextColor, int duration, BaseTransientBottomBar.BaseCallback<Snackbar> callback) {
        Snackbar snackbar = Snackbar.make(view, text, duration)
                .setActionTextColor(actionTextColor)
                .addCallback(callback);
        if (Utilities.isNotNull(actionCallback)) {
            snackbar.setAction(actionText, actionCallback);
        }
    }
}
