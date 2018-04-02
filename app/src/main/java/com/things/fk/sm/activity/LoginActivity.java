package com.things.fk.sm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.things.fk.library.activity.BaseToolbarActivity;
import com.things.fk.library.database.PrimaryKeys;
import com.things.fk.sm.R;
import com.things.fk.sm.core.data.User;
import com.things.fk.sm.core.data.source.UserRepository;
import com.things.fk.sm.core.data.source.local.UserLocalTask;
import com.things.fk.sm.core.data.source.remote.UserRemoteTask;
import com.things.fk.sm.core.login.ILoginContract;
import com.things.fk.sm.core.login.LoginPresenter;

/**
 * @author tic
 *         created on 18-3-29
 */

public class LoginActivity extends BaseToolbarActivity implements ILoginContract.View, View.OnClickListener {

    private EditText mEditPwd;
    private EditText mEditUserName;

    private ILoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBack(true);
        setupViews();
        initRealm();

        new LoginPresenter(this, new UserRepository(new UserRemoteTask(),
                new UserLocalTask(getRealm())));
    }

    private void setupViews() {
        findViewById(R.id.btn_login).setOnClickListener(this);
        mEditUserName = findViewById(R.id.edit_user_name);
        mEditPwd = findViewById(R.id.edit_password);
    }

    @Override
    protected int titleRes() {
        return R.string.str_login;
    }

    @Override
    protected int inflateLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String pwd = mEditPwd.getText().toString();
                String userName = mEditUserName.getText().toString();

                getRealm().executeTransaction(realm -> {
                    User user = getRealm().createObject(User.class,
                            PrimaryKeys.nextPrimaryKey(getRealm(), User.class));
                    user.setPassword(pwd);
                    user.setUserName(userName);
                });
                mPresenter.login(userName, pwd);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(ILoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void loading() {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void loginSuccess() {
        // SharedPreference cache user login state
    }

    @Override
    public void loginFailed(String message) {

    }
}
