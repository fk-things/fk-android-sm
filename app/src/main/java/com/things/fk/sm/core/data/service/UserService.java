package com.things.fk.sm.core.data.service;

import com.things.fk.sm.core.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author tic
 *         created on 18-3-28
 */

public interface UserService {

    @POST("users/new")
    Call<User> createUser(@Body User user);

    /**
     * request login
     *
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("users/login")
    Call<User> login(@Field(value = "userName") String userName, @Field(value = "password") String password);

}
