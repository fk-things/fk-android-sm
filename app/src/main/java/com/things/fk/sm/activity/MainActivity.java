package com.things.fk.sm.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.Menu;

import com.things.fk.library.activity.BaseDrawerActivity;
import com.things.fk.library.utils.Utilities;
import com.things.fk.library.view.Xdialog;
import com.things.fk.sm.MainApplication;
import com.things.fk.sm.R;

/**
 * 主页面
 *
 * @author tic
 */
public class MainActivity extends BaseDrawerActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 主页面或splash页面数据库操作延迟等到application初始化完成
        MainApplication.addComplete(this::initRealm);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Xdialog.isShowing(dialog)) {
            dialog = Xdialog.loading(this, "加载中...");
        }
    }

    @Override
    protected int titleRes() {
        return R.string.str_tittle_page_main;
    }

    @Override
    protected int inflateHeaderView() {
        return R.layout.navigation_header;
    }

    @Override
    protected int inflateMenuRes() {
        return R.menu.navigation;
    }

    @Override
    protected NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener() {
        return item -> {
            item.setChecked(true);
            switch (item.getItemId()) {
                case R.id.nav_search1:
                    Utilities.startActivity(MainActivity.this,
                            new Intent(MainActivity.this, LoginActivity.class));
                    break;
                case R.id.nav_search2:
                    break;
//                    case R.id.nav_search3:
//                        break;
                case R.id.nav_search4:
                    break;
                case R.id.nav_search5:
                    break;
                case R.id.nav_search6:
                    break;
                case R.id.nav_search7:
                    break;
                case R.id.nav_search8:
                    break;
                case R.id.nav_search9:
                    break;
                default:
                    break;
            }
            return true;
        };
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }
}
