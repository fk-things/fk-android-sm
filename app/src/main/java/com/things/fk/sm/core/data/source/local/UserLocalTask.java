package com.things.fk.sm.core.data.source.local;

import android.util.Log;

import com.things.fk.library.database.PrimaryKeys;
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

    public void insertOrReplace(User user) {
        mRealm.executeTransaction(realm -> {
            user.setId(PrimaryKeys
                    .nextPrimaryKey(realm, User.class));

            realm.copyToRealmOrUpdate(user);
        });
    }

    public void getUser(User user) {
        // mRealm.where(User.class).equalTo("userName", user.getUserName());
    }

    public void logAll() {
        RealmResults<User> data = mRealm.where(User.class).findAll();
        if (data == null) {
            return;
        }
        for (User user : data) {
            Log.e("User", user.toString());
        }
    }

    public User create(String userName, String password) {
        try {
            mRealm.beginTransaction();
            User user = mRealm.createObject(User.class, PrimaryKeys.nextPrimaryKey(mRealm, User.class));
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
        } finally {
            mRealm.commitTransaction();
        }

        return null;
    }
}
