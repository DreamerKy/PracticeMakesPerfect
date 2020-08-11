package com.kotlin.vip.order;

import android.os.Bundle;
import android.util.Log;

import com.kotlin.vip.annotation.ARouter;

import androidx.appcompat.app.AppCompatActivity;


@ARouter(path = "/order/Order_MainActivity")
public class Order_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);

        Log.e("netease >>> ", "order/Order_MainActivity");

        if (getIntent() != null) {
            String content = getIntent().getStringExtra("name");
            Log.e("netease >>> ", "接收参数值：" + content);
        }
    }
}
