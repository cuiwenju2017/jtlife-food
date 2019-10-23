package com.hz.meishi.bean;

public class RegisterBean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"url":"index","isNew":"0","access_Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJVc2VySWQiOjExMjU3MjQyMDI5MjY3Mzk0NTYsIlVzZXJDb2RlIjoiMTc2MzM1NDAyNjUiLCJVc2VyTmFtZSI6IiIsIk1wT3BlbklEIjoiIn0.Va2DQRYgmTa9fFmsxvHgMOi_hX1f3vikT0wJEqMv1po","userId":"1125724202926739456","userCode":"17633540265","videoId":"9","mpOpenId":""}
     */

        private String url;
        private String isNew;
        private String access_Token;
        private String userId;
        private String userCode;
        private String videoId;
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

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getMpOpenId() {
            return mpOpenId;
        }

        public void setMpOpenId(String mpOpenId) {
            this.mpOpenId = mpOpenId;
        }
}
