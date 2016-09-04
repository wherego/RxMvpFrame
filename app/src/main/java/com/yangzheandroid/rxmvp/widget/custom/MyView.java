package com.yangzheandroid.rxmvp.widget.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：Jalen on 2016/9/4 21:13
 * Contact: studylifetime@sina.com
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMySize(150, heightMeasureSpec);
        int width = getMySize(150, widthMeasureSpec);

        if (height > width) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    /**
     * 获取宽度高度
     * @param defaultSize
     * @param measureSpec
     * @return
     */
    private int getMySize(int defaultSize, int measureSpec) {

        int resultSize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED://没有约束大小,就设置默认大小
                resultSize = defaultSize;
                break;
            case MeasureSpec.AT_MOST://wrap_content 取最大的值
                resultSize = Math.max(size, defaultSize);
                break;
            case MeasureSpec.EXACTLY://精确测量
                resultSize = size;
                break;

        }

        return resultSize;

    }


}
