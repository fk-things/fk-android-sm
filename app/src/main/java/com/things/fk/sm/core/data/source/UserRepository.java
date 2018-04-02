package com.things.fk.sm.core.data.source;

import com.things.fk.sm.core.data.source.local.UserLocalTask;
import com.things.fk.sm.core.data.source.remote.UserRemoteTask;

import java.util.Map;

import io.reactivex.Flowable;

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
        return mRemoteTask.login(userName, password);
    }

    public void logAll() {
        mLocalTask.logAll();
    }
}
