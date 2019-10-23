package commeishi.shanjing.mymeishi.model;

public class Settlementbean {


    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"msg":"","paydata":""}
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
         * msg :
         * paydata :
         */

        private String msg;
        private String paydata;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getPaydata() {
            return paydata;
        }

        public void setPaydata(String paydata) {
            this.paydata = paydata;
        }
    }
}
