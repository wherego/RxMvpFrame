package com.yangzheandroid.rxmvp.view.activity.customview;

import android.widget.TextView;

import com.yangzheandroid.retrofitutils.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.widget.custom.ScaleMoney;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoneyScaleActivity extends BaseActivity implements ScaleMoney.MoveScaleInterface {


    @BindView(R.id.tv_value)
    TextView mTvValue;
    @BindView(R.id.sm_view)
    ScaleMoney mSmView;
    private Unbinder mUnbinder;

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_money_scale);
        mUnbinder = ButterKnife.bind(this);
        mSmView.setMoveScaleInterface(this);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void getValue(int value) {
        mTvValue.setText(value + "");
    }
}
