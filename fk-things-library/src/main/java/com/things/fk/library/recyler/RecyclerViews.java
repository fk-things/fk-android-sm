package com.things.fk.library.recyler;

import android.view.View;

/**
 * @author tic
 *         created on 18-3-28
 */

public class RecyclerViews {
    public interface OnItemClickListener {

        void onItemClick(View view, View parent, int position);
    }
}
