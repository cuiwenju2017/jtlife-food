package com.hz.mymeishi.bean;

/**
 * 消息详情
 */
public class TheOrderDetailsBean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"sourceId":"1131031839033659392","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635974 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38","video":"","readTime":"2019-05-22 10:59:38","createDate":"2019-05-22 10:59:38","id":"1131031839964794881"}
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
         * sourceId : 1131031839033659392
         * title : 订单提交
         * content : 您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635974 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38
         * video :
         * readTime : 2019-05-22 10:59:38
         * createDate : 2019-05-22 10:59:38
         * id : 1131031839964794881
         */

        private String sourceId;
        private String title;
        private String content;
        private String video;
        private String readTime;
        private String createDate;
        private String id;

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getReadTime() {
            return readTime;
        }

        public void setReadTime(String readTime) {
            this.readTime = readTime;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
