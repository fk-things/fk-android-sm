package com.things.fk.library.utils;

import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

/**
 *
 * 输入法打开/关闭监听
 * Created by tic on 17-11-21.
 */

public class InputMethodHelper {

    private int rootViewVisibleHeight;

    public interface OnIMMChangeListener {
        void keyBoardShow();
        void keyBoardHide();
    }

    public static void showIMM(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(windowToken, InputMethodManager.SHOW_FORCED);
    }

    public void hideIMM(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }

    public void addIMMListener(final View root, final OnIMMChangeListener listener) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                root.getWindowVisibleDisplayFrame(r);

                int visibleHeight = r.height();
                // System.out.println("" + visibleHeight);
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }

                if (rootViewVisibleHeight - visibleHeight > 200) {
                    if (listener != null) {
                        listener.keyBoardShow();
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                if (visibleHeight - rootViewVisibleHeight > 200) {
                    if (listener != null) {
                        listener.keyBoardHide();
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }
            }
        });
    }

}
