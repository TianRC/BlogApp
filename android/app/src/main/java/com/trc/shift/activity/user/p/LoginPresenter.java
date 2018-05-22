package com.trc.shift.activity.user.p;

import android.app.Activity;

import com.trc.shift.resultBean.LoginBean;
import com.trc.shift.activity.user.m.LoginModel;
import com.trc.shift.activity.user.v.ILoginView;
import com.trc.shift.base.IBasePresenter;

/**
 * Created by trc on 2018/4/30.
 */

public class LoginPresenter implements IBasePresenter<ILoginView>, ILoginPresenter {
    private ILoginView view;

    private LoginModel model;

    public LoginPresenter(ILoginView view) {
        attachView(view);
        model = new LoginModel(this);
    }

    @Override
    public void attachView(ILoginView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void showData(LoginBean list) {
        view.loginSuccess(list);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void checkData(String msg) {
        view.checkData(msg);
    }


    @Override
    public void reLogin(String name, String passWord, Activity activity) {
        model.login(name, passWord ,activity);
    }


}
