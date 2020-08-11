package com.example.dreams.mvppractice;

import com.example.dreams.mvppractice.base.BaseModel;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by likaiyu on 2020/4/18.
 */
public class UserInfoModel extends BaseModel implements UserInfoContract.UserInfoModel {
    @Override
    public Observable<UserInfo> getUser(String token) {
        //网络请求
        return Observable.unsafeCreate(new Observable.OnSubscribe<UserInfo>() {
            @Override
            public void call(Subscriber<? super UserInfo> subscriber) {
                UserInfo userInfo = new UserInfo();
                userInfo.au_nickname = "小李";
                userInfo.au_sex = "男";
                subscriber.onNext(userInfo);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
