package com.example.dreams;

import android.app.Activity;
import android.os.Bundle;

import com.example.dreams.uiutils.StatusBarUtil;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResId());
        StatusBarUtil.setTranslateStateBar(this);
        initViews();
    }

    public abstract int layoutResId();
    public abstract void initViews();

}
