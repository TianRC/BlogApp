package com.trc.shift.base;

/**
 * Created by trc on 2018/4/30.
 */

public class BaseModel  <IP> {
    protected IP mIPresenter;

    public BaseModel(IP iPresenter) {
        this.mIPresenter = iPresenter;
    }

    }
