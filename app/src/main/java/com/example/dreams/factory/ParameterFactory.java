package com.example.dreams.factory;

/**
 * Created by likaiyu on 2019/11/12.
 */
public class ParameterFactory {

    public static Api createApi(int parameter) {
        switch(parameter){
            case 1:
                return new ApiImpl();
            case 2:
                return new ApiImplA();
        }
        return null;

    }


}
