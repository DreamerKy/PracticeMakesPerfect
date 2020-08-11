package com.kotlin.vip.skinlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.kotlin.vip.skinlibrary.R;
import com.kotlin.vip.skinlibrary.SkinManager;
import com.kotlin.vip.skinlibrary.core.ViewMatch;
import com.kotlin.vip.skinlibrary.model.AttrsBean;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Created by likaiyu on 2019/12/1.
 */
public class SkinnableLinearLayout extends LinearLayout implements ViewMatch {
    private AttrsBean attrsBean;

    public SkinnableLinearLayout(Context context) {
        this(context, null);
    }

    public SkinnableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableLinearLayout, defStyleAttr, 0);
        attrsBean = new AttrsBean();
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableLinearLayout);
        typedArray.recycle();
    }

    @Override
    public void viewSkin() {
        int key = R.styleable.SkinnableLinearLayout[R.styleable.SkinnableLinearLayout_android_background];
        int backgroundResourceId = attrsBean.getViewResource(key);

        if (backgroundResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
                setBackground(drawable);
            } else {
                Object skinResourceId = SkinManager.getInstance().getBackgroundOrSrc(backgroundResourceId);
                if (skinResourceId instanceof Integer) {
                    int color = (int) skinResourceId;
                    setBackgroundColor(color);
                } else {
                    Drawable drawable = (Drawable) skinResourceId;
                    setBackground(drawable);
                }
            }

        }
    }
}
