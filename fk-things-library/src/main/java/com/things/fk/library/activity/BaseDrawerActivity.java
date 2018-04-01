package com.things.fk.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.google.common.base.Preconditions;
import com.things.fk.library.R;
import com.things.fk.library.utils.Fragments;

/**
 * @author tic
 *         created on 18-3-29
 */

public abstract class BaseDrawerActivity extends BaseActivity {

    private static final String TAG = BaseDrawerActivity.class.getSimpleName();

    private Toolbar mToolbar;

    /**
     * inflate header view
     *
     * @return header view
     */
    abstract protected int inflateHeaderView();

    /**
     * inflate menu xml resource
     *
     * @return menu resource
     */
    abstract protected int inflateMenuRes();

    /**
     * OnNavigationItemSelectedListener
     *
     * @return NavigationView.OnNavigationItemSelectedListener
     */
    abstract protected NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_main);
        mToolbar = initToolbar();
        initDrawlayer();
        initContainer();
    }

    /**
     * replace when drawer menu selected
     *
     * @param fragment
     */
    protected void replace(Fragment fragment) {
        Fragments.insertOrReplace(this, fragment, R.id.toolbar_container, TAG);
    }

    private void initContainer() {
        FrameLayout mContainer = findViewById(R.id.toolbar_container);

    }

    private void initDrawlayer() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                mToolbar, R.string.str_menu_1, R.string.str_menu_2);
        toggle.syncState();

        int headerView = inflateHeaderView();
        Preconditions.checkArgument(headerView > 0);
        navigationView.inflateHeaderView(headerView);

        int menus = inflateMenuRes();
        Preconditions.checkArgument(menus > 0);
        navigationView.inflateMenu(menus);
    }
}
