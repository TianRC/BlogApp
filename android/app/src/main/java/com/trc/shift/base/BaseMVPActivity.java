package com.trc.shift.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.gyf.barlibrary.ImmersionBar;
import com.trc.shift.R;

/**
 * Created by trc on 2018/4/29.
 */

public abstract class BaseMVPActivity<V extends IBaseView, P extends IBasePresenter<V>> extends
        BaseActivity implements IBaseDelegate<V, P> {

    protected P mPresenter;
    public ImmersionBar mImmersionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        setContentView(getLayoutId());
        initView();
        initData();
        mImmersionBar = ImmersionBar
                .with(this)
                .barColor(R.color.colorPrimary);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @NonNull
    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }

    public abstract int getLayoutId();

    public abstract void initData();

    public abstract void initView();
}
