package com.kotlin.vip.skinlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotlin.vip.skinlibrary.SkinManager;
import com.kotlin.vip.skinlibrary.core.CustomerAppCompatViewInflater;
import com.kotlin.vip.skinlibrary.core.ViewMatch;
import com.kotlin.vip.skinlibrary.utils.ActionBarUtils;
import com.kotlin.vip.skinlibrary.utils.NavigationUtils;
import com.kotlin.vip.skinlibrary.utils.StatusBarUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

/**
 * Created by likaiyu on 2019/11/28.
 */
public class SkinActivity extends AppCompatActivity {
    private CustomerAppCompatViewInflater viewInflater;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (openChangeSkin()) {
            if (viewInflater == null) {
                viewInflater = new CustomerAppCompatViewInflater(context);
            }
            viewInflater.setName(name);
            viewInflater.setAttrs(attrs);
            return viewInflater.autoMatch();
        }
        return super.onCreateView(parent, name, context, attrs);
    }

    protected boolean openChangeSkin() {
        return false;
    }

    protected void defaultSkin(int themeColorId){
        this.skinDynamic(null,themeColorId);
    }

    protected void skinDynamic(String skinPath, int themeColorId) {
        SkinManager.getInstance().loaderSkinResources(skinPath);
        if (themeColorId != 0) {
            int themeColor = SkinManager.getInstance().getColor(themeColorId);
            StatusBarUtils.forStatusBar(this, themeColor);
            NavigationUtils.forNavigation(this, themeColor);
            ActionBarUtils.forActionBar(this, themeColor);
        }
        applyViews(getWindow().getDecorView());
    }

    private void applyViews(View view) {
        if (view instanceof ViewMatch) {
            ViewMatch viewMatch = (ViewMatch) view;
            viewMatch.viewSkin();
        }
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                applyViews(parent.getChildAt(i));
            }
        }
    }
}
