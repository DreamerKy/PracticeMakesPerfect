package com.example.dreams.utils;

import android.app.Activity;

import com.example.dreams.base.BaseDialog;

import java.lang.ref.WeakReference;

/**
 * Created by Dreams on 2019/2/11.
 */

public class Tools {

    /**
     * 通用关闭等待对话框
     *
     * @param act
     */
    private static volatile BaseDialog waitingDialog;

    public static void showDialog(final Activity act) {
        final WeakReference<Activity> weakReference = new WeakReference<>(act);
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                waitingDialog = new BaseDialog(weakReference.get());
//                    waitingDialog.setContentView(R.layout.base_dialog_layout);
                waitingDialog.setCancelable(true);
//                    waitingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//                    TextView msg = (TextView) waitingDialog.findViewById(R.id.id_tv_loadingmsg);
//                    msg.setText("加载中...");
                waitingDialog.show();
            }
        });
    }

    /**
     * 通用关闭等待对话框 synchronized避免并发访问 在调用showDialog()的类的onPause中，
     * 加上Tools.closeDialog();是很好的做法，能够避免窗口泄露
     */
    public static synchronized void closeDialog() {
        try {
            if (waitingDialog != null) {
                if (waitingDialog.isShowing()) {
                    waitingDialog.cancel();
                }
                waitingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
