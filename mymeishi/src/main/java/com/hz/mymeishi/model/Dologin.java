package com.hz.mymeishi.model;

public class Dologin {


    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"url":"登录成功回调跳转地址(非必传)","isNew":"0","access_Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VySWQiOjExNDcwOTAwNDI3NTQzMDYwNDgsIlVzZXJDb2RlIjoiMTc2MDA2MTA1MjQifQ.NRqHx7gy18qA_w-bX83CmkjpR-yrBHjiV20-urM9sPw","userId":"1147090042754306048","userCode":"17600610524","nickName":"骚猪","mpOpenId":""}
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
         * url : 登录成功回调跳转地址(非必传)
         * isNew : 0
         * access_Token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VySWQiOjExNDcwOTAwNDI3NTQzMDYwNDgsIlVzZXJDb2RlIjoiMTc2MDA2MTA1MjQifQ.NRqHx7gy18qA_w-bX83CmkjpR-yrBHjiV20-urM9sPw
         * userId : 1147090042754306048
         * userCode : 17600610524
         * nickName : 骚猪
         * mpOpenId :
         */

        private String url;
        private String isNew;
        private String access_Token;
        private String userId;
        private String userCode;
        private String nickName;
        private String mpOpenId;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIsNew() {
            return isNew;
        }

        public void setIsNew(String isNew) {
            this.isNew = isNew;
        }

        public String getAccess_Token() {
            return access_Token;
        }

        public void setAccess_Token(String access_Token) {
            this.access_Token = access_Token;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getMpOpenId() {
            return mpOpenId;
        }

        public void setMpOpenId(String mpOpenId) {
            this.mpOpenId = mpOpenId;
        }
    }
}
