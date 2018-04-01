package com.things.fk.library.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.common.base.Preconditions;

/**
 * Fragment utils
 *
 * @author tic
 *         created on 18-3-30
 */

public class Fragments {

    public static void insertOrReplace(AppCompatActivity activity, Fragment fragment, int id, String tag) {
        Preconditions.checkNotNull(fragment);
        Preconditions.checkNotNull(tag);
        FragmentTransaction transition = activity.getSupportFragmentManager()
                .beginTransaction();
        transition.replace(id, fragment, tag);
        transition.commit();
    }


}
