package com.yangzheandroid.rxmvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yangzheandroid.retrofitutils.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.view.activity.customview.MoneyScaleActivity;
import com.yangzheandroid.rxmvp.view.activity.dialog.DialogListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bt_base_dialog)
    Button mBtBaseDialog;
    @BindView(R.id.bt_custom_view)
    Button mBtCustomView;
    private Unbinder mUnbinder;

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected void initPresenter() {

    }


    @OnClick({R.id.bt_base_dialog, R.id.bt_custom_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_base_dialog:
                startActivity(new Intent(MainActivity.this, DialogListActivity.class));
                break;
            case R.id.bt_custom_view:
                startActivity(new Intent(MainActivity.this, MoneyScaleActivity.class));
                break;

        }
    }
}
