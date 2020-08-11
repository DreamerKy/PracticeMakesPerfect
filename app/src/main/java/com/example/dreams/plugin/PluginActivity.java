package com.example.dreams.plugin;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.View;

import com.example.dreams.BaseActivity;
import com.example.dreams.R;

import java.io.File;

/**
 * Created by likaiyu on 2019/12/18.
 */
public class PluginActivity extends BaseActivity {
    @Override
    public int layoutResId() {
        return R.layout.activity_plugin;
    }

    @Override
    public void initViews() {
        findViewById(R.id.btn_load_plugin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPlugin();
            }
        });

        findViewById(R.id.btn_start_plugin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPluginActivity();
            }
        });


        findViewById(R.id.btn_load_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadStaticReceiver();
            }
        });

        findViewById(R.id.btn_send_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendStaticReceiver();
            }
        });

    }


    // 加载插件
    public void loadPlugin() {
        PluginManager.getInstance(this).loadPlugin();
    }

    // 启动插件里面的Activity
    public void startPluginActivity() {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "p.apk");
        String path = file.getAbsolutePath();

        // 获取插件包 里面的 Activity
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        ActivityInfo activityInfo = packageInfo.activities[0];

        // 占位  代理Activity
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", activityInfo.name);
        startActivity(intent);
    }

    // 注册 插件里面 配置的 静态广播接收者
    public void loadStaticReceiver() {
        PluginManager.getInstance(this).parserApkAction();
    }

    // 发送静态广播
    public void sendStaticReceiver() {
        Intent intent = new Intent();
        intent.setAction("plugin.static_receiver");
        sendBroadcast(intent);
    }
}
