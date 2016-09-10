package com.yangzheandroid.rxmvp.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.yangzheandroid.retrofitutils.utils.MyUtils;
import com.yangzheandroid.rxmvp.R;

/**
 * Author：杨哲丶 on 2016/9/6 22:24
 * Contact: studylifetime@sina.com
 */
public class WaveView extends View {


    private int mCount;
    private int mWidth;
    private int mDefaultWidth;
    private int mColor;
    private int mMode;
    private float mRectWidth;
    private int mHeight;
    private int mDefaultHeight;
    private float mRectHeight;
    private Paint mPaint;
    private float mWaveHeight;
    private Context mContext;
    private float mWaveWidth;
    private static final int MODE_TRIANGLE = 1;
    private static final int MODE_Circle = 2;

    public WaveView(Context context) {
        this(context, null);
    }
    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attr) {
        TypedArray array = mContext.obtainStyledAttributes(attr, R.styleable.WaveView);
        mCount = array.getInt(R.styleable.WaveView_waveCount, 10);
        mDefaultWidth = array.getInt(R.styleable.WaveView_waveWidth, 200);
        mDefaultHeight = array.getInt(R.styleable.WaveView_waveHeight,200);
        mColor = array.getColor(R.styleable.WaveView_android_color, Color.parseColor("#CCA4E3"));
        mMode = array.getInteger(R.styleable.WaveView_mode, MODE_TRIANGLE);//如果取不到默认为三角形、
        array.recycle();

        mPaint = new Paint();
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //矩形宽度为view的80%
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
            mRectWidth = (float) (mWidth * 0.8);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //mDefaultWidth 为你自定义设置的属性
            mWidth = MyUtils.dp2px(mContext, mDefaultWidth);
            mRectWidth = (float) (mWidth * 0.8);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = (float) (mHeight * 0.8);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //mDefaultHeight 为你自定义设置的属性
            mHeight = MyUtils.dp2px(mContext, mDefaultHeight);
            mRectHeight = (float) (mHeight * 0.8);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算每个三角形的高
        mWaveHeight = mRectHeight / mCount;
        mWaveWidth = mWaveHeight / 2;//因为我们画的是正三角形
        //绘制矩形  计算padding
        float lrpadding = ((mWidth - mRectWidth) / 2);//左右paddding
        float tbpadding = ((mHeight - mRectHeight) / 2);//上下paddding

        canvas.drawRect(lrpadding, tbpadding, mRectWidth + lrpadding, mRectHeight + tbpadding, mPaint);

        if (mMode == MODE_TRIANGLE) {
            //绘制右边的波浪
            float startX = lrpadding + mRectWidth;
            float startY = tbpadding;
            Path path = new Path();

            path.moveTo(startX, startY);

            for (int i = 0; i < mCount; i++) {
                path.lineTo(startX + mWaveWidth, startY + i * mWaveHeight + mWaveWidth);
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path, mPaint);

            //绘制左边的波浪
            startX = lrpadding;
            startY = tbpadding;
            path.moveTo(startX, startY);
            for (int i = 0; i < mCount; i++) {
                path.lineTo(startX - mWaveWidth, startY + i * mWaveHeight + mWaveWidth);
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }

            path.close();
            canvas.drawPath(path, mPaint);

        } else if (mMode == MODE_Circle) {
            //绘制右边的圆
            float startX = lrpadding + mRectWidth;
            float startY = tbpadding;

            for (int i = 0; i < mCount; i++) {
                canvas.drawCircle(startX, startY + i * mWaveHeight + mWaveWidth, mWaveWidth, mPaint);
            }

            //绘制右边的圆
            startX = lrpadding;

            for (int i = 0; i < mCount; i++) {
                canvas.drawCircle(startX, startY + i * mWaveHeight + mWaveWidth, mWaveWidth, mPaint);
            }

        }
    }
}
