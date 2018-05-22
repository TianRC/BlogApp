package com.trc.shift.base;

import java.lang.ref.WeakReference;

/**
 * Created by trc on 2018/4/29.
 */

public class BasePresenter <V extends IBaseView> implements IBasePresenter {
    private WeakReference actReference;
    protected V iView;
    @Override
    public void attachView(IBaseView iView) {
        actReference = new WeakReference(iView);
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }
}