package com.trc.shift.base;

/**
 * Created by trc on 2018/4/29.
 */

public interface IBasePresenter<V extends IBaseView> {

    /**
     * @param view 绑定
     */
    void attachView(V view);


    /**
     * 防止内存的泄漏,清楚presenter与activity之间的绑定
     */
    void detachView();


}