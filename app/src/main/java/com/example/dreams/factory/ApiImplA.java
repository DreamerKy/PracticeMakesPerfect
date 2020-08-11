package com.example.dreams.factory;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class ApiImplA implements Api {
    @Override
    public UserInfo create() {
        UserInfo userInfo = new UserInfo("李二狗");
        return userInfo;
    }
}
