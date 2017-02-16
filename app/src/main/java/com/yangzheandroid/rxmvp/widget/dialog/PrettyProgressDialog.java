package com.yangzheandroid.rxmvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.yangzheandroid.retrofitutils.utils.MyUtils;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.widget.custom.ProgressLoading;


/**
 * Created by Jalen on 16/12/16.
 */

public class PrettyProgressDialog extends Dialog {
    private Context mContext;
    private TextView mShowText;
    private String mDefaultText = "默认拼命加载中..";
    private ProgressLoading mProgressLoading;

    public PrettyProgressDialog(Context context) {
        super(context, R.style.progress_dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pretty_dialog);
        setCancelable(true);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);
        mShowText = (TextView) findViewById(R.id.id_tv_loadingmsg);
        mProgressLoading = (ProgressLoading) findViewById(R.id.progressLoading);
        mProgressLoading.spin();
        mProgressLoading.setBarWidth(MyUtils.dp2px(mContext, 3));
        mShowText.setText(mDefaultText);
    }

    public void setText(String text) {
       mDefaultText=text;
    }

    public void setText(int text) {
       mDefaultText=mContext.getResources().getString(text);
    }
}
