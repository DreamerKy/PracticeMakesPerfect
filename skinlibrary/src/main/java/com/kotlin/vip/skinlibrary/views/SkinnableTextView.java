package com.kotlin.vip.skinlibrary.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.kotlin.vip.skinlibrary.R;
import com.kotlin.vip.skinlibrary.SkinManager;
import com.kotlin.vip.skinlibrary.core.ViewMatch;
import com.kotlin.vip.skinlibrary.model.AttrsBean;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/**
 * Created by likaiyu on 2019/12/1.
 */
public class SkinnableTextView extends AppCompatTextView implements ViewMatch {


    private final AttrsBean attrsBean;

    public SkinnableTextView(Context context) {
        this(context, null);
    }

    public SkinnableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableTextView, defStyleAttr, 0);
        attrsBean = new AttrsBean();
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableTextView);
        typedArray.recycle();
    }

    @Override
    public void viewSkin() {
        int key = R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_android_background];
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
                    setBackgroundDrawable(drawable);
                }
            }
        }

        key = R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_android_textColor];
        int textColorResourceId = attrsBean.getViewResource(key);
        if (textColorResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                ColorStateList color = ContextCompat.getColorStateList(getContext(), textColorResourceId);
                setTextColor(color);
            } else {
                ColorStateList color = SkinManager.getInstance().getColorStateList(textColorResourceId);
                setTextColor(color);
            }
        }

//        key = R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_custom_typeface];
//        int textTypefaceResourceId = attrsBean.getViewResource(key);
//        if (textTypefaceResourceId > 0) {
//            if (SkinManager.getInstance().isDefaultSkin()) {
//                setTypeface(Typeface.DEFAULT);
//            } else {
//                setTypeface(SkinManager.getInstance().getTypeface(textTypefaceResourceId));
//            }
//        }


    }
}
