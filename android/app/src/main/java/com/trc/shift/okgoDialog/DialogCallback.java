package com.trc.shift.okgoDialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.trc.shift.base.MyApplication;

/**
 * Created by trc on 2018/5/2.
 */

public class DialogCallback<T> extends JsonCallback<T> {

    private ProgressDialog dialog;

    private void initDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
    }

    public DialogCallback(Activity activity) {
        super();
        initDialog(activity);
    }

    public DialogCallback() {
        super();
        dialog = null;
//        initDialog(MyApplication.getInstance());
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
        Log.d("DialogCallback", "————————————————");
    }

    @Override
    public void onSuccess(Response<T> response) {
        Gson gson = new Gson();
        String json = gson.toJson(response);
        Log.d("DialogCallback", json);
    }


    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Log.d("DialogCallback", "====");
    }
}
