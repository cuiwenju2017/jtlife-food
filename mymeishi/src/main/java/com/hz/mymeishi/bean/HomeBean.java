package com.hz.mymeishi.bean;

public class HomeBean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"verifycode":""}
     */

    private String status;
    private String errorCode;
    private String errorMsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * verifycode :
         */

        private String verifycode;

        public String getVerifycode() {
            return verifycode;
        }

        public void setVerifycode(String verifycode) {
            this.verifycode = verifycode;
        }
    }
}
