package com.example.dreams.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by likaiyu on 2020/3/26.
 */
public class FlowLayout extends ViewGroup {
    //记录每一行行高用于layout()
    private ArrayList<Integer> heightsList;
    //记录所有的行，用于layout()
    private ArrayList<ArrayList<View>> lineViewsList;
    private ArrayList<View> viewList;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //行高记录
        heightsList = new ArrayList<>();
        //每行的views
        lineViewsList = new ArrayList<>();
        //单行信息容器
        viewList = new ArrayList<>();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightsList.clear();
        lineViewsList.clear();
        viewList.clear();
        //获取父view的size和测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //初始化当前控件宽高
        int measureWidth = 0;
        int measureHeight = 0;
        //当前行宽高
        int currentLineWidth = 0;
        int currentLineHeight = 0;

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
            measureHeight = heightSize;
        } else {
            //当前View宽高
            int currentChildWidth;
            int currentChildHeight;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                //view实际测量宽高
                currentChildWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                currentChildHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                //是否需要换行
                if (currentLineWidth + currentChildWidth > widthSize) {
                    //记录当前行信息
                    measureWidth = Math.max(measureWidth, currentLineWidth);
                    measureHeight += currentLineHeight;
                    heightsList.add(currentLineHeight);
                    lineViewsList.add(viewList);

                    currentLineWidth = currentChildWidth;
                    currentLineHeight = currentChildHeight;
                    viewList = new ArrayList<>();
                    viewList.add(childAt);
                } else {
                    //不换行
                    currentLineWidth += currentChildWidth;
                    currentLineHeight = Math.max(currentLineHeight, currentChildHeight);
                    viewList.add(childAt);
                }

                //如果是最后一行，需要换行
                if (i == childCount - 1) {
                    measureWidth = Math.max(measureWidth, currentChildWidth);
                    measureHeight += currentLineHeight;
                    lineViewsList.add(viewList);
                    heightsList.add(currentLineHeight);
                }
            }
        }

        setMeasuredDimension(measureWidth, measureHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //开始布局
        int left, top, right, bottom;
        int currentTop = 0;
        int currentLeft = 0;

        int lineCount = lineViewsList.size();
        for (int i = 0; i < lineCount; i++) {
            ArrayList<View> views = lineViewsList.get(i);
            int lineViewsSize = views.size();
            for (int j = 0; j < lineViewsSize; j++) {
                View childView = views.get(j);
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();
                left = currentLeft + layoutParams.leftMargin;
                top = currentTop + layoutParams.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
                childView.layout(left, top, right, bottom);
                currentLeft += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            currentLeft = 0;
            currentTop += heightsList.get(i);
        }
        lineViewsList.clear();
        heightsList.clear();

    }
}
