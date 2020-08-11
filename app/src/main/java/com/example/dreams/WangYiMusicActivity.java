package com.example.dreams;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.dreams.model.MusicData;
import com.example.dreams.utils.BitmapUtil;
import com.example.dreams.widget.BackgroundAnimationRelationLayout;
import com.example.dreams.widget.DiscView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2019/11/6.
 */
public class WangYiMusicActivity extends BaseActivity implements View.OnClickListener, DiscView.IPlayInfo {

    private DiscView mDiscView;
    private List<MusicData> mMusicDatas = new ArrayList<>();
    private BackgroundAnimationRelationLayout backgroundRelativeLayout;

    @Override
    public int layoutResId() {
        return R.layout.activity_wangyi_music;
    }

    @Override
    public void initViews() {
        findViewById(R.id.ivPlayOrPause).setOnClickListener(this);
        mDiscView = findViewById(R.id.discview);
        mDiscView.setPlayInfo(this);
        backgroundRelativeLayout = findViewById(R.id.rootLayout);
        initData();
    }

    private void initData() {

        MusicData musicData1 = new MusicData(R.raw.music1, R.raw.image1, R.raw.image1,"寻", "三亩地");
        MusicData musicData2 = new MusicData(R.raw.music2, R.raw.image2, R.raw.image2,"Nightingale", "YANI");
        MusicData musicData3 = new MusicData(R.raw.music3, R.raw.image3, R.raw.image3,"Cornfield Chase", "Hans Zimmer");

        mMusicDatas.add(musicData1);
        mMusicDatas.add(musicData2);
        mMusicDatas.add(musicData3);

        mDiscView.setMusicData(mMusicDatas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPlayOrPause:

                break;
        }
    }

    @Override
    public void onMusicInfoChanged(String name) {

    }

    @Override
    public void onMusicPicChanged(final int musicPicRes) {
        if (backgroundRelativeLayout.isNeed2UpdateBackground(musicPicRes)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Drawable foregroundDrawable = BitmapUtil.getForegroundDrawable(WangYiMusicActivity.this, musicPicRes);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(foregroundDrawable!=null){
                                backgroundRelativeLayout.setForeground(foregroundDrawable);
                            }
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onMusicChanged(DiscView.MusicChangedStatus musicChangedStatus) {

    }
}
