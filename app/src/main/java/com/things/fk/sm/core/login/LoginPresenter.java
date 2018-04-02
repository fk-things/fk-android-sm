package com.things.fk.sm.core.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.things.fk.library.http.RespUtils;
import com.things.fk.sm.core.data.parser.UserParser;
import com.things.fk.sm.core.data.source.UserRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author tic
 *         created on 18-3-28
 */

public class LoginPresenter implements ILoginContract.Presenter {

    private final UserRepository mRepository;
    private ILoginContract.View mViews;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public LoginPresenter(ILoginContract.View view, UserRepository repository) {
        this.mViews = Preconditions.checkNotNull(view, "Login View cannot be null");
        this.mRepository = Preconditions.checkNotNull(repository, "repository cannot be null");
        this.mViews.setPresenter(this);
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void login(String userName, String password) {
        mViews.loading();

        mCompositeDisposable.clear();

        Disposable disposable = mRepository
                .loginRequest(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    Log.e("login", "login done");

                })
                .subscribe(
                        // onNext
                        data -> {
                            if (RespUtils.isDataOk(data)) {

                                mViews.loginSuccess();
                                mViews.dismissLoading();
                            } else {
                                mViews.loginFailed((String) data.get(UserParser.KEY_MESSAGE));
                                mViews.showLoadingError();
                            }
                        },
                        // onError
                        throwable -> {
                            throwable.printStackTrace();
                            mViews.showLoadingError();
                            mViews.loginFailed(throwable.getMessage());
                        });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
