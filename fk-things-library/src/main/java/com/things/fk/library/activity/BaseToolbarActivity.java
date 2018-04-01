package com.things.fk.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.things.fk.library.R;

/**
 * @author tic
 *         created on 18-3-29
 */

public abstract class BaseToolbarActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_main);
        mToolbar = initToolbar();
        init();
    }

    private void init() {
        ViewGroup container = findViewById(R.id.toolbar_container);
        Preconditions.checkNotNull(container);

        View mainView = getLayoutInflater().inflate(inflateLayout(), container, false);
        Preconditions.checkNotNull(mainView);
        ViewGroup.LayoutParams layoutParams = new ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mainView.setLayoutParams(layoutParams);
        container.removeAllViews();
        container.addView(mainView);
    }

    /**
     * get main layout
     * @return layout resource
     */
    abstract protected int inflateLayout();

}
