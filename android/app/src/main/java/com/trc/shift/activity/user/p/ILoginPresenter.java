package com.trc.shift.activity.user.p;

import android.app.Activity;

import com.trc.shift.resultBean.LoginBean;

/**
 * Created by trc on 2018/4/29.
 */

public interface ILoginPresenter {
    /** 显示数据 */
    void showData(LoginBean list);

    /** 提示无数据 */
    void showEmpty();

    /**数据检测提示*/
    void checkData(String msg);
    /**
     * 登陆请求
     */
    void reLogin(String phone, String passWord, Activity activity);
}