package com.yangzheandroid.rxmvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.utils.DeviceUtil;

/**
 * 自定义Dialog
 */
public class HintDialog extends Dialog implements View.OnClickListener {

    private ViewGroup mRootView;

    private View mLine, mDialogView;

    private TextView mTitle, mMsg, mLeftBtn, mRightBtn;

    private AnimationSet mAnimIn, mAnimOut;

    private int mBackgroundColor, mTitleTextColor, mMsgTextColor;

    private OnLeftListener mOnLeftListener;

    private OnRightListener mOnRightListener;

    private CharSequence mTitleText, mMsgText, mLeftText, mRightText;

    private boolean mIsShowAnim;

    private boolean mLeftisGone = false;
    private boolean mRightisGone = false;

    private int mLiftColor;
    private int mRightColor;

    public HintDialog(Context context) {
        this(context, R.style.base_dialog);
        init();
    }

    public HintDialog(Context context, int theme) {
        super(context, R.style.base_dialog);
        init();
    }

    private void init() {
        mAnimIn = getInAnimation(getContext());
        mAnimOut = getOutAnimation(getContext());
        initAnimListener();
    }

    @Override
    public void show() {
        super.show();
        getWindow().setLayout(DeviceUtil.dp2px(getContext(), 300), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitleText = title;
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getContext().getText(titleId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = View.inflate(getContext(), R.layout.base_dialog, null);
        setContentView(contentView);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);

        mRootView = (ViewGroup) contentView.findViewById(R.id.rl_root);

        mTitle = (TextView) contentView.findViewById(R.id.tv_title);
        mMsg = (TextView) contentView.findViewById(R.id.tv_msg);

        mLeftBtn = (TextView) contentView.findViewById(R.id.tv_btn_left);
        mRightBtn = (TextView) contentView.findViewById(R.id.tv_btn_right);

        mLine = contentView.findViewById(R.id.vi_line);

        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);

        mTitle.setText(mTitleText);
        mMsg.setText(mMsgText);
        mLeftBtn.setText(mLeftText);
        mRightBtn.setText(mRightText);

        if (0 != mLiftColor) {
            mLeftBtn.setTextColor(mLiftColor);
        }

        if (0 != mRightColor) {
            mRightBtn.setTextColor(mRightColor);
        }


        if (null == mMsgText || mMsgText.equals("")) {
            mMsg.setVisibility(View.GONE);
        }

        if (null == mOnLeftListener && null != mOnRightListener) {
            mLeftBtn.setVisibility(View.GONE);
            mLine.setVisibility(View.GONE);
            mRightBtn.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.base_selector_dialog_btn_normal));
        } else if (null != mOnLeftListener && null == mOnRightListener) {
            mRightBtn.setVisibility(View.GONE);
            mLine.setVisibility(View.GONE);
            mLeftBtn.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.base_selector_dialog_btn_normal));
        }

        setTextColor();
        setBackgroundColor();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startWithAnimation(mIsShowAnim);
    }

    @Override
    public void dismiss() {
        dismissWithAnimation(mIsShowAnim);
    }

    private void startWithAnimation(boolean showInAnimation) {
        if (showInAnimation) {
            mDialogView.startAnimation(mAnimIn);
        }
    }

    private void dismissWithAnimation(boolean showOutAnimation) {
        if (showOutAnimation) {
            mDialogView.startAnimation(mAnimOut);
        } else {
            super.dismiss();
        }
    }

    private void initAnimListener() {
        mAnimOut.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {
            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        HintDialog.super.dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {
            }
        });
    }

    private void setBackgroundColor() {

        if (0 == mBackgroundColor) return;

        int radius = DeviceUtil.dp2px(getContext(), 6);
        float[] outerRadii = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape roundRectShape = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(mBackgroundColor);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        mRootView.setBackgroundDrawable(shapeDrawable);
    }

    private void setTextColor() {

        if (0 != mTitleTextColor) {
            mTitle.setTextColor(mTitleTextColor);
        }

        if (0 != mMsgTextColor) {
            mMsg.setTextColor(mMsgTextColor);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (R.id.tv_btn_left == id) {
            mOnLeftListener.onClick(this);
        } else if (R.id.tv_btn_right == id) {
            mOnRightListener.onClick(this);
        }
    }

    /**
     * 是否显示动画
     */
    public HintDialog setAnimationEnable(boolean enable) {
        mIsShowAnim = enable;
        return this;
    }

    /**
     * 设置进场动画
     */
    public HintDialog setAnimationIn(AnimationSet animIn) {
        mAnimIn = animIn;
        return this;
    }

    /**
     * 设置离场动画
     */
    public HintDialog setAnimationOut(AnimationSet animOut) {
        mAnimOut = animOut;
        initAnimListener();
        return this;
    }

    /**
     * 设置背景颜色
     */
    public HintDialog setBackgroundColor(int color) {
        mBackgroundColor = color;
        return this;
    }

    /**
     * 设置背景颜色
     */
    public HintDialog setBackgroundColor(String color) {
        try {
            setBackgroundColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置标题颜色
     */
    public HintDialog setTitleTextColor(int color) {
        mTitleTextColor = color;
        return this;
    }

    /**
     * 设置标题颜色
     */
    public HintDialog setTitleTextColor(String color) {
        try {
            setTitleTextColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void setSingleText(String text, int color) {
        mLiftColor = color;
        mLeftText = text;
        mLeftisGone = false;
        mOnRightListener = null;
    }

    /**
     * 设置提示信息颜色
     */
    public HintDialog setMsgTextColor(int color) {
        mMsgTextColor = color;
        return this;
    }

    /**
     * 设置提示信息颜色
     */
    public HintDialog setMsgTextColor(String color) {
        try {
            setMsgTextColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置左侧按钮监听
     */
    public HintDialog setLeftListener(CharSequence text, OnLeftListener l) {
        mLeftText = text;
        mOnLeftListener = l;
        return this;
    }

    /**
     * 设置左侧按钮监听
     */
    public HintDialog setLeftListener(CharSequence text, int liftColor, OnLeftListener l) {
        mLiftColor = liftColor;
        mLeftText = text;
        mOnLeftListener = l;
        return this;
    }

    /**
     * 设置左侧按钮监听
     */
    public HintDialog setLeftListener(int textId, OnLeftListener l) {
        return setLeftListener(getContext().getText(textId), l);
    }

    /**
     * 设置右侧按钮监听
     */
    public HintDialog setRightListener(CharSequence text, OnRightListener l) {
        mRightText = text;
        mOnRightListener = l;
        return this;
    }

    /**
     * 设置右侧按钮监听
     */
    public HintDialog setRightListener(CharSequence text, int color, OnRightListener l) {
        mRightColor = color;
        mRightText = text;
        mOnRightListener = l;
        return this;
    }

    /**
     * 设置右侧按钮监听
     */
    public HintDialog setRightListener(int textId, OnRightListener l) {
        return setRightListener(getContext().getText(textId), l);
    }

    /**
     * 设置提示信息
     */
    public HintDialog setMsgText(CharSequence text) {
        mMsgText = text;
        return this;
    }

    /**
     * 设置提示信息
     */
    public HintDialog setMsgText(int textId) {
        return setMsgText(getContext().getText(textId));
    }

    /**
     * 获取提示信息
     */
    public CharSequence getMsgText() {
        return mMsgText;
    }

    /**
     * 获取标题信息
     */
    public CharSequence getTitleText() {
        return mTitleText;
    }

    /**
     * 获取左侧按钮信息
     */
    public CharSequence getLeftText() {
        return mLeftText;
    }

    /**
     * 获取右侧按钮信息
     */
    public CharSequence getRightText() {
        return mRightText;
    }

    /**
     * 隐藏提示信息
     */
    public void setMsgTextVisibility(int visibility) {
        mMsg.setVisibility(visibility);
    }

    /**
     * 左侧按钮监听
     */
    public interface OnLeftListener {
        void onClick(HintDialog dialog);
    }

    public void setGoneMsgText() {
        mMsg.setVisibility(View.GONE);
    }


    /**
     * 右侧按钮监听
     */
    public interface OnRightListener {
        void onClick(HintDialog dialog);
    }


    private AnimationSet getInAnimation(Context context) {
        AnimationSet in = new AnimationSet(context, null);

        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(90);

        ScaleAnimation scale1 = new ScaleAnimation(0.8f, 1.05f, 0.8f, 1.05f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        scale1.setDuration(135);

        ScaleAnimation scale2 = new ScaleAnimation(1.05f, 0.95f, 1.05f, 0.95f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        scale2.setDuration(105);
        scale2.setStartOffset(135);

        ScaleAnimation scale3 = new ScaleAnimation(0.95f, 1f, 0.95f, 1.0f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        scale3.setDuration(60);
        scale3.setStartOffset(240);

        in.addAnimation(alpha);
        in.addAnimation(scale1);
        in.addAnimation(scale2);
        in.addAnimation(scale3);

        return in;
    }

    private AnimationSet getOutAnimation(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }
}