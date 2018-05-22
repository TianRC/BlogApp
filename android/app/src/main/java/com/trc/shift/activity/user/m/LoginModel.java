package com.trc.shift.activity.user.m;

import android.app.Activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.trc.shift.activity.user.p.ILoginPresenter;
import com.trc.shift.base.BaseModel;
import com.trc.shift.config.ApiUrl;
import com.trc.shift.okgoDialog.DialogCallback;
import com.trc.shift.okgoDialog.TrcResponse;
import com.trc.shift.resultBean.LoginBean;

/**
 * Created by trc on 2018/4/30.
 */

public class LoginModel extends BaseModel<ILoginPresenter> {
    public LoginModel(ILoginPresenter iPresenter) {
        super(iPresenter);
    }

    public void login(String name, String passWord, Activity activity) {
        OkGo.<TrcResponse<LoginBean>>post(ApiUrl.LOGIN)
                .tag(this)
                .params("username", name)
                .params("psw", passWord)
                .execute(new DialogCallback<TrcResponse<LoginBean>>(activity) {
                    @Override
                    public void onSuccess(Response<TrcResponse<LoginBean>> response) {
                        if (response.body().code != 200) {
                            mIPresenter.checkData(response.body().msg);
                        } else {
                            mIPresenter.showData(response.body().data);
                        }
                    }
                });
    }

}
