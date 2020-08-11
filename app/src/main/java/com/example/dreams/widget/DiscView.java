package com.example.dreams.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dreams.R;
import com.example.dreams.model.MusicData;
import com.example.dreams.uiutils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by likaiyu on 2019/11/6.
 * 唱盘
 */
public class DiscView extends RelativeLayout {

    private int mScreenWidth, mScreenHeight;
    private ViewPager mVpContain;
    private List<View> mDiscLayouts = new ArrayList<>();
    private List<MusicData> mMusicDatas = new ArrayList<>();
    private List<ObjectAnimator> mDiscAnimations = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;
    private IPlayInfo iPlayInfo;
    private ImageView mIvNeedle;
    private ObjectAnimator mNeedleAnimator;
    public static final int DURATION_NEEDLE_ANIAMTOR = 500;
    private NeedleAnimatorStatus needleAnimatorStatus = NeedleAnimatorStatus.IN_FAR_END;
    /*标记ViewPager是否处于偏移的状态*/
    private boolean mViewPagerIsOffset = false;
    /*标记唱针复位后，是否需要重新偏移到唱片处*/
    private boolean mIsNeed2StartPlayAnimator = false;

    public DiscView(Context context) {
        this(context, null);
    }

    public DiscView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiscView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = DisplayUtil.getScreenWidth(context);
        mScreenHeight = DisplayUtil.getScreenHeight(context);
    }

    public void setPlayInfo(IPlayInfo iPlayInfo) {
        this.iPlayInfo = iPlayInfo;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //加载背景
        initDiscBackground();
        //初始化控件
        initViewPager();

        //初始化唱针
        initNeedle();
        //初始化动画
        initAnimation();
    }

    private void initDiscBackground() {
        ImageView mDiscBackground = findViewById(R.id.ivDiscBlackgound);
        mDiscBackground.setImageDrawable(getDiscBackgroundDrawable());
        int marginTop = (int) (DisplayUtil.SCALE_DISC_MARGIN_TOP * mScreenHeight);
        LayoutParams layoutParams = (LayoutParams) mDiscBackground.getLayoutParams();
        layoutParams.setMargins(0, marginTop, 0, 0);
        mDiscBackground.setLayoutParams(layoutParams);
    }

    private void initViewPager() {
        mVpContain = findViewById(R.id.vpDiscContain);
        mVpContain.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mVpContain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (iPlayInfo != null) {
                    iPlayInfo.onMusicPicChanged(mMusicDatas.get(position).getmMusicBackPicRes());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                doWithAnimatorOnPageScrollChanged(state);
            }
        });
        viewPagerAdapter = new ViewPagerAdapter();
        mVpContain.setAdapter(viewPagerAdapter);

        LayoutParams layoutParams = (LayoutParams) mVpContain.getLayoutParams();
        int marginTop = (int) (DisplayUtil.SCALE_DISC_MARGIN_TOP * mScreenHeight);
        layoutParams.setMargins(0, marginTop, 0, 0);
        mVpContain.setLayoutParams(layoutParams);
    }

    private void doWithAnimatorOnPageScrollChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
            case ViewPager.SCROLL_STATE_SETTLING:
                mViewPagerIsOffset = false;
                playAnimator();
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                mViewPagerIsOffset = true;
                pauseAnimator();
                break;
        }
    }

    private void initNeedle() {
        mIvNeedle = findViewById(R.id.ivNeedle);
        int needleWidth = (int) (DisplayUtil.SCALE_NEEDLE_WIDTH * mScreenWidth);
        int needleHeight = (int) (DisplayUtil.SCALE_NEEDLE_HEIGHT * mScreenHeight);
        int marginTop = (int) (DisplayUtil.SCALE_NEEDLE_MARGIN_TOP * mScreenHeight) * -1;
        int marginLeft = (int) (DisplayUtil.SCALE_NEEDLE_MARGIN_LEFT * mScreenWidth);

        Bitmap originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_needle);
        Bitmap bitmap = Bitmap.createScaledBitmap(originBitmap, needleWidth, needleHeight, false);

        LayoutParams layoutParams = (LayoutParams) mIvNeedle.getLayoutParams();
        layoutParams.setMargins(marginLeft, marginTop, 0, 0);

        int pivotX = (int) (DisplayUtil.SCALE_NEEDLE_PIVOT_X * mScreenWidth);
        int pivotY = (int) (DisplayUtil.SCALE_NEEDLE_PIVOT_Y * mScreenWidth);
        mIvNeedle.setPivotX(pivotX);
        mIvNeedle.setPivotY(pivotY);
        mIvNeedle.setRotation(DisplayUtil.ROTATION_INIT_NEEDLE);
        mIvNeedle.setImageBitmap(bitmap);
        mIvNeedle.setLayoutParams(layoutParams);

    }

    private void initAnimation() {
        mNeedleAnimator = ObjectAnimator.ofFloat(mIvNeedle, View.ROTATION, DisplayUtil.ROTATION_INIT_NEEDLE, 0);
        mNeedleAnimator.setDuration(DURATION_NEEDLE_ANIAMTOR);
        mNeedleAnimator.setInterpolator(new AccelerateInterpolator());
        mNeedleAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (needleAnimatorStatus == NeedleAnimatorStatus.IN_FAR_END) {
                    needleAnimatorStatus = NeedleAnimatorStatus.TO_NEAR_END;
                } else if (needleAnimatorStatus == NeedleAnimatorStatus.IN_NEAR_END) {
                    needleAnimatorStatus = NeedleAnimatorStatus.TO_FAR_END;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (needleAnimatorStatus == NeedleAnimatorStatus.TO_NEAR_END) {
                    needleAnimatorStatus = NeedleAnimatorStatus.IN_NEAR_END;
                    int index = mVpContain.getCurrentItem();
                    playDiscAnimator(index);
                } else if (needleAnimatorStatus == NeedleAnimatorStatus.TO_FAR_END) {
                    needleAnimatorStatus = NeedleAnimatorStatus.IN_FAR_END;
                }

                if (mIsNeed2StartPlayAnimator) {
                    mIsNeed2StartPlayAnimator = false;
                    /**
                     * 只有在ViewPager不处于偏移状态时，才开始唱盘旋转动画
                     * */
                    if (!mViewPagerIsOffset) {
                        /*延时500ms*/
                        DiscView.this.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                playAnimator();
                            }
                        }, 50);
                    }
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void playAnimator() {
        if (needleAnimatorStatus == NeedleAnimatorStatus.IN_FAR_END) {
            mNeedleAnimator.start();
        } else if (needleAnimatorStatus == NeedleAnimatorStatus.TO_FAR_END) {
            mIsNeed2StartPlayAnimator = true;
        }

    }

    private void pauseAnimator() {
        if(needleAnimatorStatus == NeedleAnimatorStatus.IN_NEAR_END){
            int index = mVpContain.getCurrentItem();
            pauseDiscAnimator(index);
        }else if(needleAnimatorStatus == NeedleAnimatorStatus.TO_NEAR_END){
            mNeedleAnimator.reverse();
            needleAnimatorStatus = NeedleAnimatorStatus.TO_FAR_END;
        }

    }

    private void pauseDiscAnimator(int index) {
        ObjectAnimator objectAnimator = mDiscAnimations.get(index);
        objectAnimator.pause();
        mNeedleAnimator.reverse();
    }


    //打碟动画
    private void playDiscAnimator(int index) {
        ObjectAnimator objectAnimator = mDiscAnimations.get(index);
        if(objectAnimator.isPaused()){
            objectAnimator.resume();
        }else{
            objectAnimator.start();
        }
    }

    private Drawable getDiscBackgroundDrawable() {
        int discSize = (int) (mScreenWidth * DisplayUtil.SCALE_DISC_SIZE);
        Bitmap discBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_disc_blackground), discSize, discSize, false);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), discBitmap);
        return roundedBitmapDrawable;
    }

    public void setMusicData(List<MusicData> mMusicDatas) {
        if (mMusicDatas.isEmpty()) return;
        mDiscLayouts.clear();
        this.mMusicDatas.clear();
        mDiscAnimations.clear();
        this.mMusicDatas.addAll(mMusicDatas);
        for (MusicData musicData : mMusicDatas) {
            View discLayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_disc, mVpContain, false);
            ImageView disc = discLayout.findViewById(R.id.ivDisc);
            disc.setImageDrawable(getDiscDrawable(musicData.getMusicPicRes()));
            mDiscAnimations.add(getDiscObjectAnimator(disc));
            mDiscLayouts.add(discLayout);
        }

        viewPagerAdapter.notifyDataSetChanged();

    }

    private ObjectAnimator getDiscObjectAnimator(ImageView disc) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(disc,View.ROTATION,0, 360);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setDuration(5*10000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return objectAnimator;
    }

    //合成圖片
    private Drawable getDiscDrawable(int musicPicRes) {
        int discSize = (int) (mScreenWidth * DisplayUtil.SCALE_DISC_SIZE);
        int musicPicSize = (int) (mScreenWidth * DisplayUtil.SCALE_MUSIC_PIC_SIZE);
        Bitmap bitmapDisc = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_disc), discSize, discSize, false);
        Bitmap bitmapMusicPic = getMusicPicBitmap(musicPicSize, musicPicRes);
        BitmapDrawable disDrawable = new BitmapDrawable(bitmapDisc);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmapMusicPic);

        disDrawable.setAntiAlias(true);
        roundedBitmapDrawable.setAntiAlias(true);

        Drawable[] drawables = new Drawable[2];
        drawables[0] = roundedBitmapDrawable;
        drawables[1] = disDrawable;
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        int musicPicMargin = (int) ((DisplayUtil.SCALE_DISC_SIZE - DisplayUtil.SCALE_MUSIC_PIC_SIZE) * mScreenWidth / 2);
        layerDrawable.setLayerInset(0, musicPicMargin, musicPicMargin, musicPicMargin, musicPicMargin);
        return layerDrawable;
    }

    private Bitmap getMusicPicBitmap(int musicPicSize, int musicPicRes) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), musicPicRes, options);
        int imageWidth = options.outWidth;
        int sample = imageWidth / musicPicSize;
        int dstSample = 1;
        if (sample > dstSample) {
            dstSample = sample;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = dstSample;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), musicPicRes, options), musicPicSize, musicPicSize, false);
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View discLayout = mDiscLayouts.get(position);
            container.addView(discLayout);
            return discLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mDiscLayouts.get(position));
        }

        @Override
        public int getCount() {
            return mDiscLayouts.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    public enum MusicChangedStatus {
        PLAY, PAUSE, NEXT, LAST, STOP
    }

    /*唱针当前所处的状态*/
    private enum NeedleAnimatorStatus {
        /*移动时：从唱盘往远处移动*/
        TO_FAR_END,
        /*移动时：从远处往唱盘移动*/
        TO_NEAR_END,
        /*静止时：离开唱盘*/
        IN_FAR_END,
        /*静止时：贴近唱盘*/
        IN_NEAR_END
    }

    public interface IPlayInfo {

        void onMusicInfoChanged(String name);

        void onMusicPicChanged(int musicPicRes);

        void onMusicChanged(MusicChangedStatus musicChangedStatus);
    }


}
