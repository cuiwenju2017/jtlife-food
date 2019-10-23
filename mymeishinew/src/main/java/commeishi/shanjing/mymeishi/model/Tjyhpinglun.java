package commeishi.shanjing.mymeishi.model;

public class Tjyhpinglun {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"isAdd":1}
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
         * isAdd : 1
         */

        private int isAdd;

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }
    }
}
