package com.things.fk.library.database;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.things.fk.library.utils.Utilities;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author tic
 *         created on 18-3-28
 */

public class Realms {

    private static final String TAG = Realms.class.getSimpleName();

    public static final String DB_NAME_DEFAULT = "default_db";
    public static final int DB_VERSION = 1;

    private static int sOpenRealmCount;

    /**
     * init once when launch application
     *
     * @param context
     */
    public static void init(Context context) {
        Realm.init(context);
        Realm.setDefaultConfiguration(getConfig(DB_NAME_DEFAULT));
    }

    public static RealmConfiguration getConfig(String tag) {
        return new RealmConfiguration.Builder()
                .schemaVersion(DB_VERSION)
                .name(tag)
//              .modules(new DBLibraryModule())
                .migration(DBMigration.getInstance())
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static Realm getRealm() {
        return getRealm(null);
    }

    /**
     * 初始化Realm数据库
     *
     * @param tag tag是根据数据库文件名区分
     * @return Realm单例
     */
    public static Realm getRealm(String tag) {
        addOpenRealmCount();
        if (TextUtils.isEmpty(tag)) {
            return Realm.getDefaultInstance();
        }
        return Realm.getInstance(getConfig(tag));
    }

    /**
     * close realm
     *
     * @param realm realm to closed
     */
    public static void close(Realm realm) {
        if (Utilities.isNotNull(realm) && !realm.isClosed()) {
            realm.close();
            reduceOpenRealmCount();
        }
    }

    /**
     * wrap realm transaction, auto rollback transaction when catch exception
     *
     * @param realm
     * @param executor
     */
    public static <T> T executeForResult(Realm realm, Executor<T> executor) {
        try {
            Preconditions.checkNotNull(executor,
                    "executor callback cannot be null");

            realm.beginTransaction();
            return executor.runForResult(realm);
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
        } finally {
            realm.commitTransaction();
        }
        return null;
    }

    /**
     * wrap realm transaction, auto rollback transaction when catch exception
     *
     * @param realm
     * @param executor
     */
    public static void execute(Realm realm, Executor executor) {
        try {
            Preconditions.checkNotNull(executor,
                    "executor callback cannot be null");

            realm.beginTransaction();
            executor.run(realm);
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
        } finally {
            realm.commitTransaction();
        }
    }

    private static void addOpenRealmCount() {
        if (sOpenRealmCount < 100) {
            sOpenRealmCount++;
            Log.d(TAG, "Get Realm Over " + sOpenRealmCount + " Times");
        } else {
            Log.e(TAG, "Alarm -> Get Realm Over 100 Times");
        }
    }

    private static void reduceOpenRealmCount() {
        if (sOpenRealmCount > 0) {
            sOpenRealmCount--;
            Log.d(TAG, "close Realm Over " + sOpenRealmCount + " Times");
        }
    }
}
