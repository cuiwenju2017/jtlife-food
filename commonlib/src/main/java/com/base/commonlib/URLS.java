package com.base.commonlib;


public class URLS {
    public static final String HTTP = "https://1.3ypt.com/SYWeb/";

    public static String Firstlogin(String telenumber, String password) {
        String getyz = HTTP + "user_login?phone=" + telenumber + "&password=" + password;
        return getyz;
    }

    public static final String Hostqq = "http://47.111.13.4:5000/api/v1";
    public static final String Host = "http://47.111.13.4:5000/api/v1/";

}
