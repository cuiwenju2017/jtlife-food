package com.hz.mymeishi.model;

import java.util.List;

public class Wlgslistbean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : [{"expressID":8,"ename":"EMS","expressCode":"EMS","estate":1,"ecode":"ems","eletter":"E","eorder":2,"eurl":"http://www.ems.com.cn"},{"expressID":28,"ename":"申通快递","expressCode":"STO","estate":1,"ecode":"shentong","eletter":"S","eorder":2,"eurl":"http://www.sto.cn"},{"expressID":29,"ename":"顺丰快递","expressCode":"SF","estate":1,"ecode":"shunfeng","eletter":"S","eorder":1,"eurl":"http://www.sf-express.com"},{"expressID":32,"ename":"天天快递","expressCode":"HHTT","estate":1,"ecode":"tiantian","eletter":"T","eorder":2,"eurl":"http://www.ttkdex.com"},{"expressID":40,"ename":"圆通快递","expressCode":"YTO","estate":1,"ecode":"yuantong","eletter":"Y","eorder":1,"eurl":"http://www.yto.net.cn"},{"expressID":41,"ename":"韵达快递","expressCode":"YD","estate":1,"ecode":"yunda","eletter":"Y","eorder":1,"eurl":"http://www.yundaex.com"},{"expressID":44,"ename":"中通快递","expressCode":"ZTO","estate":1,"ecode":"zhongtong","eletter":"Z","eorder":1,"eurl":"http://www.zto.cn"},{"expressID":46,"ename":"宅急送","expressCode":"ZJS","estate":1,"ecode":"zhaijisong","eletter":"Z","eorder":2,"eurl":"http://www.zjs.com.cn"},{"expressID":48,"ename":"京东快递","expressCode":"JD","estate":1,"ecode":"jingdongwuliu","eletter":"J","eorder":1,"eurl":"http://www.jdwl.com/"}]
     */

    private String status;
    private String errorCode;
    private String errorMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * expressID : 8
         * ename : EMS
         * expressCode : EMS
         * estate : 1
         * ecode : ems
         * eletter : E
         * eorder : 2
         * eurl : http://www.ems.com.cn
         */

        private int expressID;
        private String ename;
        private String expressCode;
        private int estate;
        private String ecode;
        private String eletter;
        private int eorder;
        private String eurl;

        public int getExpressID() {
            return expressID;
        }

        public void setExpressID(int expressID) {
            this.expressID = expressID;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getExpressCode() {
            return expressCode;
        }

        public void setExpressCode(String expressCode) {
            this.expressCode = expressCode;
        }

        public int getEstate() {
            return estate;
        }

        public void setEstate(int estate) {
            this.estate = estate;
        }

        public String getEcode() {
            return ecode;
        }

        public void setEcode(String ecode) {
            this.ecode = ecode;
        }

        public String getEletter() {
            return eletter;
        }

        public void setEletter(String eletter) {
            this.eletter = eletter;
        }

        public int getEorder() {
            return eorder;
        }

        public void setEorder(int eorder) {
            this.eorder = eorder;
        }

        public String getEurl() {
            return eurl;
        }

        public void setEurl(String eurl) {
            this.eurl = eurl;
        }
    }
}
