package com.hz.meishi.bean;

public class BaseBean<T>  {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"url":"index","isNew":"0","access_Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VySWQiOjExMjU3MjQyMDI5MjY3Mzk0NTYsIlVzZXJDb2RlIjoiMTc2MzM1NDAyNjUiLCJVc2VyTmFtZSI6IiIsIk1wT3BlbklEIjoiIn0.Va2DQRYgmTa9fFmsxvHgMOi_hX1f3vikT0wJEqMv1po","userId":"1125724202926739456","userCode":"17633540265","videoId":"9","mpOpenId":""}
     */

    private String status;
    private String errorCode;
    private String errorMsg;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
