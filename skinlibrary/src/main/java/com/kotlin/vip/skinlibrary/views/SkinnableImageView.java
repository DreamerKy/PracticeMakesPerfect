package com.kotlin.vip.skinlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.kotlin.vip.skinlibrary.R;
import com.kotlin.vip.skinlibrary.SkinManager;
import com.kotlin.vip.skinlibrary.core.ViewMatch;
import com.kotlin.vip.skinlibrary.model.AttrsBean;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

/**
 * Created by likaiyu on 2019/12/1.
 */
public class SkinnableImageView extends AppCompatImageView implements ViewMatch {
    private final AttrsBean attrsBean;

    public SkinnableImageView(Context context) {
        this(context, null);
    }

    public SkinnableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsBean = new AttrsBean();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableImageView, defStyleAttr, 0);
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableImageView);
        typedArray.recycle();

    }

    @Override
    public void viewSkin() {
        int key = R.styleable.SkinnableImageView[R.styleable.SkinnableImageView_android_src];
        int backgroundResourceId = attrsBean.getViewResource(key);
        if (backgroundResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
                setImageDrawable(drawable);
            } else {
                //获取皮肤资源包
                Object skinResourceId = SkinManager.getInstance().getBackgroundOrSrc(backgroundResourceId);
                //兼容包转换
                if (skinResourceId instanceof Integer) {
                    int color = (int) skinResourceId;
                    setImageResource(color);
                } else {
                    Drawable drawable = (Drawable) skinResourceId;
                    setImageDrawable(drawable);
                }
            }
        }

    }
}
