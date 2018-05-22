package com.trc.shift.resultBean;

import com.google.gson.Gson;

/**
 * Created by trc on 2018/4/29.
 */

public class LoginBean {


    /**
     * token : 199203195113
     * user_name : trc
     * psw : trc123456
     * authorPic : ww.baidu.com
     * sex : 0
     */

    private String token;
    private String userName;
    private String psw;
    private String authorPic;
    private int sex;

    public static LoginBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginBean.class);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getAuthorPic() {
        return authorPic;
    }

    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
