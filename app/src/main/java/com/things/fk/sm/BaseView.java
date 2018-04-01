package com.things.fk.sm;

/**
 * @author tic
 *         created on 18-3-30
 */

public interface BaseView<P extends BasePresenter> {
    /**
     * bind the presenter
     *
     * @param presenter model presenter
     */
    void setPresenter(P presenter);

}
