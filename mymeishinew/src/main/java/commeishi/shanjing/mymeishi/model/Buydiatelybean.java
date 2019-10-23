package commeishi.shanjing.mymeishi.model;

import com.google.gson.annotations.SerializedName;

public class Buydiatelybean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"msg":"","paydata":{"appid":"wx2e4397d00834a087","partnerid":"1517446181","prepayid":"wx17173956919102b234520e271021872400","package":"prepay_id=wx17173956919102b234520e271021872400","noncestr":"Sf8UMiTEciOkjan9","timestamp":"1560764397","sign":"9348F7D807DF272269FFCA3F4FF38D28"}}
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
         * paydata : {"appid":"wx2e4397d00834a087","partnerid":"1517446181","prepayid":"wx17173956919102b234520e271021872400","package":"prepay_id=wx17173956919102b234520e271021872400","noncestr":"Sf8UMiTEciOkjan9","timestamp":"1560764397","sign":"9348F7D807DF272269FFCA3F4FF38D28"}
         */

        private String msg;
        private PaydataBean paydata;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public PaydataBean getPaydata() {
            return paydata;
        }

        public void setPaydata(PaydataBean paydata) {
            this.paydata = paydata;
        }

        public static class PaydataBean {
            /**
             * appid : wx2e4397d00834a087
             * partnerid : 1517446181
             * prepayid : wx17173956919102b234520e271021872400
             * package : prepay_id=wx17173956919102b234520e271021872400
             * noncestr : Sf8UMiTEciOkjan9
             * timestamp : 1560764397
             * sign : 9348F7D807DF272269FFCA3F4FF38D28
             */

            private String appid;
            private String partnerid;
            private String prepayid;
            @SerializedName("package")
            private String packageX;
            private String noncestr;
            private String timestamp;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
