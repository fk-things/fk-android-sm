package com.things.fk.sm.core.data.source.local;

import android.util.Log;

import com.google.common.base.Preconditions;
import com.things.fk.library.database.Executor;
import com.things.fk.library.database.PrimaryKeys;
import com.things.fk.library.database.Realms;
import com.things.fk.sm.core.data.User;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author tic
 *         created on 18-3-28
 */

public class UserLocalTask {

    private Realm mRealm;

    public UserLocalTask(Realm realm) {
        this.mRealm = realm;
    }

    public Realm getRealm() {
        return mRealm;
    }

    /**
     * 插入或者更新
     *
     * @param user
     */
    public void insertOrReplace(User user) {
        Preconditions.checkNotNull(mRealm);

        Realms.execute(mRealm, new Executor() {
            @Override
            protected void run(Realm realm) {
                user.setId(PrimaryKeys.nextPrimaryKey(realm, User.class));
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public void getUser(User user) {
        // mRealm.where(User.class).equalTo("userName", user.getUserName());
    }

    public void logAll() {
        Preconditions.checkNotNull(mRealm);
        RealmResults<User> data = mRealm.where(User.class).findAll();
        if (data == null) {
            return;
        }
        for (User user : data) {
            Log.e("User", user.toString());
        }
    }

    public User create(String userName, String password) {
        Preconditions.checkNotNull(mRealm);

        return Realms.executeForResult(mRealm, new Executor<User>() {
            @Override
            protected User runForResult(Realm realm) {
                User user = realm.createObject(User.class, PrimaryKeys.nextPrimaryKey(mRealm, User.class));
                user.setUserName(userName);
                user.setPassword(password);
                return user;
            }
        });
    }
}
