package com.things.fk.sm.core.data.source;

import com.things.fk.library.database.Executor;
import com.things.fk.library.database.Realms;
import com.things.fk.library.http.RespUtils;
import com.things.fk.sm.core.data.User;
import com.things.fk.sm.core.data.source.local.UserLocalTask;
import com.things.fk.sm.core.data.source.remote.UserRemoteTask;

import java.util.Map;

import io.reactivex.Flowable;
import io.realm.Realm;

/**
 * @author tic
 *         created on 18-4-1
 */

public class UserRepository {

    private UserRemoteTask mRemoteTask;
    private UserLocalTask mLocalTask;

    public UserRepository(UserRemoteTask remoteTask, UserLocalTask localTask) {
        this.mRemoteTask = remoteTask;
        this.mLocalTask = localTask;
    }

    public Flowable<Map<String, Object>> loginRequest(String userName, String password) {
        return mRemoteTask.userToken(userName, password)
                .doOnNext(data -> {
                    if (RespUtils.isDataOk(data)) {
                        User user = mLocalTask.create(userName, password);
                        Realms.execute(mLocalTask.getRealm(), new Executor() {
                            @Override
                            protected void run(Realm realm) {
                                user.setCreateTime(System.currentTimeMillis());
                            }
                        });
                    }
                });
    }

    public void logAll() {
        mLocalTask.logAll();
    }
}
