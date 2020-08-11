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

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

/**
 * Created by likaiyu on 2019/11/28.
 */
public class SkinnableButton extends AppCompatButton implements ViewMatch {


    private final AttrsBean attrsBean;

    public SkinnableButton(Context context) {
        this(context, null);
    }

    public SkinnableButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public SkinnableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsBean = new AttrsBean();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableButton, defStyleAttr, 0);
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableButton);
        typedArray.recycle();
    }

    @Override
    public void viewSkin() {
        int key = R.styleable.SkinnableButton[R.styleable.SkinnableButton_android_background];
        int backgroundResourceId = attrsBean.getViewResource(key);
        if (backgroundResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                //兼容包转换
                Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
                setBackgroundDrawable(drawable);
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

        key = R.styleable.SkinnableButton[R.styleable.SkinnableButton_android_textColor];
        int textColorResourceId = attrsBean.getViewResource(key);
        if (textColorResourceId > 0) {
            if (SkinManager.getInstance().isDefaultSkin()) {
                ColorStateList color = ContextCompat.getColorStateList(getContext(), textColorResourceId);
                setTextColor(color);
            }else{
                ColorStateList color = SkinManager.getInstance().getColorStateList(textColorResourceId);
                setTextColor(color);
            }
        }

//        key = R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_custom_typeface];
//        int textTypefaceResourceId = attrsBean.getViewResource(key);
//        if(textTypefaceResourceId>0){
//            if(SkinManager.getInstance().isDefaultSkin()){
//                setTypeface(Typeface.DEFAULT);
//            }else{
//                setTypeface(SkinManager.getInstance().getTypeface(textTypefaceResourceId));
//            }
//
//        }


    }
}
