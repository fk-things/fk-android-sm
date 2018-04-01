package com.things.fk.sm;

/**
 * @author tic
 *         created on 18-3-30
 */

public interface BasePresenter<V extends BaseView> {

    void subscribe();

    void unsubscribe();
}
