package com.example.dreams;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.dreams.widget.RotateAnimationZ;

/**
 * Created by likaiyu on 2019/11/6.
 */
public class RedPaperActivity extends BaseActivity {

    @Override
    public int layoutResId() {
        return R.layout.activity_red_paper;
    }

    @Override
    public void initViews() {
        final ImageView imageView = findViewById(R.id.iv_red_paper_open);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(RedPaperActivity.this, RedPaperActivity.class));

//                RotateAnimationZ rotateAnimationZ = new RotateAnimationZ();
//                rotateAnimationZ.isZhengfangxiang = false;
//                rotateAnimationZ.direction = rotateAnimationZ.Y;
//                rotateAnimationZ.setDuration(1000);
//                rotateAnimationZ.setInterpolator(new AccelerateDecelerateInterpolator());
//                rotateAnimationZ.setRepeatCount(0);
//                imageView.startAnimation(rotateAnimationZ);

                imageView.animate()
                        .setInterpolator(new LinearInterpolator())
                        .setDuration(2000)
                        .rotationY(720)
                        .start();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("onNewIntent  " + this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate  " + this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("onConfigurationChanged  " + this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart  "+ this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart  "+ this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume  "+ this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("onRestoreInstanceState  "+ this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        System.out.println("onRestoreInstanceState  "+ this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause  "+ this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop  "+ this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState  "+ this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        System.out.println("onSaveInstanceState  "+ this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy  "+ this);
    }
}
