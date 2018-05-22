package com.trc.shift.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by trc on 2018/5/4.
 * <p>
 * <p>
 * <p>
 * public abstract class BaseMVPActivity<V extends IBaseView, P extends IBasePresenter<V>> extends
 * BaseActivity implements IBaseDelegate<V, P> {
 */

public abstract class BaseMvpFragment<V extends IBaseView, P extends IBasePresenter<V>>
        extends BaseFragment implements IBaseDelegate<V, P> {
    protected P mPresenter;

    @Override
    public void onStart() {
        super.onStart();
    }

    @NonNull
    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        mPresenter = createPresenter();
        initView();
        initData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();
}
