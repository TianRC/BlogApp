package com.trc.shift.activity.user.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.trc.shift.R;
import com.trc.shift.activity.home.ui.HomeActivity;
import com.trc.shift.activity.user.p.LoginPresenter;
import com.trc.shift.activity.user.v.ILoginView;
import com.trc.shift.base.BaseMVPActivity;
import com.trc.shift.base.BasePresenter;
import com.trc.shift.config.Constant;
import com.trc.shift.resultBean.LoginBean;
import com.trc.shift.unit.MyToast;
import com.trc.shift.unit.SPUtil;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;


/**
 * Created by trc on 2018/4/29.
 */

public class LoginActivity extends BaseMVPActivity<ILoginView, LoginPresenter> implements ILoginView {


    private Button button;
    private EditText et_userName, et_psw;
    private ViewGroup viewGroup;

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void checkData(String failMsg) {
        MyToast.makeError(viewGroup, failMsg);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    public void initData() {
        button.setOnClickListener(v -> {
            boolean flag = false;
            if (isEtBlank(et_userName, "请输入用户名")) return;
            if (isEtBlank(et_psw, "请输入正确的密码")) return;
            mPresenter.reLogin(
                    et_userName.getText().toString()
                    , et_psw.getText().toString(), this);
        });
    }

    @Override
    public void initView() {
        viewGroup = (ViewGroup) findViewById(android.R.id.content).getRootView();
        button = (Button) findViewById(R.id.btn_login);
        et_userName = (EditText) findViewById(R.id.input_user);
        et_psw = (EditText) findViewById(R.id.input_psw);
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        SPUtil.put(this, Constant.TOKEN, bean.getToken());
        TSnackbar.make(viewGroup,
                "登录成功",
                TSnackbar.LENGTH_SHORT,
                TSnackbar.APPEAR_FROM_TOP_TO_DOWN).setPromptThemBackground(Prompt.SUCCESS).show();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    public boolean isEtBlank(EditText editText, String errorText) {
        if (TextUtils.isEmpty(editText
                .getText().toString().trim())) {
            MyToast.makeError(viewGroup, errorText);
            return true;
        }
        return false;
    }
}
