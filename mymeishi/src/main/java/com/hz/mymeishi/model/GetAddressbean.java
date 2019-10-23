package com.hz.mymeishi.model;

public class GetAddressbean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"memberId":1130170916874096640,"sysAreaId":"350106","sysAreaName":"浙江省,杭州市,西湖区","addressDetail":"东日晴好6号楼3-101","zipCode":"","receiveName":"ASONG","receiveTel":"13093765908","defaultFlag":"1","id":"1130755442851581952"}
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
         * memberId : 1130170916874096640
         * sysAreaId : 350106
         * sysAreaName : 浙江省,杭州市,西湖区
         * addressDetail : 东日晴好6号楼3-101
         * zipCode :
         * receiveName : ASONG
         * receiveTel : 13093765908
         * defaultFlag : 1
         * id : 1130755442851581952
         */

        private long memberId;
        private String sysAreaId;
        private String sysAreaName;
        private String addressDetail;
        private String zipCode;
        private String receiveName;
        private String receiveTel;
        private String defaultFlag;
        private String id;

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public String getSysAreaId() {
            return sysAreaId;
        }

        public void setSysAreaId(String sysAreaId) {
            this.sysAreaId = sysAreaId;
        }

        public String getSysAreaName() {
            return sysAreaName;
        }

        public void setSysAreaName(String sysAreaName) {
            this.sysAreaName = sysAreaName;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getReceiveTel() {
            return receiveTel;
        }

        public void setReceiveTel(String receiveTel) {
            this.receiveTel = receiveTel;
        }

        public String getDefaultFlag() {
            return defaultFlag;
        }

        public void setDefaultFlag(String defaultFlag) {
            this.defaultFlag = defaultFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
