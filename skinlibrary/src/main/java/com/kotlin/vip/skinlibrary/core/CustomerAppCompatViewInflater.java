package com.kotlin.vip.skinlibrary.core;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.kotlin.vip.skinlibrary.views.SkinnableButton;
import com.kotlin.vip.skinlibrary.views.SkinnableImageView;
import com.kotlin.vip.skinlibrary.views.SkinnableLinearLayout;
import com.kotlin.vip.skinlibrary.views.SkinnableRelativeLayout;
import com.kotlin.vip.skinlibrary.views.SkinnableTextView;

import androidx.appcompat.app.AppCompatViewInflater;

/**
 * Created by likaiyu on 2019/11/28.
 */
public final class CustomerAppCompatViewInflater extends AppCompatViewInflater {

    private final Context context;
    private String name;
    private AttributeSet attrs;

    public CustomerAppCompatViewInflater( Context context) {
        this.context = context;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttrs(AttributeSet attrs) {
        this.attrs = attrs;
    }

    /**
     * 自动匹配控件名，并初始化控件对象
     */
    public View autoMatch() {
        View view = null;
        switch (name) {
            case "LinearLayout":
                view = new SkinnableLinearLayout(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "RelativeLayout":
                view = new SkinnableRelativeLayout(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "TextView":
                view = new SkinnableTextView(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "ImageView":
                view = new SkinnableImageView(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "Button":
                view = new SkinnableButton(context, attrs);
                this.verifyNotNull(view, name);
                break;
        }
        return view;
    }


    private void verifyNotNull(View view, String name) {
        if (view == null) {
            throw new IllegalStateException(this.getClass().getName() + " asked to inflate view for <" + name + ">, but returned null");
        }
    }
}
