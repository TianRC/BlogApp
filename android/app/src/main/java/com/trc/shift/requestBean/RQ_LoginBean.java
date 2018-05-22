package com.trc.shift.requestBean;

/**
 * Created by trc on 2018/5/2.
 */

public class RQ_LoginBean {
    private String userName,psw;

    public RQ_LoginBean(String userName, String psw) {
        this.userName = userName;
        this.psw = psw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
