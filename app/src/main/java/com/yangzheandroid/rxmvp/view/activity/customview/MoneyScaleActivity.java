package com.yangzheandroid.rxmvp.view.activity.customview;

import android.widget.SeekBar;
import android.widget.TextView;

import com.yangzheandroid.retrofitutils.base.BaseActivity;
import com.yangzheandroid.rxmvp.R;
import com.yangzheandroid.rxmvp.widget.custom.PanelView;
import com.yangzheandroid.rxmvp.widget.custom.ScaleMoney;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoneyScaleActivity extends BaseActivity implements ScaleMoney.MoveScaleInterface {


    @BindView(R.id.tv_value)
    TextView mTvValue;
    @BindView(R.id.sm_view)
    ScaleMoney mSmView;
    @BindView(R.id.panelview)
    PanelView mPanelview;
    @BindView(R.id.seek_control)
    SeekBar mSeekControl;
    private Unbinder mUnbinder;

    @Override
    protected void initVarlible() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_money_scale);
        mUnbinder = ButterKnife.bind(this);
        mSmView.setMoveScaleInterface(this);

        mSeekControl.setMax(100);
        mSeekControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPanelview.setPercent(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
