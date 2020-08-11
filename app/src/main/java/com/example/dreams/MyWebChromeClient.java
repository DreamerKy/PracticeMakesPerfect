package com.example.dreams;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;


public class MyWebChromeClient extends WebChromeClient {

    private Activity act;
    private ProgressBar progressBar;
    public OpenFileCallBack openFileCallBack;


    public MyWebChromeClient(Activity activity, ProgressBar bar, OpenFileCallBack openFileCallBack) {
        this.act = activity;
        this.progressBar = bar;
        this.openFileCallBack = openFileCallBack;
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        MessageLevel messageLevel = consoleMessage.messageLevel();
        if (MessageLevel.ERROR.compareTo(messageLevel) == 0) {
        }
        return super.onConsoleMessage(consoleMessage);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        result.confirm();
        return true;
    }

    //进度加载处理
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (null != progressBar) {
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                if (progressBar.getVisibility() == View.GONE)
                    progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
            act.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
        }
        super.onProgressChanged(view, newProgress);
    }


    /*
    内部接口，处理返回过
     */
    public interface OpenFileCallBack {

        void requestPicturePermission();

        //暂时不用
        void onShowFileChooser(ValueCallback<Uri[]> filePathCallback);
    }

    // For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileCallBack.requestPicturePermission();
    }

    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    // For Android > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                     FileChooserParams fileChooserParams) {
        openFileCallBack.onShowFileChooser(filePathCallback);
        return true;
    }


}
