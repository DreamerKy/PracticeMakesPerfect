package com.example.dreams.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.example.dreams.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by likaiyu on 2019/11/4.
 */
public class MyRecyclerView extends ViewGroup {
    private List<View> viewList;
    private int touchSlop;
    private boolean needRelayout;
    private Adapter adapter;
    private Recycler recycler;
    private int scrollY;
    private int firstRow;
    private int width;
    private int height;
    private int[] heights;//item  高度
    //行数
    private int rowCount;
    private int currentY;

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            recycler = new Recycler(adapter.getViewTypeCount());
            scrollY = 0;
            firstRow = 0;
            needRelayout = true;
            requestLayout();
        }
    }

    private void init(Context context, AttributeSet attrs) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        this.touchSlop = configuration.getScaledDoubleTapSlop();
        this.viewList = new ArrayList<>();
        this.needRelayout = true;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int h = 0;
        if (adapter != null) {
            this.rowCount = adapter.getCount();
            heights = new int[rowCount];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = adapter.getHeight(i);
            }
        }
        int tmpHeight = sumArray(heights, 0, heights.length);
        h = Math.min(heightSize, tmpHeight);
        setMeasuredDimension(widthSize, h);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int sumArray(int[] heights, int firstIndex, int count) {
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += heights[i];
        }
        return sum;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needRelayout || changed) {
            needRelayout = false;
            viewList.clear();
            removeAllViews();
            if (adapter != null) {
                width = r - l;
                height = b - t;
                int left, top = 0, right, bottom;
                for (int i = 0; i < rowCount && top < height; i++) {
                    right = width;
                    bottom = top + heights[i];
                    View view = makeAndStep(i, 0, top, width, bottom);
                    viewList.add(view);
                    //循环摆放
                    top = bottom;
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentY = (int) event.getY();
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                int y2 = (int) Math.abs(currentY - event.getRawY());
                if (y2 > touchSlop) {
                    intercept = true;
                }
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                int y2 = (int) event.getRawY();
                int diffY = currentY - y2;
                scrollBy(0, diffY);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void scrollBy(int x, int y) {
        scrollY += y;
        scrollY = scrollBounds(scrollY);
    }

    private int scrollBounds(int scrollY) {
        if (scrollY > 0) {
            //上滑
        } else {
            scrollY = Math.max(scrollY, -sumArray(heights, 0, firstRow));
        }
        return scrollY;
    }

    private View makeAndStep(int row, int left, int top, int right, int bottom) {
        View view = obtainView(row, right - left, bottom - top);
        view.layout(left, top, right, bottom);
        return view;
    }

    private View obtainView(int row, int width, int height) {
        int itemType = adapter.getItemViewType(row);
        View recycleView = recycler.get(itemType);
        View view = null;
        if (recycleView == null) {
            view = adapter.onCreateViewHolder(row, recycleView, this);
            if (view == null) {
                throw new RuntimeException("onCreatViewHolder must set layout");
            }
        } else {
            view = adapter.onBinderViewHolder(row, recycleView, this);
        }
        view.setTag(R.id.tag_type_view, itemType);
        view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        addView(view, 0);
        return view;
    }

    interface Adapter {
        View onCreateViewHolder(int position, View convertView, ViewGroup parent);

        View onBinderViewHolder(int position, View convertView, ViewGroup parent);

        int getItemViewType(int row);

        int getViewTypeCount();

        int getCount();

        int getHeight(int index);
    }

    class Recycler {
        private Stack<View>[] views;

        public Recycler(int typeNumber) {
            views = new Stack[typeNumber];
            for (int i = 0; i < typeNumber; i++) {
                views[i] = new Stack<>();
            }
        }

        public void put(View view, int type) {
            views[type].push(view);
        }

        public View get(int type) {
            try {
                return views[type].pop();
            } catch (Exception e) {
                return null;
            }
        }

    }


}
