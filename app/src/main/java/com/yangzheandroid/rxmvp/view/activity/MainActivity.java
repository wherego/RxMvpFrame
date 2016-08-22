package com.yangzheandroid.rxmvp.view.activity;

import android.widget.Button;
import android.widget.ImageView;

import com.yangzheandroid.retrofitutils.base.BaseActivity;
import com.yangzheandroid.retrofitutils.image.ImageLoader;
import com.yangzheandroid.rxmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_main)
    ImageView mIvMain;
    @BindView(R.id.bt_load)
    Button mBtLoad;
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


    @OnClick(R.id.bt_load)
    public void onClick() {

        ImageLoader imageLoader = new ImageLoader();
//        imageLoader.setImageCache(new MemoryCache(this));
        imageLoader.dispayImage("http://img5.imgtn.bdimg.com/it/u=2917608665,2198585488&fm=11&gp=0.jpg", mIvMain);

    }
}
