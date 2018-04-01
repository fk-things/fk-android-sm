package com.things.fk.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.view.WindowManager;

/**
 * Activity 工具
 * 跳转,全屏，状态栏高度等等
 * Created by tic on 18-1-4.
 */
public class Activities {
    /**
     * @param context
     * @param pkg     包名
     */
    public static void start(Context context, String pkg) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pkg);
        context.startActivity(intent);
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusbarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * screen全屏是否需要全屏
     *
     * @param screen Activity界面
     * @param enable true=全屏,false=非全屏
     */
    public static void fullScreen(Activity screen, boolean enable) {
        if (enable) {
            // go full screen
            WindowManager.LayoutParams attrs = screen.getWindow()
                    .getAttributes();
            // 添加全屏标志位到当前的flags
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            screen.getWindow().setAttributes(attrs);
            screen.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            // go non-full screen
            WindowManager.LayoutParams attrs = screen.getWindow()
                    .getAttributes();
            // 从当前的flags清除全屏标志位
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            screen.getWindow().setAttributes(attrs);
            screen.getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 应用卸载
     *
     * @param context
     * @param intent
     */
    public static void unInstall(Context context, Intent intent) {
        try {
            PackageManager mgr = context.getPackageManager();
            ResolveInfo res = mgr.resolveActivity(intent, 0);
            String pkg = res.activityInfo.packageName;
            if (pkg != null) {
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
                        Uri.parse("package:" + pkg));
                context.startActivity(uninstallIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
