package com.yangzheandroid.rxmvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.utils.ToastUtils;

/**
 * 封装好的Dialog
 */
public class SelectDialog extends Dialog {

    private View mDialogView;

    private ImageView mUserHead;
    private AnimationSet mAnimIn, mAnimOut;

    public SelectDialog(Context context) {
        this(context, R.style.base_dialog);
    }

    public SelectDialog(Context context, int themeResId) {
        super(context, R.style.base_dialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAnimIn = getInAnimation(getContext());
        mAnimOut = getOutAnimation(getContext());
        initAnimListener();

        setContentView(R.layout.activity_wode_zuhe_select_dialog);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        mDialogView = findViewById(R.id.wode_zuhe_select_dialog_root);

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
                        SelectDialog.super.dismiss();
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