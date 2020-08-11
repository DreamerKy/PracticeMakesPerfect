package com.kotlin.vip.skinlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.kotlin.vip.skinlibrary.R;
import com.kotlin.vip.skinlibrary.SkinManager;
import com.kotlin.vip.skinlibrary.core.ViewMatch;
import com.kotlin.vip.skinlibrary.model.AttrsBean;

import androidx.core.content.ContextCompat;

public class SkinnableRelativeLayout extends RelativeLayout implements ViewMatch {

    private AttrsBean attrsBean;

    public SkinnableRelativeLayout(Context context) {
        this(context, null);
    }

    public SkinnableRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableRelativeLayout, defStyleAttr, 0);
        attrsBean = new AttrsBean();
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableRelativeLayout);
        typedArray.recycle();
    }

    @Override
    public void viewSkin() {

        int key = R.styleable.SkinnableRelativeLayout[R.styleable.SkinnableRelativeLayout_android_background];
        int viewResource = attrsBean.getViewResource(key);
        if (viewResource > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), viewResource);
                setBackground(drawable);
            }else {
                Object skinResourceId = SkinManager.getInstance().getBackgroundOrSrc(viewResource);
                if (skinResourceId instanceof Integer) {
                    int color = (int) skinResourceId;
                    setBackgroundColor(color);
                } else {
                    Drawable drawable = (Drawable) skinResourceId;
                    setBackground(drawable);
                }
            }
        }


        // 根据自定义属性，获取styleable中的background属性
//        int key = R.styleable.SkinnableRelativeLayout[R.styleable.SkinnableRelativeLayout_android_background];
//        // 根据styleable获取控件某属性的resourceId
//        int backgroundResourceId = attrsBean.getViewResource(key);
//        if (backgroundResourceId > 0) {
//            // 是否默认皮肤
//            if (SkinManager.getInstance().isDefaultSkin()) {
//                // 兼容包转换
//                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
//                // 控件自带api，这里不用setBackgroundColor()因为在9.0测试不通过
//                // setBackgroundDrawable在这里是过时了
//                setBackground(drawable);
//            } else {
//                // 获取皮肤包资源
//                Object skinResourceId = SkinManager.getInstance().getBackgroundOrSrc(backgroundResourceId);
//                // 兼容包转换
//                if (skinResourceId instanceof Integer) {
//                    int color = (int) skinResourceId;
//                    setBackgroundColor(color);
//                    // setBackgroundResource(color); // 未做兼容测试
//                } else {
//                    Drawable drawable = (Drawable) skinResourceId;
//                    setBackground(drawable);
//                }
//            }
//        }
    }
}
