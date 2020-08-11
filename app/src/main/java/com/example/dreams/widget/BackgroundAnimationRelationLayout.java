package com.example.dreams.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.dreams.R;

import androidx.annotation.RequiresApi;

/**
 * Created by likaiyu on 2019/11/6.
 * 播放界面背景
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class BackgroundAnimationRelationLayout extends RelativeLayout {

    private LayerDrawable layerDrawable;
    private ObjectAnimator objectAnimator;
    private int musicPicRes = -1;

    public BackgroundAnimationRelationLayout(Context context) {
        this(context,null);
    }

    public BackgroundAnimationRelationLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BackgroundAnimationRelationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @SuppressLint("ObjectAnimatorBinding")
    private void init() {
        Drawable backgroundDrawable = getContext().getDrawable(R.drawable.ic_blackground);
        Drawable[] drawables = new Drawable[2];
        drawables[0] = backgroundDrawable;
        drawables[1] = backgroundDrawable;
        layerDrawable = new LayerDrawable(drawables);
        //设置动画
        objectAnimator = ObjectAnimator.ofFloat(this,"xxx",0f,1.0f);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int foregroundAlpha = (int) ((float)animation.getAnimatedValue() * 255);
                layerDrawable.getDrawable(1).setAlpha(foregroundAlpha);
                setBackground(layerDrawable);
            }
        });
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layerDrawable.setDrawable(0,layerDrawable.getDrawable(1));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    public void setForeground(Drawable drawable){
        layerDrawable.setDrawable(1,drawable);
        objectAnimator.start();
    }

    public boolean isNeed2UpdateBackground(int musicPicRes) {
        if (this.musicPicRes == -1) return true;
        if (musicPicRes != this.musicPicRes) {
            return true;
        }
        return false;
    }


}


