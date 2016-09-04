package com.yangzheandroid.rxmvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.utils.ToastUtils;

/**
 * 封装好的Dialog
 */
public class BottomDialog extends Dialog {

    private View mDialogView;

    private ImageView mUserHead;
    private AnimationSet mAnimIn, mAnimOut;

    public BottomDialog(Context context) {
        this(context, R.style.base_dialog);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, R.style.base_dialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAnimIn = getInAnimation(getContext());
        mAnimOut = getOutAnimation(getContext());
        initAnimListener();

        setContentView(R.layout.activity_wode_zuhe_bottom_dialog);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        //设置宽度撑满屏幕
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        mDialogView = findViewById(R.id.wode_zuhe_bottom_dialog_root);

        TextView zhiding = (TextView) mDialogView.findViewById(R.id.txt_dialog_zuhe_zhiding);
        TextView delete = (TextView) mDialogView.findViewById(R.id.txt_dialog_zuhe_delete);

        zhiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("置顶");
                dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("删除");
                dismiss();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startWithAnimation(true);
    }

    @Override
    public void dismiss() {
        dismissWithAnimation(true);
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
                        BottomDialog.super.dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {
            }
        });
    }

    private AnimationSet getInAnimation(Context context) {
        AnimationSet in = new AnimationSet(context, null);


        TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF,

                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
        translate.setDuration(500);

        in.addAnimation(translate);


        return in;
    }

    private AnimationSet getOutAnimation(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,

                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        translate.setDuration(500);

        out.addAnimation(translate);
        return out;
    }


}