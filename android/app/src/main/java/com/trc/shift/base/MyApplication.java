package com.trc.shift.base;

import android.app.Application;

/**
 * Created by trc on 2018/4/29.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

}
