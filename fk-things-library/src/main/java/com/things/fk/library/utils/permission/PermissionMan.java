package com.things.fk.library.utils.permission;

import android.app.Activity;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * 权限管理
 *
 * @author tic
 */
public class PermissionMan implements PermissionChecker.Man<PermissionMan> {

    public interface RequestCallback {
        /**
         * 权限被用户永久禁用，但功能需要该权限，
         * 提示用户去应用设置页面修改
         *
         * @param permission
         */
        void dontAskAgainTip(String permission);

        /**
         * 权限被用户拒绝
         *
         * @param permission
         */
        void deny(String permission);

        /**
         * 授权成功, activityResult 调用
         *
         * @param permission
         */
        void granted(String permission);
    }

    private static PermissionMan INSTANCE;
    private Activity activity;
    private boolean checked = true;

    public static PermissionMan getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PermissionMan();
        }
        return INSTANCE;
    }

    @Override
    public PermissionMan setUp(Activity activity) {
        this.activity = activity;
        return INSTANCE;
    }

    @Override
    public PermissionMan check(String... permissions) {
        if (activity == null) {
            throw new IllegalArgumentException("Call setup before check permission please");
        }
        if (permissions.length <= 0) {
            throw new IllegalArgumentException("Request permission can not null");
        }
        PermissionChecker impl = PermissionChecker.getImpl();
        impl.granted(activity, permissions, checked);

        for (String permission : permissions) {
            if (impl.isDontAskAgain(permission)) {
                RequestCallback callback = tryGetCallback();
                if (callback != null) {
                    callback.dontAskAgainTip(permission);
                }
            }
        }
        checked = false;
        return INSTANCE;
    }

    @Override
    public PermissionMan check(List<String> permission) {
        if (permission == null || permission.isEmpty()) {
            throw new IllegalArgumentException("Request permission can not null");
        }
        String[] permissions = new String[permission.size()];
        check(permission.toArray(permissions));
        return INSTANCE;
    }


    /**
     * 回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PermissionChecker.REQUEST_CODE_PERMISSION) {
            RequestCallback callback = tryGetCallback();
            if (grantResults.length <= 0) {
                return;
            }

            for (int i = 0; i < grantResults.length; i++) {
                String permission = permissions[i];
                if (callback != null) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        callback.granted(permission);
                    } else {
                        callback.deny(permission);
                    }
                }
            }
            checked = true;
        }
    }

    private RequestCallback tryGetCallback() {
        if (activity instanceof RequestCallback) {
            return (RequestCallback) activity;
        }
        return null;
    }
}
