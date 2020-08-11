package com.example.dreams.factory;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class UserInfoFactory {

    public static Api createApi() {
        return new ApiImpl();
    }
}
