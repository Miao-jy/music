package com.example.music.view.login.manager;

import com.example.music.view.login.entity.User;

public class UserManager {
    private volatile static UserManager mInstance;

    private User mUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (mInstance == null) {
            synchronized (UserManager.class) {
                if (mInstance == null) {
                    mInstance = new UserManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 保存User信息到进内存
     */
    public void saveUser(User user) {
        this.mUser = user;
        saveLocal(user);
    }

    /**
     * 保存User信息到数据库
     */
    private void saveLocal(User user) {

    }

    public User getUser() {
        return this.mUser;
    }

    /**
     * 从数据库中获取User信息
     */
    private User getLocalUser() {
        return null;
    }

    /**
     * 清楚User信息
     */
    public void removeUser() {
        this.mUser = null;
        removeLocalUser();
    }

    /**
     * 清楚数据库中User信息
     */
    private void removeLocalUser() {

    }

    /**
     * @return ture -已经登录
     *         false -没有登录
     */
    public boolean hasLogin() {
        return getUser() != null;
    }
} 
