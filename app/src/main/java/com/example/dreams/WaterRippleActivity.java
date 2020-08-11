package com.example.dreams;

import android.view.View;

import com.example.dreams.widget.WaterRippleView;

/**
 * Created by likaiyu on 2019/11/6.
 */
public class WaterRippleActivity extends BaseActivity {

    @Override
    public int layoutResId() {
        return R.layout.activity_water_ripple;
    }

    @Override
    public void initViews() {
        final WaterRippleView waterRipple = findViewById(R.id.water_ripple);

        findViewById(R.id.iv_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!waterRipple.isRunning()){
                    waterRipple.startAnimator();
                }else{
                    waterRipple.stopAnimator();
                }
            }
        });

    }

}
