package com.things.fk.library.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.things.fk.library.R;
import com.things.fk.library.utils.Utilities;

/**
 * material loadingDialog
 *
 * @author tic
 *         created on 18-4-9
 */
public class Xdialog {

    /**
     * loading dialog for activity only
     *
     * @param activity activity
     * @param message
     * @return
     */
    public static Dialog loading(Activity activity, CharSequence message) {
        LayoutInflater ll = activity.getLayoutInflater();
        View view = ll.inflate(R.layout.dialog_loading, null);
        TextView messager = view.findViewById(R.id.tv_message);
        messager.setText(message);

        MaterialDialog dialog = new MaterialDialog.Builder(activity)
                .customView(view, false)
                .build();
        dialog.show();
        return dialog;
    }

    /**
     * dismiss dialog
     *
     * @param dialog
     */
    public static void dismiss(Dialog dialog) {
        if (Utilities.isNotNull(dialog) && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * dialog is showing
     *
     * @param dialog
     * @return
     */
    public static boolean isShowing(Dialog dialog) {
        return Utilities.isNotNull(dialog) && dialog.isShowing();
    }


}
