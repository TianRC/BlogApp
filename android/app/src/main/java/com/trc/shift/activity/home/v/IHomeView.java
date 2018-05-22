package com.trc.shift.activity.home.v;

import com.trc.shift.base.IBaseView;
import com.trc.shift.resultBean.LoginBean;

/**
 * Created by trc on 2018/5/3.
 */

public interface IHomeView extends IBaseView {
    void loginSuccess(LoginBean str);
}