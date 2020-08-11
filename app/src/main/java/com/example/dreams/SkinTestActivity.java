package com.example.dreams;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kotlin.vip.annotation.ARouter;
import com.kotlin.vip.skinlibrary.base.SkinActivity;
import com.kotlin.vip.skinlibrary.utils.PreferencesUtils;

import java.io.File;

import androidx.annotation.RequiresApi;

/**
 * 如果图标有固定的尺寸，不需要更改，那么drawable更加适合
 * 如果需要变大变小变大变小的，有动画的，放在mipmap中能有更高的质量
 */

@ARouter(path = "/app/SkinTestActivity")
public class SkinTestActivity extends SkinActivity {

    private String skinPath;
    public static final String SDCARD_FOLDER = Environment.getExternalStorageDirectory() + "/neteaseskin/";

    @Override
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File dir = new File(SDCARD_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // File.separator含义：拼接 /

        skinPath = SDCARD_FOLDER + "net163.skin";

        // 运行时权限申请（6.0+）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(perms, 200);
            }
        }

        if (("net163").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            skinDynamic(skinPath, R.color.skin_item_color);
        } else {
            defaultSkin(R.color.colorPrimary);
        }
        //AIDL Test
//        bindMyService();
    }

    ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IPaymentService iPaymentService = IPaymentService.Stub.asInterface(service);
            try {
                iPaymentService.doBusiness(new ITradeCallback() {
                    @Override
                    public void onCallback(String result) throws RemoteException {

                    }

                    @Override
                    public IBinder asBinder() {
                        return null;
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void bindMyService() {

//        Intent intent = new Intent();
//        intent.setAction("XXXService");
//        (intent,con,BIND_AUTO_CREATE);
    }

    // 换肤按钮（api限制：5.0版本）
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void skinDynamic(View view) {
        // 真实项目中：需要先判断当前皮肤，避免重复操作！
        if (!("net163").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            Log.e("netease >>> ", "-------------start-------------");
            long start = System.currentTimeMillis();
            File file = new File(skinPath);
            if(file.exists()){
                skinDynamic(skinPath, R.color.skin_item_color);
                PreferencesUtils.putString(this, "currentSkin", "net163");
            }else{
                Toast.makeText(this, "暂无皮肤资源", Toast.LENGTH_SHORT).show();
            }
            long end = System.currentTimeMillis() - start;
            Log.e("netease >>> ", "换肤耗时（毫秒）：" + end);
            Log.e("netease >>> ", "-------------end---------------");
        }
    }

    // 默认按钮（api限制：5.0版本）
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void skinDefault(View view) {
        if (!("default").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            Log.e("netease >>> ", "-------------start-------------");
            long start = System.currentTimeMillis();

            defaultSkin(R.color.colorPrimary);
            PreferencesUtils.putString(this, "currentSkin", "default");

            long end = System.currentTimeMillis() - start;
            Log.e("netease >>> ", "还原耗时（毫秒）：" + end);
            Log.e("netease >>> ", "-------------end---------------");
        }
    }

    // 跳转按钮
    public void jumpSelf(View view) {
        startActivity(new Intent(this, this.getClass()));
    }

    @Override
    protected boolean openChangeSkin() {
        return true;
    }
}
