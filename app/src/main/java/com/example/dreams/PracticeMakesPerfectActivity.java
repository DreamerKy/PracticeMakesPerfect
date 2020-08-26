package com.example.dreams;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dreams.aopdboperation.DBOperation;
import com.example.dreams.aspectj.LoginCheck;
import com.example.dreams.mvppractice.UserInfoActivity;
import com.example.dreams.okhttp_download.DownloadCallBack;
import com.example.dreams.okhttp_download.OkHttpFileDownloader;
import com.example.dreams.plugin.PluginActivity;
import com.example.dreams.uiutils.UIUtils;
import com.example.dreams.utils.Utils;
import com.example.dreams.widget.ChoosePictureActivity;
import com.example.dreams.widget.LoadingView;
import com.example.dreams.widget.SlideLockView;
import com.example.dreams.widget.circlepercentview.ProgressAnimateActivity;
import com.example.dreams.widget.miclockview.MiClockActivity;
import com.example.dreams.widget.mysoftkeyboard.KeyBoardActivity;
import com.kotlin.vip.arouterapi.RouterManager1;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import androidx.viewpager.widget.ViewPager;

public class PracticeMakesPerfectActivity extends BaseActivity implements DBOperation {

    private int max = 1000;
    private int progress = max;
    private SlideLockView lockView;
    private RelativeLayout bg;

    private ViewPager viewpager;
    String[] dataArray = {"头条", "头条", "头条", "头条", "头条", "头条", "头条", "头条", "头条", "头条"};

    private String url = "http://www.ntsc.ac.cn";

    private DBOperation dbOperation;

//    private var iBinder: IBinder? = null
//    private var isConnect = false
//    private var callBack = object : ITradeCallback.Stub(){
//        override fun onCallback(result: String?) {
//            toast(result.toString())
//        }
//    }
//    private var serviceConnection = object : ServiceConnection{
//        override fun onServiceDisconnected(name: ComponentName?) {
//            isConnect = false
//            iBinder = null
//        }
//
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            isConnect = true
//            iBinder = service
//        }
//    }

//    private fun getServiceConnect() {
//        println("连接服务")
//        val it = Intent()
//        it.action = "com.cardinfo.qpay"
//        it.setPackage("com.cardinfo.qpay")
//        context.bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
//    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    System.out.println("handleMessage");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<String, Object> params = new HashMap<>();
//        Glide.with(this).load("").into(new ImageView(this));

        dbOperation = (DBOperation) Proxy.newProxyInstance(DBOperation.class.getClassLoader(), new Class[]{DBOperation.class}, new DBHandler(PracticeMakesPerfectActivity.this));

        /*HttpUtils.sendJsonRequest(url, "", new ResponseCallback<String>() {
            @Override
            public void onSuccess(String responseBean) {

            }

            @Override
            public void onFailure() {

            }
        });

        HttpHelper.getInstance().post(url, params, new HttpCallback<String>() {
            @Override
            public void onSuccess(String object) {
            }

        });*/

//        XSelector.init(getApplication());
//        setContentView(R.layout.activity_practice);
//        viewpager = findViewById(R.id.viewpager);
//        viewpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return NewsFragment.getInstance(dataArray[position]);
//            }
//
//            @Override
//            public int getCount() {
//                return dataArray.length;
//            }
//
//        });
//        Button button = findViewById(R.id.button);
//        lockView = findViewById(R.id.lockview);
//        bg = findViewById(R.id.rl_bg);


//        XSelector.shadowHelper()
//                .setShapeRadius(dpToPx(8))
//                .setBgColor(Color.parseColor("#1DE9B6"))
//                .setShadowColor(Color.parseColor("#4DA65740"))
//                .setShadowRadius(dpToPx(8))
//                .setOffsetY(dpToPx(5))
//                .into(bg);


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                start();
//            }
//        });
//        CheckView mCheckBox = findViewById(R.id.checkbox);
//        mCheckBox.setText("美女");
//        mCheckBox.setChecked(true);


        //                    if (iBinder != null && isConnect) {
//                        try {
//                            IPaymentService.Stub.asInterface(iBinder).doBusiness(callBack)
//                        } catch (e: RemoteException) {
//                            e.printStackTrace()
//                        }
//
//                    } else {
//                        val intent = Intent()
//                        val component = ComponentName("com.cardinfo.qpay", "com.cardinfo.qpay.PaymentService")
//                        intent.component = component
//                        activity.startService(intent)
//                        if (!isConnect) getServiceConnect()
//                    }



    }

    @Override
    public int layoutResId() {
        return R.layout.activity_practice;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void initViews() {
        final Button wangYiBtn = findViewById(R.id.btn_wang_yi);
//        wangYiBtn.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
//
//            @Override
//            public void onDraw() {
//                test();
//                wangYiBtn.getViewTreeObserver().removeOnDrawListener(this);
//            }
//        });

        UIUtils.getInstance(this);
        wangYiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpFileDownloader.getInstance().init(PracticeMakesPerfectActivity.this);
                OkHttpFileDownloader.getInstance().startDownload("https://down.qq.com/qqweb/QQ_1/android_apk/Android_8.3.3.4515_537063791.apk", new DownloadCallBack() {
                    @Override
                    public void onFailure(IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PracticeMakesPerfectActivity.this, "下载失败！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(File file) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PracticeMakesPerfectActivity.this, "下载成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });



//                startActivity(new Intent(PracticeMakesPerfectActivity.this, WangYiMusicActivity.class));
            }
        });
        findViewById(R.id.btn_splash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, WangYiSplashActivity.class));
            }
        });
        findViewById(R.id.btn_slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, WangYiSlideActivity.class));
            }
        });
        findViewById(R.id.btn_bubble).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, WangYiBubbleActivity.class));
            }
        });
        findViewById(R.id.btn_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, WangYiLoadingActivity.class));
            }
        });
        findViewById(R.id.btn_draw_bezier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, DrawBezierActivity.class));
            }
        });
        findViewById(R.id.btn_water_ripple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, WaterRippleActivity.class));
            }
        });
        findViewById(R.id.btn_skin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager1.getInstance()
                        .build("/app/SkinTestActivity")
//                        .build("/order/Order_MainActivity")
                        .navigation(PracticeMakesPerfectActivity.this);
//                startActivity(new Intent(PracticeActivity.this, SkinTestActivity$$ARouter.findTargetClass("/app/SkinTestActivity")));
            }
        });
        findViewById(R.id.red_paper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, RedPaperActivity.class));
            }
        });
        findViewById(R.id.mi_clock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, MiClockActivity.class));
            }
        });

        findViewById(R.id.progress_animate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, ProgressAnimateActivity.class));
            }
        });

        findViewById(R.id.plugin_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startPluginActivity
                startActivity(new Intent(PracticeMakesPerfectActivity.this, PluginActivity.class));
            }
        });
        findViewById(R.id.method_hook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, HookActivity.class));
                dbOperation.save();
            }
        });
        findViewById(R.id.plugin_hook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.netease.plugin", "com.netease.plugin.PluginLoginActivity"));
                startActivity(intent);
            }
        });
        findViewById(R.id.my_view_pager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, MyViewPagerActivity.class));
            }
        });
        findViewById(R.id.my_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, SwitchActivity.class));
            }
        });

        findViewById(R.id.my_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this,HuaWeiLoadingActivity.class));
            }
        });
        findViewById(R.id.my_hook_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this,HookViewActivity.class));
            }
        });
        findViewById(R.id.my_eraser_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this,EraserViewActivity.class));
            }
        });
        findViewById(R.id.my_big_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this,BigViewActivity.class));
            }
        });

        findViewById(R.id.my_search_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this,SearchViewActivity.class));
            }
        });
        findViewById(R.id.my_event_bus_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this,FirstActivity.class));
            }
        });

        findViewById(R.id.my_flow_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this,FlowLayoutActivity.class));
            }
        });

        findViewById(R.id.my_key_board_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startKeyBoardActivity();
            }
        });

        findViewById(R.id.my_key_board_layout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(PracticeMakesPerfectActivity.this,KeyBoardTwoActivity.class));
                startActivity(new Intent(PracticeMakesPerfectActivity.this, UserInfoActivity.class));
            }
        });

        findViewById(R.id.my_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMakesPerfectActivity.this, FragmentTestActivity.class);
                byte[] data = new byte[1024 * 504];
                intent.putExtra("imageBytes",data);
                startActivity(intent);
//                Bundle bundle = new Bundle();
//                byte[] imageBytes = new byte[1024 * 1024 * 100];
//                bundle.putByteArray("image_bytes", imageBytes);
//                intent.putExtra("bundle",bundle);
            }
        });
        findViewById(R.id.my_degree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, DegreeViewActivity.class));
            }
        });
        findViewById(R.id.my_clock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, ClockViewActivity.class));
            }
        });

        findViewById(R.id.my_clock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, ClockViewActivity.class));
            }
        });
        findViewById(R.id.my_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, CameraViewActivity.class));
            }
        });

        findViewById(R.id.my_flip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, FlipViewActivity.class));
            }
        });

        findViewById(R.id.choose_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeMakesPerfectActivity.this, ChoosePictureActivity.class));
            }
        });



        /*TextView textView = findViewById(R.id.tv);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_l);
        drawable.setBounds(0, 0, 3*drawable.getIntrinsicWidth() / 4, 3*drawable.getIntrinsicHeight() / 4);
        VerticalImageSpan imageSpan = new VerticalImageSpan(drawable);
        SpannableString spanHalfYear = new SpannableString(" 图文混排测排测试图文混排测试图文混排测试图文混排测试图图文混排测排测试图文混排测试图文混排测试图文混排测试图");
        spanHalfYear.setSpan(imageSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanHalfYear);
        textView.append(new SpannableString("图文混排测排测试图文混排测试图文混排测试图文混排测试图图文混排测排测试图文混排测试图文混排测试图文混排测试图"));*/
    }

    private void test() {
        while(true){
            System.out.println("死循环");
        }
    }

    @LoginCheck
    private void startKeyBoardActivity() {
        System.out.println("LoginCheckAspect startKeyBoardActivityBefore");
        startActivity(new Intent(PracticeMakesPerfectActivity.this, KeyBoardActivity.class));
        System.out.println("LoginCheckAspect startKeyBoardActivityAfter");
    }

    private int dpToPx(int dp) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
    }

    public void start(View view) {
        ((LoadingView) view).startAllAnimator();
    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void save() {
        System.out.println("保存数据");
    }


    /*public void start() {
        progress = max;
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    progress--;
                    progressView.setMax(max);
                    progressView.setProgessAndText(progress, progress + "米");
                    if (progress == 0) {
                        progressView.setProgessAndText(progress, "结束！");
                        break;
                    }
                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        }.start();
    }*/

    class DBHandler implements InvocationHandler {

        private DBOperation dbOperation;

        DBHandler(DBOperation dbOperation) {
            this.dbOperation = dbOperation;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            if (dbOperation != null) {
                System.out.println("操作数据之前开始备份");
                method.invoke(dbOperation, args);
                System.out.println("数据备份完成等待操作");
                return null;
//            }
//            return null;
        }
    }


}
