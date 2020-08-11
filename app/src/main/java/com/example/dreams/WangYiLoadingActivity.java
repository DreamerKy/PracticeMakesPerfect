package com.example.dreams;

import com.example.dreams.widget.LoadingView;

/**
 * Created by likaiyu on 2019/11/6.
 */
//@ARouter(path = "/app/WangYiLoadingActivity")
public class WangYiLoadingActivity extends BaseActivity {

    @Override
    public int layoutResId() {
        return R.layout.activity_loading_view;
    }

    @Override
    public void initViews() {
        final LoadingView mLoadingView = findViewById(R.id.loading_view);
        mLoadingView.startAllAnimator();
    }

}
