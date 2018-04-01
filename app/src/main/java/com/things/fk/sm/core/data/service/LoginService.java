package com.things.fk.sm.core.data.service;

import com.things.fk.sm.core.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author tic
 *         created on 18-3-28
 */

public interface LoginService {

    @POST("users/new")
    Call<User> createUser(@Body User user);

    @POST("users/login")
    void login(@Body User user);

}
