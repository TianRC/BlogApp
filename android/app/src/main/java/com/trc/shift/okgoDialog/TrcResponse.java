package com.trc.shift.okgoDialog;

import java.io.Serializable;

/**
 * Created by trc on 2018/5/2.
 */

public class TrcResponse<T> implements Serializable {
    private static final long serialVersionUID = 5213230387175987834L;

    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "TrcResponse{\n" +//
                "\tcode=" + code + "\n" +//
                "\tmsg='" + msg + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }
}
