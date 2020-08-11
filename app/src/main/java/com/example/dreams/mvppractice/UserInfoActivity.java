package com.example.dreams.mvppractice;

import android.widget.Toast;

import com.example.dreams.R;
import com.example.dreams.mvppractice.annotation.PresenterInjection;
import com.example.dreams.mvppractice.base.BaseMvpActivity;

public class UserInfoActivity extends BaseMvpActivity<UserInfoPresenter> implements UserInfoContract.UserInfoView {

    @PresenterInjection
    private UserInfoPresenter mPresenter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
//        getPresenter().getUsers("xxxxx");
        mPresenter.getUsers("ddd");
    }

//    @Override
//    protected UserInfoPresenter createPresenter() {
//        return new UserInfoPresenter();
//    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(UserInfo userinfo) {
        Toast.makeText(this, userinfo.toString(), Toast.LENGTH_LONG).show();
    }
}
