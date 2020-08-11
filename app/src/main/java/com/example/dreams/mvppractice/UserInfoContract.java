package com.example.dreams.mvppractice;

import com.example.dreams.mvppractice.base.BaseView;

import rx.Observable;

/**
 * Created by likaiyu on 2020/4/18.
 */
public class UserInfoContract {

    interface UserInfoView extends BaseView{
        void onLoading();
        void onError();
        void onSuccess(UserInfo userinfo);
    }

    interface UserInfoPresenter{
        void getUsers(String token);
    }

    interface UserInfoModel{
        Observable<UserInfo> getUser(String token);
    }


}
