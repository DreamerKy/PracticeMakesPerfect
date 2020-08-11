package com.example.dreams;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dreams.bean.ImageHandleBean;
import com.example.dreams.qr.AppConfig;
import com.example.dreams.qr.MipcaActivityCapture;
import com.example.dreams.qr.Utils;
import com.example.dreams.utils.Base64BitmapUtil;
import com.example.dreams.utils.BitmapUtil;
import com.example.dreams.utils.Tools;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

//public class MainActivity extends AppCompatActivity implements MyWebChromeClient.OpenFileCallBack {
//
//    @BindView(R.id.web_view)
//    WebView mWebView;
//    @BindView(R.id.progress_bar)
//    ProgressBar mProgress;
//
//    public static final int GET_RESULT_REQUEST = 0;
//    private static final int REQUEST_CHOOSE_CODE = 1;
//    private static final int REQUEST_TAKE_PHOTO = 2;
//    /**
//     * 压缩照片宽度
//     */
//    public static final int DES_PIC_WIDTH = 600;
//    /**
//     * 压缩照片高度
//     */
//    public static final int DES_PIC_HEIGHT = 450;
//    /**
//     * 照片最大值
//     */
//    public static final int DES_PIC_MAX_SIZE = 500;
//
//    private String choosePath;
//    private RxPermissions rxPermissions;
//    private Uri mOutputUri;//拍照后uri
//    private String sdPath;//图片根目录
//    private String photoPath;//拍照后图片路径
//    private String photoid, chooseid;
//
//    ValueCallback<Uri[]> mUploadMessage5;
//    // 相册、拍照
//    private static final int REQUEST_CODE_PICK_IMAGE = 3;
//    private static final int REQUEST_CODE_IMAGE_CAPTURE = 4;
//    private File tempFile;
//    private Uri mOutPutUri;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_web);
//        ButterKnife.bind(this);
//        initView();
//    }
//
//    private void initView() {
//        clearCache();
//        fileDir();
//        WebSettings setting = mWebView.getSettings();
//        mWebView.setWebViewClient(new MyWebViewClient());
//        mWebView.setWebChromeClient(new MyWebChromeClient(this, mProgress, MainActivity.this));
//        mWebView.addJavascriptInterface(new JavaScriptInterface(), "android");
//
//        // 设置默认的显示编码
//        setting.setDefaultTextEncodingName("UTF-8");
//        setting.setTextZoom(100);
//        setting.setJavaScriptEnabled(true);
//        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        setting.setAppCacheEnabled(true);
//        setting.setAppCacheMaxSize(1024 * 1024 * 10);// 10M
//        setting.setTextZoom(100);
//        setting.setSavePassword(false);
//        rxPermissions = new RxPermissions(MainActivity.this);
//        mWebView.loadUrl("https://mobile.cdtft.cn/login/toLogin");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void requestPicturePermission() {
//        showOptions();
//    }
//
//
//    private void requestCamerPermissions() {
//        rxPermissions.request(
//                Manifest.permission.CAMERA
//        ).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if (aBoolean) {
//                    takePicture();
//                } else {
//                    Toast.makeText(MainActivity.this, "访问相册未授权", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void requestStoragePermissions() {
//        rxPermissions.request(
//                Manifest.permission.READ_EXTERNAL_STORAGE
//        ).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if (aBoolean) {
//                    Intent showImgIntent =
//                            new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(showImgIntent, REQUEST_CODE_PICK_IMAGE);
//                } else {
//                    Toast.makeText(MainActivity.this, "访问相机未授权", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onShowFileChooser(ValueCallback<Uri[]> filePathCallback) {
//        this.mUploadMessage5 = filePathCallback;
//        showOptions();
//    }
//
//    public void showOptions() {
//        CharSequence[] sequences = {"相册", "拍照"};
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialogInterface) {
//                if (mUploadMessage5 != null) {
//                    mUploadMessage5.onReceiveValue(null);
//                    mUploadMessage5 = null;
//                }
//            }
//        });
//        alertDialog.setTitle("选择图片");
//        alertDialog.setItems(sequences, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) {
//                    requestStoragePermissions();
//                } else {
//                    requestCamerPermissions();
//                }
//            }
//        });
//        alertDialog.show();
//    }
//
//    /**
//     * 拍照获取图片
//     */
//    private void takePicture() {
//        Intent intent =
//                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        String fileName =
//                new SimpleDateFormat("yyyyMMddHHmmss", Locale.CANADA).format(new Date()) + "_";
//        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        try {
//            tempFile = File.createTempFile(fileName, ".jpg", file);
//            if (!tempFile.exists()) {
//                tempFile.mkdir();
//            }
//            if (Build.VERSION.SDK_INT >= 24) {
//                mOutPutUri = FileProvider.getUriForFile(getBaseContext(), AppConfig.FILE_PROVIDER, tempFile);
//            } else {
//                mOutPutUri = Uri.fromFile(tempFile);
//            }
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutUri);
//            startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 图片查询
//     */
//
//    private void PicAsyncTask() {
//
//        @SuppressLint("StaticFieldLeak") AsyncTask asyncTask = new AsyncTask() {
//            ImageHandleBean bean = new ImageHandleBean();
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                Tools.showDialog(MainActivity.this);
//            }
//
//            @Override
//            protected void onPostExecute(Object o) {
//                super.onPostExecute(o);
//                Tools.closeDialog();
//            }
//
//            @Override
//            protected Object doInBackground(Object[] params) {
//                try {
//                    Uri uri;
//                    Bitmap bitmap = compressImage(tempFile.getPath(), 1280, 720);
//                    String fileName = tempFile.getPath().substring(tempFile.getPath().lastIndexOf("/") + 1);
//                    File PHOTO_DIR =
//                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);//设置保存路径
//
//                    File avaterFile = new File(PHOTO_DIR, "new" + fileName);//设置文件名称
//
//                    if (avaterFile.exists()) {
//                        avaterFile.delete();
//                    }
//                    try {
//                        avaterFile.createNewFile();
//                        FileOutputStream fos = new FileOutputStream(avaterFile);
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
//                        fos.flush();
//                        fos.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    uri = Uri.fromFile(avaterFile);
//                    mUploadMessage5.onReceiveValue(new Uri[]{uri});
//                    return bean;
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    return false;
//                }
//            }
//        };
//        asyncTask.execute();
//    }
//
//    /**
//     * 尺寸压缩
//     *
//     * @param width  目标宽
//     * @param height 目标高
//     */
//    public static Bitmap compressImage(String srcPath, float width, float height) {
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        newOpts.inJustDecodeBounds = true;
//        Bitmap bitmap;
//
//        newOpts.inJustDecodeBounds = false;
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 1;// be=1表示不缩放
//        if (w > h && w > width) {// 如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / width);
//        } else if (w < h && h > height) {// 如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / height);
//        }
//        if (be <= 0) be = 1;
//        newOpts.inSampleSize = be;// 设置缩放比例
//        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;// 该模式是默认的,可不设
//        newOpts.inPurgeable = true;// 同时设置才会有效
//        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收
//        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//        return bitmap;
//    }
//
//
//    /**
//     * JS调用
//     */
//    public class JavaScriptInterface {
//        @JavascriptInterface
//
//        public void scanQrCode() {
//            rxPermissions.requestEach(Manifest.permission.CAMERA).subscribe(new Action1<Permission>() {
//                @Override
//                public void call(Permission permission) {
//                    if (permission.granted) {//允许
//                        Intent intent = new Intent(MainActivity.this, MipcaActivityCapture.class);
//                        startActivityForResult(intent, GET_RESULT_REQUEST);
//                    } else {
//                        Toast.makeText(MainActivity.this, "相机未授权", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//        }
//
//        @JavascriptInterface
//        public void openPhoto(String id) {
//            chooseid = id;
//            rxPermissions.request(
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//            ).subscribe(new Action1<Boolean>() {
//                @Override
//                public void call(Boolean aBoolean) {
//                    if (aBoolean) {
//                        Intent intent = new Intent(Intent.ACTION_PICK, null);
//                        // 如要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
//                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                        startActivityForResult(intent, REQUEST_CHOOSE_CODE);
//                    } else {
//                        Toast.makeText(MainActivity.this, "读取文件未授权", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//
//        @JavascriptInterface
//        public void openCamera(String id) {
//            photoid = id;
//            rxPermissions.requestEach(
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//            ).subscribe(new Action1<Permission>() {
//                @Override
//                public void call(Permission permission) {
//                    if (permission.granted && Manifest.permission.CAMERA.equals(permission.name)) {//允许
//                        takePhoto();
//                    } else {
//                        Toast.makeText(MainActivity.this, "相机未授权", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
//
//    /**
//     * 执行拍照前，应该先判断SD卡是否存在
//     */
//    private void fileDir() {
//
//        String SDState = Environment.getExternalStorageState();
//        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
//            sdPath = AppConfig.OPEN_USER_PIC_FOLDER;
//            File dir = new File(sdPath);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//        }
//    }
//
//
//    /**
//     * 拍照获取图片
//     */
//    private void takePhoto() {
//        //执行拍照前，应该先判断SD卡是否存在
//        String SDState = Environment.getExternalStorageState();
//        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
//            Intent intent =
//                    new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            photoPath = sdPath + System.currentTimeMillis() + ".jpg";
//            File mOutputFile = new File(sdPath, System.currentTimeMillis() + ".jpg");
//            if (Build.VERSION.SDK_INT >= 24) {
//                mOutputUri = FileProvider
//                        .getUriForFile(MainActivity.this, AppConfig.FILE_PROVIDER,
//                                mOutputFile);
//            } else {
//                mOutputUri = Uri.fromFile(mOutputFile);
//            }
//            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mOutputUri);
//            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
//        } else {
//            Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case REQUEST_CODE_IMAGE_CAPTURE:
//                    if (mUploadMessage5 != null) {
//                        PicAsyncTask();
//                    }
//                    break;
//                case REQUEST_CODE_PICK_IMAGE:
//                    try {
//                        final Uri result = (data == null) ? null : data.getData();
//                        if (result != null && mUploadMessage5 != null) {
//                            mUploadMessage5.onReceiveValue(new Uri[]{result});
//                        } else {
//                            mUploadMessage5.onReceiveValue(new Uri[]{});
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case GET_RESULT_REQUEST:
//                    String result = data.getStringExtra("result");
//                    mWebView.loadUrl("javascript:setScanResult('" + result + "')");
//                    break;
//                case REQUEST_CHOOSE_CODE:
//                    String[] proj = {MediaStore.Images.Media.DATA};
//                    // 获取选中图片的路径
//                    Cursor cursor = getContentResolver().query(data.getData(),
//                            proj, null, null, null);
//                    if (cursor.moveToFirst()) {
//                        int column_index = cursor
//                                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                        choosePath = cursor.getString(column_index);
//                        if (choosePath == null) {
//                            choosePath = Utils.getPath(getApplicationContext(),
//                                    data.getData());
//                        }
//                    }
//                    cursor.close();
//                    sendChoosePictureData();
//                    break;
//                case REQUEST_TAKE_PHOTO:
//                    if (mOutputUri == null && null != data && data.getExtras() != null) {
//                        Bundle bundle = data.getExtras();
//                        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
//                        if (data.getData() != null) {
//                            mOutputUri = data.getData();
//                        } else {
//                            mOutputUri = Uri.parse(
//                                    MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
//                        }
//                        if (mOutputUri != null) {
//                            sendPhotoPictureData();
//                        } else {
//                            return;
//                        }
//                    } else {
//                        if (mOutputUri != null) {
//                            sendPhotoPictureData();
//                        } else {
//                            Toast.makeText(this, "获取图片失败！", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    break;
//            }
//        } else {
//            if (mUploadMessage5 != null) {
//                mUploadMessage5.onReceiveValue(null);
//                mUploadMessage5 = null;
//            }
//            return;
//        }
//    }
//
//    /**
//     * 上送图片base64数据
//     */
//    private void sendChoosePictureData() {
//        Bitmap bitmap = BitmapUtil.compressImgResize(choosePath, DES_PIC_WIDTH, DES_PIC_HEIGHT);// 压缩尺寸
//        String mPhotoPath = AppConfig.OPEN_USER_PIC_FOLDER + "compress_choose_pic.jpg";
//        BitmapUtil.compressBmpToFile(bitmap, DES_PIC_MAX_SIZE, mPhotoPath);// 压缩质量
//        Bitmap photoBitMap = BitmapFactory.decodeFile(mPhotoPath);
//
//        String base64Bitmap = Base64BitmapUtil.bitmapToBase64(photoBitMap);
//
//        if (!TextUtils.isEmpty(base64Bitmap)) {
//            base64Bitmap = "data:image/jpeg;base64," + base64Bitmap;
//            mWebView.loadUrl("javascript:albumResutl('" + base64Bitmap + "','" + chooseid + "')");
//        } else {
//            Toast.makeText(this, "图片数据为空", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * 上送图片base64数据
//     */
//    private void sendPhotoPictureData() {
//        Bitmap bitmap = BitmapUtil.compressImgResize(photoPath, DES_PIC_WIDTH, DES_PIC_HEIGHT);// 压缩尺寸
//        String mPhotoPath = AppConfig.OPEN_USER_PIC_FOLDER + "compress_photo_pic.jpg";
//        BitmapUtil.compressBmpToFile(bitmap, DES_PIC_MAX_SIZE, mPhotoPath);// 压缩质量
//        Bitmap photoBitMap = BitmapFactory.decodeFile(mPhotoPath);
//        String base64Bitmap = Base64BitmapUtil.bitmapToBase64(photoBitMap);
//
//        if (!TextUtils.isEmpty(base64Bitmap)) {
//            base64Bitmap = "data:image/jpeg;base64," + base64Bitmap;
//            mWebView.loadUrl("javascript:photographResutl('" + base64Bitmap + "','" + photoid + "')");
//        } else {
//            Toast.makeText(this, "图片数据为空", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    // Web视图
//    private class MyWebViewClient extends WebViewClient implements DownloadListener {
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onLoadResource(WebView view, String url) {
//            super.onLoadResource(view, url);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//        }
//
//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//            return super.shouldInterceptRequest(view, url);
//        }
//
//        @Override
//        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//
//        }
//    }
//
//    /**
//     * 屏蔽返回键
//     *
//     * @author
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWebView.canGoBack()) {
//                mWebView.goBack();
//            } else {
//                MainActivity.this.finish();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void clearCache() {
//        //清空所有
//        CookieSyncManager.createInstance(getApplicationContext());
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.removeAllCookie();
//        CookieSyncManager.getInstance().sync();
//        mWebView.setWebChromeClient(null);
//        mWebView.setWebViewClient(null);
//        mWebView.getSettings().setJavaScriptEnabled(false);
//        mWebView.clearCache(true);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        clearCache();
//    }
//}
