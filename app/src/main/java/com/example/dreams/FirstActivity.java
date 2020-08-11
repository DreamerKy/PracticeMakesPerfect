package com.example.dreams;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        EventBus.getDefault().register(this);
    }

    // 订阅方法
    @Subscribe
    public void event(String string) {
        Log.e("event >>> ", string);
    }

    // 测试优先级
    @Subscribe(priority = 10, sticky = true)
    public void event2(String string) {
        Log.e("event2 >>> ", string);
    }

    // 点击事件
    public void jump(View view) {
        EventBus.getDefault().postSticky("sticky");
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
