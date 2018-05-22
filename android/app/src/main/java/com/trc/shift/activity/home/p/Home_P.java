package com.trc.shift.activity.home.p;

import android.app.Activity;

import com.trc.shift.activity.home.m.HomeModel;
import com.trc.shift.activity.home.v.IHomeView;
import com.trc.shift.base.IBasePresenter;
import com.trc.shift.resultBean.LoginBean;

/**
 * Created by trc on 2018/5/3.
 */

public class Home_P implements IBasePresenter<IHomeView>, IHome_P {
    private IHomeView view;
    private HomeModel mModel;

    public Home_P(IHomeView view) {
        this.view = view;
        mModel = new HomeModel(this);
    }

    @Override
    public void attachView(IHomeView view) {

    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void showData(LoginBean list) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void checkData(String msg) {

    }

    @Override
    public void reLogin(String phone, String passWord, Activity activity) {

    }
}
