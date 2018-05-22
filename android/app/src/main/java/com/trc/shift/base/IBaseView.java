package com.trc.shift.base;

/**
 * Created by trc on 2018/4/29.
 */

public interface IBaseView {
    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    void checkData(String failMsg);
    /** 无数据 */
    void showEmpty();
}
