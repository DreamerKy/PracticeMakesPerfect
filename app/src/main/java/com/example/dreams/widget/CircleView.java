package com.example.dreams.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2019/11/8.
 */
public class CircleView extends View {
    private WaterRippleView rippleAnimationView;

    public CircleView(WaterRippleView rippleAnimationView) {
        this(rippleAnimationView.getContext(), null);
        this.rippleAnimationView = rippleAnimationView;
        this.setVisibility(INVISIBLE);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = (Math.min(getWidth(), getHeight())) / 2;
        canvas.drawCircle(radius, radius, radius - rippleAnimationView.getStrokWidth(), rippleAnimationView.getPaint());
    }
}
