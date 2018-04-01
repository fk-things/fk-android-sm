package com.things.fk.library.utils.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * permission checker
 * @author tic
 */
public class PermissionChecker {

    static final int REQUEST_CODE_PERMISSION = 12580;

    private static class ClassHolder {
        static final PermissionChecker INSTANCE = new PermissionChecker();
    }

    static PermissionChecker getImpl() {
        return ClassHolder.INSTANCE;
    }

    public interface Man<T> {
        /**
         *
         * @param activity
         */
        T setUp(Activity activity);

        /**
         *
         * @param permission
         */
        T check(String... permission);

        /**
         * @param permission
         */
        T check(List<String> permission);
    }

    private List<String> daas = new ArrayList<>();

    /**
     * check permissions
     *
     * @param activity
     * @param permissions
     * @return
     */
    void granted(Activity activity, String[] permissions, boolean firstLauncher) {
        daas.clear();
        ArrayList<String> request = new ArrayList<>();
        for (String permission : permissions) {
            int hasPermission = ContextCompat.checkSelfPermission(activity, permission);
            if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                continue;
            }
            // if user choose don't ask again and deny the permission or first time start app return false
            boolean daa = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            if (!daa) {
                daas.add(permission);
                continue;
            }
            request.add(permission);
        }
        if (request.isEmpty() && !firstLauncher) {
            return;
        }
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_PERMISSION);
    }

    boolean isDontAskAgain(String permission) {
        return daas.contains(permission);
    }
}
