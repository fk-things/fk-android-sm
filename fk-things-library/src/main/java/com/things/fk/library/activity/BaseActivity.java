package com.things.fk.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.common.base.Preconditions;
import com.things.fk.library.R;
import com.things.fk.library.database.Realms;
import com.things.fk.library.utils.Utilities;

import io.realm.Realm;

/**
 * @author tic
 *         created on 18-3-27
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected static final String TAG = BaseActivity.class.getSimpleName();
    private String activity;
    private Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getClass().getSimpleName();
        Log.i(TAG, activity + " onCreate");
        Utilities.strictMode();
    }

    public Realm getRealm() {
        return mRealm;
    }

    /**
     * init realm when we have db operation
     */
    protected void initRealm() {
        mRealm = Realms.getRealm();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, activity + " onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, activity + " onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, activity + " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, activity + " onDestroy");

        Realms.close(mRealm);
    }

    protected Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        Preconditions.checkNotNull(toolbar);

        int title = titleRes();
        Preconditions.checkArgument(title > 0);
        toolbar.setTitle(title);

        toolbar.setNavigationIcon(R.mipmap.ic_back_white);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showBack(false);
        return toolbar;
    }

    /**
     * get title res string
     *
     * @return title res string
     */
    abstract protected int titleRes();

    protected void setToolbarTitle(int title) {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(title);
    }

    protected void showBack(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);
        }
    }
}
