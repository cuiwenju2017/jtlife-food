package com.hz.mymeishi.bean;

import java.util.List;

public class MessageNotificationBean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"list":[{"sourceId":"1131031839033659392","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635974 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38","video":"","readTime":"2019-05-22 10:59:38","createDate":"2019-05-22 10:59:38","id":"1131031839964794881"},{"sourceId":"1131031839033659393","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635344 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38","video":"","readTime":"2019-05-22 10:59:38","createDate":"2019-05-22 10:59:38","id":"1131031839964794880"},{"sourceId":"1131015823088029696","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 162.00 <br>订单号: 20190522095600139775 <br>商品详情:  <br>&nbsp;&nbsp;ISHIZAWA LABS日本石泽研究所毛穴抚子大米化精华面霜/30g 标准规格 ×1<br>&nbsp;&nbsp;肌美精3D面膜（粉色保湿款） 标准规格 ×1<br>提交时间: 2019-05-22 09:56:00","video":"","readTime":"2019-05-22 09:56:00","createDate":"2019-05-22 09:56:00","id":"1131015823272579072"},{"sourceId":"1130950084767387648","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 1255.00 <br>订单号: 20190522053446902505 <br>商品详情:  <br>&nbsp;&nbsp;Mandom曼丹 Bifesta缤若诗 保湿卸妆湿巾浸润型 46枚 标准规格 ×1<br>&nbsp;&nbsp;SK-II 青春露 护肤精华露（神仙水）230毫升 标准规格 ×1<br>提交时间: 2019-05-22 05:34:47","video":"","readTime":"2019-05-22 05:34:47","createDate":"2019-05-22 05:34:47","id":"1130950085404921856"},{"sourceId":"0","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 70.00 <br>订单号: 20190522045926452308 <br>商品详情:  <br>&nbsp;&nbsp;大麦若叶  ×1<br>提交时间: 2019-05-22 04:59:26","video":"","readTime":"2019-05-22 04:59:26","createDate":"2019-05-22 04:59:26","id":"1130941191500730368"},{"sourceId":"1130755608388177920","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 51.00 <br>订单号: 20190521164200120615 <br>商品详情:  <br>&nbsp;&nbsp;脂流茶  ×1<br>提交时间: 2019-05-21 16:42:00","video":"","readTime":"2019-05-21 16:42:00","createDate":"2019-05-21 16:42:00","id":"1130755609344479232"}],"totalCount":6,"pageNo":1,"pageSize":10,"last":1}
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
         * list : [{"sourceId":"1131031839033659392","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635974 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38","video":"","readTime":"2019-05-22 10:59:38","createDate":"2019-05-22 10:59:38","id":"1131031839964794881"},{"sourceId":"1131031839033659393","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 126.00 <br>订单号: 20190522105938635344 <br>商品详情:  <br>&nbsp;&nbsp;SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克 标准规格 ×2<br>提交时间: 2019-05-22 10:59:38","video":"","readTime":"2019-05-22 10:59:38","createDate":"2019-05-22 10:59:38","id":"1131031839964794880"},{"sourceId":"1131015823088029696","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 162.00 <br>订单号: 20190522095600139775 <br>商品详情:  <br>&nbsp;&nbsp;ISHIZAWA LABS日本石泽研究所毛穴抚子大米化精华面霜/30g 标准规格 ×1<br>&nbsp;&nbsp;肌美精3D面膜（粉色保湿款） 标准规格 ×1<br>提交时间: 2019-05-22 09:56:00","video":"","readTime":"2019-05-22 09:56:00","createDate":"2019-05-22 09:56:00","id":"1131015823272579072"},{"sourceId":"1130950084767387648","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 1255.00 <br>订单号: 20190522053446902505 <br>商品详情:  <br>&nbsp;&nbsp;Mandom曼丹 Bifesta缤若诗 保湿卸妆湿巾浸润型 46枚 标准规格 ×1<br>&nbsp;&nbsp;SK-II 青春露 护肤精华露（神仙水）230毫升 标准规格 ×1<br>提交时间: 2019-05-22 05:34:47","video":"","readTime":"2019-05-22 05:34:47","createDate":"2019-05-22 05:34:47","id":"1130950085404921856"},{"sourceId":"0","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 70.00 <br>订单号: 20190522045926452308 <br>商品详情:  <br>&nbsp;&nbsp;大麦若叶  ×1<br>提交时间: 2019-05-22 04:59:26","video":"","readTime":"2019-05-22 04:59:26","createDate":"2019-05-22 04:59:26","id":"1130941191500730368"},{"sourceId":"1130755608388177920","title":"订单提交","content":"您已经成功提交订单 <br>订单金额: ￥ 51.00 <br>订单号: 20190521164200120615 <br>商品详情:  <br>&nbsp;&nbsp;脂流茶  ×1<br>提交时间: 2019-05-21 16:42:00","video":"","readTime":"2019-05-21 16:42:00","createDate":"2019-05-21 16:42:00","id":"1130755609344479232"}]
         * totalCount : 6
         * pageNo : 1
         * pageSize : 10
         * last : 1
         */

        private int totalCount;
        private int pageNo;
        private int pageSize;
        private int last;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
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
}
