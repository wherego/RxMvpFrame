package com.yangzheandroid.rxmvp.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yangzheandroid.retrofitutils.utils.LogUtil;
import com.yangzheandroid.retrofitutils.utils.MyUtils;
import com.yangzheandroid.rxmvp.R;

/**
 * Author：杨哲丶 on 2016/9/7 22:53
 * Contact: studylifetime@sina.com
 */
public class PanelView extends View {

    private static final String TAG = PanelView.class.getSimpleName();
    private int mWidth;
    private int mHeight;

    private int mPercent = 50;

    //刻度宽度
    private float mTikeWidth;

    //第二个弧的宽度
    private int mScendArcWidth;

    //最小圆的半径
    private int mMinCircleRadius;

    //文字矩形的宽
    private int mRectWidth;

    //文字矩形的高
    private int mRectHeight;

    //文字内容
    private String mText = "";

    //文字的大小
    private int mTextSize;

    //设置文字颜色
    private int mTextColor;
    private int mArcColor;

    //小圆和指针颜色
    private int mMinCircleColor;

    //刻度的个数
    private int mTikeCount;

    private Context mContext;

    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.PanelView);
        mArcColor = a.getColor(R.styleable.PanelView_arcColor, Color.parseColor("#5FB1ED"));
        mMinCircleColor = a.getColor(R.styleable.PanelView_pointerColor, Color.parseColor("#C9DEEE"));
        mTikeCount = a.getInt(R.styleable.PanelView_tikeCount, 12);
        mTextSize = a.getDimensionPixelSize(MyUtils.sp2px(mContext, R.styleable.PanelView_android_textSize), 20);
        mText = a.getString(R.styleable.PanelView_android_text);
        mScendArcWidth = 50;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = MyUtils.dp2px(mContext, 200);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = MyUtils.dp2px(mContext, 200);
        }

        LogUtil.e(TAG, "mWidth:  " + mWidth);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制最外边的圆弧
        Paint p = new Paint();
        int strokeWidth = 3;
        p.setStrokeWidth(strokeWidth);//设置线的宽度
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(mArcColor);
        //如果想让圆弧在View里，需要减去画笔宽度的一半，因为半径是从圆心到画笔宽度的中间算的
        canvas.drawArc(new RectF(strokeWidth, strokeWidth, mWidth - strokeWidth,
                mHeight - strokeWidth), 145, 250, false, p);


        //我们赋值在构造函数里赋值 mScendArcWidth = 50;
        p.setStrokeWidth(mScendArcWidth);
        //内圆的矩形区域，每一个圆弧都是依据矩形绘制的，所以首先要确定矩形的宽高和位置。
        RectF secondRectF = new RectF(strokeWidth + 50, strokeWidth + 50,
                mWidth - strokeWidth - 50, mHeight - strokeWidth - 50);
        float secondRectWidth = mWidth - strokeWidth - 50 - (strokeWidth + 50);//内圆的矩形宽度
        float secondRectHeight = mHeight - strokeWidth - 50 - (strokeWidth + 50);//内圆的矩形高度


        //mPercent是变量，可以随着它的变化而变化弧度做准备
        float percent = mPercent / 100f;
        //充满的圆弧的度数    -5是大小弧的偏差
        float fill = 250 * percent;
        //空的圆弧的度数
        float empty = 250 - fill;
        //如果percent为0，切换画笔颜色
        if (percent == 0) {
            p.setColor(Color.WHITE);
        }


        //第一步，画粗弧突出部分左端
        canvas.drawArc(secondRectF, 135, 11, false, p);
        //第二步，画粗弧的充满部分
        canvas.drawArc(secondRectF, 145, fill, false, p);
        p.setColor(Color.WHITE);
        //第三步，画弧胡的未充满部分
        canvas.drawArc(secondRectF, 145 + fill, empty, false, p);
        //第四步，画粗弧突出部分右端,如果mPercent=100,才开始设置为一样的颜色
        if (percent == 1) {
            p.setColor(mArcColor);
        }
        canvas.drawArc(secondRectF, 144 + fill + empty, 10, false, p);


/***********************************绘制内圆*************************************/

        p.setColor(mArcColor);
        //绘制小圆外圈
        p.setStrokeWidth(3);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 30, p);
        //绘制小圆内圈
        p.setColor(mMinCircleColor);
        p.setStrokeWidth(8);
        mMinCircleRadius = 15;
        canvas.drawCircle(mWidth / 2, mHeight / 2, mMinCircleRadius, p);
        //绘制最底部矩形
        p.setStyle(Paint.Style.FILL);
        p.setColor(mArcColor);
        mRectWidth = 60;
        mRectHeight = 25;
        //文字矩形的最底部坐标
        float rectBottomY = mHeight / 2 + secondRectHeight / 3 + mRectHeight;
        canvas.drawRect(mWidth / 2 - mRectWidth / 2, mHeight / 2 + secondRectHeight / 3,
                mWidth / 2 + mRectWidth / 2, rectBottomY, p);
        p.setTextSize(mTextSize);
        mTextColor = Color.WHITE;
        p.setColor(mTextColor);
        float txtLength = p.measureText(mText);
        canvas.drawText(mText, (mWidth - txtLength) / 2, rectBottomY + 45, p);


        //绘制刻度
        p.setColor(mArcColor);
        //绘制第一条最上面的刻度
        mTikeWidth = 20;
        p.setStrokeWidth(3);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mTikeWidth, p);

        //旋转的角度
        float rAngle = 250f / mTikeCount;

        //通过旋转画布 绘制右面的刻度
        for (int i = 0; i < mTikeCount / 2; i++) {
            canvas.rotate(rAngle, mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 2, 0, mWidth / 2, mTikeWidth, p);
        }
        //现在需要将将画布旋转回来
        canvas.rotate(-rAngle * mTikeCount / 2, mWidth / 2, mHeight / 2);

        //通过旋转画布 绘制左面的刻度
        for (int i = 0; i < mTikeCount / 2; i++) {
            canvas.rotate(-rAngle, mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 2, 0, mWidth / 2, mTikeWidth, p);
        }
        //现在需要将将画布旋转回来
        canvas.rotate(rAngle * mTikeCount / 2, mWidth / 2, mHeight / 2);


        //绘制指针
        p.setColor(mMinCircleColor);
        p.setStrokeWidth(4);

        //按照百分比绘制刻度
        canvas.rotate((250 * percent - 250 / 2), mWidth / 2, mHeight / 2);

        canvas.drawLine(mWidth / 2, (mHeight / 2 - secondRectHeight / 2)
                + mScendArcWidth / 2 + 2, mWidth / 2, mHeight / 2 - mMinCircleRadius, p);

        //将画布旋转回来
        canvas.rotate(-(250 * percent - 250 / 2), mWidth / 2, mHeight / 2);



        super.onDraw(canvas);
    }


    /**
     * 设置百分比
     *
     * @param percent
     */
    public void setPercent(int percent) {
        mPercent = percent;
        invalidate();
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        mText = text;
        invalidate();
    }

    /**
     * 设置圆弧颜色
     *
     * @param color
     */

    public void setArcColor(int color) {
        mArcColor = color;

        invalidate();
    }


    /**
     * 设置指针颜色
     *
     * @param color
     */
    public void setPointerColor(int color) {
        mMinCircleColor = color;

        invalidate();
    }

    /**
     * 设置文字大小
     *
     * @param size
     */
    public void setTextSize(int size) {
        mTextSize = size;

        invalidate();
    }

    /**
     * 设置粗弧的宽度
     *
     * @param width
     */
    public void setArcWidth(int width) {
        mScendArcWidth = width;

        invalidate();
    }
}
