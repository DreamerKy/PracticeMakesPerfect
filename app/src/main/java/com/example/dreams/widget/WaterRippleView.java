package com.example.dreams.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.dreams.R;
import com.example.dreams.uiutils.UIUtils;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

/**
 * Created by likaiyu on 2019/11/8.
 */
public class WaterRippleView extends RelativeLayout {
    private int radius;
    private int strokWidth;
    private int rippleColor;
    private Paint paint;
    private ArrayList<CircleView> viewList = new ArrayList<>();
    private AnimatorSet animatorSet;
    private boolean isRunning = false;

    public WaterRippleView(Context context) {
        this(context, null);
    }

    public WaterRippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleAnimationView);
        int rippleType = typedArray.getInt(R.styleable.RippleAnimationView_ripple_anim_type, 0);
        radius = typedArray.getInteger(R.styleable.RippleAnimationView_radius, 54);
        strokWidth = typedArray.getInteger(R.styleable.RippleAnimationView_strokWidth, 2);
        rippleColor = typedArray.getColor(R.styleable.RippleAnimationView_ripple_anim_color, ContextCompat.getColor(context, R.color.rippleColor));
        paint.setStrokeWidth(UIUtils.getInstance().getWidth(strokWidth));
        if (rippleType == 0) {
            paint.setStyle(Paint.Style.FILL);
        } else {
            paint.setStyle(Paint.Style.STROKE);
        }
        paint.setColor(rippleColor);
        typedArray.recycle();
        LayoutParams rippleParams = new LayoutParams(UIUtils.getInstance().getWidth(radius + strokWidth), UIUtils.getInstance().getWidth(radius + strokWidth));
        rippleParams.addRule(CENTER_IN_PARENT, TRUE);
        float maxScale = 10;
        int rippleDuration = 5000;
        int singleDelay = rippleDuration / 4;//时间间隔
        ArrayList<Animator> animators = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CircleView circleView = new CircleView(this);
            addView(circleView, rippleParams);
            viewList.add(circleView);
            //x方向扩散
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(circleView, "ScaleX", 1.0f, maxScale);
            scaleXAnimator.setDuration(rippleDuration);
            scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleXAnimator.setStartDelay(i * singleDelay);
            animators.add(scaleXAnimator);
            //y方向扩散
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(circleView,"ScaleY",1.0f,maxScale);
            scaleYAnimator.setDuration(rippleDuration);
            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleYAnimator.setStartDelay(i*singleDelay);
            animators.add(scaleYAnimator);
            //透明度
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(circleView,"Alpha",1.0f,0f);
            alphaAnimator.setDuration(rippleDuration);
            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            alphaAnimator.setStartDelay(i*singleDelay);
            animators.add(alphaAnimator);

        }

        animatorSet = new AnimatorSet();
        animatorSet.setDuration(rippleDuration);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(animators);

    }

    public void startAnimator(){
        if(!isRunning){
            for(CircleView circleView : viewList){
                circleView.setVisibility(VISIBLE);
            }
            animatorSet.start();
            isRunning = true;
        }
    }

    public void stopAnimator(){
        if(isRunning){
            for(CircleView circleView : viewList){
                circleView.setVisibility(INVISIBLE);
            }
            animatorSet.cancel();
            isRunning = false;
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public int getStrokWidth() {
        return strokWidth;
    }

    public Paint getPaint() {
        return paint;
    }


}
