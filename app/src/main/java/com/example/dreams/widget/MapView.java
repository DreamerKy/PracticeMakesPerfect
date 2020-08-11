package com.example.dreams.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.dreams.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

/**
 * Created by likaiyu on 2019/11/4.
 */
public class MapView extends View {

    private List<ProviceItem> itemList;
    private Context context;
    private int[] colorArray = new int[]{0xFF239BD7, 0xFF30A9E5, 0xFF80CBF1, 0xFFFFFFFF};
    private Paint mPaint;
    private ProviceItem selectedItem;
    private RectF totleRect;
    private float scale = 1.0f;
    private Handler handler;

    public MapView(Context context) {
        this(context, null);
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        itemList = new ArrayList<>();
        loadThread.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (totleRect != null) {
            double mapWidth = totleRect.width();
            double mapHeight = totleRect.height();
            scale = (float) Math.min((width / mapWidth), (height / mapHeight));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (itemList != null) {
            canvas.save();
            canvas.scale(scale, scale);
            for (ProviceItem proviceItem : itemList) {
                if (proviceItem == selectedItem) {
                    selectedItem.drawItem(canvas, mPaint, true);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "位置+" + selectedItem.getPosition() + " " + selectedItem.getName(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    proviceItem.drawItem(canvas, mPaint, false);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handleTouch(event.getX(), event.getY());
        return super.onTouchEvent(event);
    }

    private void handleTouch(float x, float y) {
        if (itemList == null) {
            return;
        }

        ProviceItem setlectedItem = null;
        for (ProviceItem proviceItem : itemList) {
            if (proviceItem.isTouch(x / scale, y / scale)) {
                setlectedItem = proviceItem;
            }
        }

        if (setlectedItem != null) {
            selectedItem = setlectedItem;
            postInvalidate();
        }
    }

    private Thread loadThread = new Thread(new Runnable() {
        @SuppressLint("RestrictedApi")
        @Override
        public void run() {
            //Dom解析
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.china);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;
                builder = factory.newDocumentBuilder();
                Document document = builder.parse(inputStream);
                Element rootElement = document.getDocumentElement();
                NodeList paths = rootElement.getElementsByTagName("path");
                float left = -1;
                float right = -1;
                float top = -1;
                float bottom = -1;

                for (int i = 0; i < paths.getLength(); i++) {
                    Element item = (Element) paths.item(i);
                    String pathData = item.getAttribute("android:pathData");
                    String name = item.getAttribute("android:name");
                    Path pathFromPathData = PathParser.createPathFromPathData(pathData);
                    ProviceItem proviceItem = new ProviceItem(pathFromPathData);
                    proviceItem.setDrawColor(colorArray[i % 4]);
                    proviceItem.setPosition(i);
                    proviceItem.setName(TextUtils.isEmpty(name) ? "没找到哦~" : name);
                    RectF rect = new RectF();
                    pathFromPathData.computeBounds(rect, true);
                    left = left == -1 ? rect.left : Math.min(left, rect.left);
                    right = right == -1 ? rect.right : Math.max(right, rect.right);
                    top = top == -1 ? rect.top : Math.min(top, rect.top);
                    bottom = bottom == -1 ? rect.bottom : Math.max(bottom, rect.bottom);
                    itemList.add(proviceItem);
                }
                totleRect = new RectF(left, top, right, bottom);
                handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestLayout();
                        invalidate();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });


}
