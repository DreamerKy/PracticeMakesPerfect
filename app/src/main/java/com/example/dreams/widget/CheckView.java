package com.example.dreams.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dreams.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by likaiyu on 2019/7/10.
 */

public class CheckView extends FrameLayout implements View.OnClickListener {

    String text;
    boolean checked;

    public CheckView(@NonNull Context context) {
        this(context,null);
    }

    public CheckView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CheckView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CheckView);
        text = ta.getString(R.styleable.CheckView_text);
        checked = ta.getBoolean(R.styleable.CheckView_checked, false);
        ta.recycle();
        init();
    }

    private TextView mTv;
    private CheckBox mCb;
    private RelativeLayout root;

    private void init() {
        View view = View.inflate(getContext(), R.layout.check_box_layout, this);
        mTv = view.findViewById(R.id.tv);
        mCb = view.findViewById(R.id.cb);
        mTv.setText(text);
        mCb.setChecked(checked);
        root = view.findViewById(R.id.root);
        root.setOnClickListener(this);
    }

    public void setText(String text) {
        mTv.setText(text);
    }

    public boolean isChecked() {
        return mCb.isChecked();
    }

    public void setChecked(boolean checked) {

        mCb.setChecked(checked);

    }


    @Override
    public void onClick(View v) {
        mCb.setChecked(!mCb.isChecked());
    }
}
