package com.example.dreams.factory;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class Test {
    public static void main(String[] args) {
        //常规编码
        Api api = new ApiImpl();
        UserInfo userInfo = api.create();
        //简单工厂，降低模块间耦合度
        Api api1 = UserInfoFactory.createApi();
        api1.create();

        //根据不同参数实现
        Api aapi = ParameterFactory.createApi(1);
        if (aapi != null) {
            aapi.create();
        }

    }

}
