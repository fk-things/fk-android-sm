package com.things.fk.library.database;

import io.realm.Realm;

/**
 * A functional interface to add, update, delete on Realm
 *
 * @author tic
 *         created on 18-4-9
 */

public abstract class Executor<T> {

    /**
     * for overide
     * <p>
     * run the executor right now and return the result T
     *
     * @param realm realm
     * @return result
     */
    protected T runForResult(Realm realm) {
        return null;
    }

    /**
     * for overide
     * <p>
     * run the executor right now
     *
     * @param realm
     */
    protected void run(Realm realm) {}

}
