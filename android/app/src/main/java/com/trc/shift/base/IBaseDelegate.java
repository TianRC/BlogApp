package com.trc.shift.base;

import android.support.annotation.NonNull;

/**
 * Created by trc on 2018/4/29.
 */

interface IBaseDelegate<V extends IBaseView, P extends IBasePresenter<V>> {

    /**
     * 初始化presenter
     */
    @NonNull
    P createPresenter();

    /**
     * 获取presenter
     */
    @NonNull
    P getPresenter();
    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();


    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();

}
