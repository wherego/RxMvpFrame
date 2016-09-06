package com.yangzheandroid.rxmvp.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.yangzheandroid.retrofitutils.utils.MyUtils;
import com.yangzheandroid.rxmvp.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Author：杨哲丶 on 2016/9/4 21:13
 * Contact: studylifetime@sina.com
 */
public class MyView extends View {

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private Paint mPaint;
    private Rect mBound;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);//注意一下，这里是this，不是默认的super
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);//初始化自定属性

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = randomText();
                //android中实现view的更新有两组方法，一组是invalidate，另一组是postInvalidate，
                // 其中前者是在UI线程自身中使用，而后者在非UI线程中使用。
                postInvalidate();
            }
        });
    }

    /**
     * 随机获得四个数字的方法
     * @return
     */
    private String randomText() {
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();
        while (set.size() < 4) {
            set.add(random.nextInt(10));
        }

        StringBuffer sb = new StringBuffer();
        for (Integer integer : set) {
            sb.append(integer + "");
        }

        return sb.toString();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyView);
        //默认的属性值都是用下划线分割的，官方规定。
        mTitleText = a.getString(R.styleable.MyView_titleText1);
        mTitleTextColor = a.getColor(R.styleable.MyView_titleTextColor1, Color.BLACK);
        //我这里用的我自己的工具类，就是为了适配而已，给初学者普及一下...
        mTitleTextSize = a.getDimensionPixelSize(R.styleable.MyView_titleTextSize1, MyUtils.dp2px(getContext(), 16));
        a.recycle();//记得释放

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        //获取文本的宽和高
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getSize(widthMeasureSpec, true);
        int height = getSize(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    /**
     * 获取高度和宽度
     *
     * @param measureSpec
     * @param type  true 宽度，false 高度
     * @return
     */
    private int getSize(int measureSpec, boolean type) {
        int resultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            resultSize = size;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            resultSize = type ? getPaddingLeft() + getPaddingRight() + mBound.width()
                    : getPaddingTop() + getPaddingBottom() + mBound.height();
        }
        return resultSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#CCA4E3"));
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);

    }


}
