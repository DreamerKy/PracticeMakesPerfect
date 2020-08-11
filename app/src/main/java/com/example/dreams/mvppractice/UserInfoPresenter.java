package com.example.dreams.mvppractice;

import com.example.dreams.mvppractice.base.BasePresenter;

/**
 * Created by likaiyu on 2020/4/18.
 */
public class UserInfoPresenter extends BasePresenter<UserInfoContract.UserInfoView, UserInfoModel> implements UserInfoContract.UserInfoPresenter {

    @Override
    public void getUsers(String token) {
        getView().onLoading();
        getModel().getUser(token).subscribe(new BaseSubscriber<UserInfo>() {
            @Override
            public void onNext(UserInfo userInfo) {
                getView().onSuccess(userInfo);
            }

            @Override
            protected void onError(String errorCode, String errorMessage) {

            }
        });
    }
}
