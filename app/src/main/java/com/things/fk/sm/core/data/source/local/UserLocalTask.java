package com.things.fk.sm.core.data.source.local;

import com.things.fk.library.database.PrimaryKeys;
import com.things.fk.sm.core.data.User;

import io.realm.Realm;

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

}
