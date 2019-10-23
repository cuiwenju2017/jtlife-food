package com.hz.mymeishi.bean;

import java.util.List;

public class MessageBean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : [{"notifyType":"1","notifyType_name":"交易物流","notifyType_icon":"http://imgs.weekfan.com/main/wuliu@2x.png","nums":0,"content":"您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635974 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38","createDate":"10:59"},{"notifyType":"2","notifyType_name":"账户通知","notifyType_icon":"http://imgs.weekfan.com/main/zhanghu@2x.png","nums":0,"content":""},{"notifyType":"3","notifyType_name":"会员权益消息","notifyType_icon":"http://imgs.weekfan.com/main/huiyuan@2x.png","nums":0,"content":""},{"notifyType":"4","notifyType_name":"兼职代理薪金消息","notifyType_icon":"http://imgs.weekfan.com/main/fenyong@2x.png","nums":0,"content":""}]
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
         * notifyType : 1
         * notifyType_name : 交易物流
         * notifyType_icon : http://imgs.weekfan.com/main/wuliu@2x.png
         * nums : 0
         * content : 您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635974 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38
         * createDate : 10:59
         */

        private String notifyType;
        private String notifyType_name;
        private String notifyType_icon;
        private int nums;
        private String content;
        private String createDate;

        public String getNotifyType() {
            return notifyType;
        }

        public void setNotifyType(String notifyType) {
            this.notifyType = notifyType;
        }

        public String getNotifyType_name() {
            return notifyType_name;
        }

        public void setNotifyType_name(String notifyType_name) {
            this.notifyType_name = notifyType_name;
        }

        public String getNotifyType_icon() {
            return notifyType_icon;
        }

        public void setNotifyType_icon(String notifyType_icon) {
            this.notifyType_icon = notifyType_icon;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
