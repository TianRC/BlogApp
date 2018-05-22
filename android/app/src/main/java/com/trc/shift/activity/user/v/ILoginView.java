package com.trc.shift.activity.user.v;

import com.trc.shift.resultBean.LoginBean;
import com.trc.shift.base.IBaseView;

/**
 * Created by trc on 2018/4/29.
 */

public interface ILoginView  extends IBaseView{
    void loginSuccess(LoginBean str);
}
