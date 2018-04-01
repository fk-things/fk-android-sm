package com.things.fk.sm.core.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author tic
 *         created on 18-3-28
 */
public class User extends RealmObject {

    /**
     * 用户ID
     */
    @PrimaryKey
    private long _id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private long createTime;

    public User() {
    }

    public User(String userName, String pwd) {
        this.userName = userName;
        this.password = pwd;
    }

    /**
     * 设置：用户ID
     */
    public void setId(long _id) {
        this._id = _id;
    }

    /**
     * 获取：用户ID
     */
    public long getId() {
        return _id;
    }

    /**
     * 设置：用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public long getCreateTime() {
        return createTime;
    }
}
