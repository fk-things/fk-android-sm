package com.things.fk.library.database;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * @author tic
 *         created on 18-4-1
 */

public class PrimaryKeys {

    private static final String TAG = PrimaryKeys.class.getSimpleName();

    /**
     * get the next primary key with key name
     *
     * @param realm
     * @param clazz
     * @param primaryKey key name
     * @return
     */
    public static long nextPrimaryKey(Realm realm, Class<? extends RealmModel> clazz, String primaryKey) {
        long count = 1;
        synchronized (TAG) {
            RealmResults<? extends RealmModel> result = realm.where(clazz).findAll();
            if (!result.isEmpty()) {
                count = realm.where(clazz).max(primaryKey).longValue() + 1;
            }
        }
        return count;
    }

    /**
     * get the next primary key
     *
     * @param realm
     * @param clazz
     * @return
     */
    public static long nextPrimaryKey(Realm realm, Class<? extends RealmModel> clazz) {
        return nextPrimaryKey(realm, clazz, "_id");
    }
}
