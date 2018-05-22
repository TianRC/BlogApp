package com.trc.shift.okgoDialog;

import java.io.Serializable;

/**
 * Created by trc on 2018/5/2.
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public TrcResponse toLzyResponse() {
        TrcResponse lzyResponse = new TrcResponse();
        lzyResponse.code = code;
        lzyResponse.msg = msg;
        return lzyResponse;
    }
}