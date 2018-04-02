package com.things.fk.sm.core.login;

import com.things.fk.sm.BasePresenter;
import com.things.fk.sm.BaseView;

/**
 * @author tic
 *         created on 18-3-28
 */

public interface ILoginContract {

    interface View extends BaseView<Presenter> {
        /**
         * show loading view when in busy
         */
        void loading();

        /**
         * 加载失败
         */
        void showLoadingError();

        /**
         * 关闭加载
         */
        void dismissLoading();

        void loginSuccess();

        void loginFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void login(String userName, String password);
    }
}
