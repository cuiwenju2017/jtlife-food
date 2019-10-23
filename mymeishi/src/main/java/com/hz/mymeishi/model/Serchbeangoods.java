package com.hz.mymeishi.model;

import java.util.List;

public class Serchbeangoods {


    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"list":[{"title":"Melty Wink 大眼立显眼霜 17毫升","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/YO9JozSo98KKuwSK8SwWWwpK1PWqWO.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":99,"discountPrice":78,"id":"1087622200783147008"},{"title":"SK-II 微肌因修护焕采眼霜 大眼眼霜 15克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/U621011WRZMz8309MryE62i5m24f7w.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":635,"discountPrice":500,"id":"1087622200460185600"},{"title":"nepia 妮飘 超柔鼻炎感冒抽纸 16包/组","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/FOY2ZLSz5sylL5L5yUrLoLrYLU55lj.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":28,"discountPrice":22,"id":"1087622197998129152"},{"title":"【2双装】ATSUGI 厚木 弹力发热显瘦保暖140D连裤袜丝袜","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/I50AJb510BVa30JnVoMB3vVnvOv4Ba.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":76,"discountPrice":60,"id":"1087622196844695552"},{"title":"HABA 鲨烷保湿洁面乳100g(无添加 温和保湿洗面奶 日本)","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/c3Dn3TjZdlgDvBZBb3r0x3xg5n23Jn.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":152,"discountPrice":120,"id":"1087622195540267008"},{"title":"ÍpsΛ 茵芙莎 粘土按摩面膜 100克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/HI1S3t4I8Y036b8ZZ66hiAb8zQzSpq.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":258,"discountPrice":258,"id":"1087622194902732800"},{"title":"POLA 宝丽 B.A洁面膏 100克 奢侈护肤","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/VY2NCpnjZ4nNxGP5afKyEy7cANMCZA.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":616,"discountPrice":485,"id":"1087622194613325824"},{"title":"LION 狮王旗下CLINICA 齿力佳 酵素珍珠亮白 花香薄荷牙膏 130克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/ylzV00G7rMV8zX15Z7rCGVrcz17cii.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":20,"discountPrice":16,"id":"1087622194265198592"},{"title":"怡丽丝尔ELIXIR WHITE 亮白睡眠凝胶啫喱面膜紧致收毛孔 105克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/EZ2QxsapYwYAXPxiiX4pIa2wWFaJi2.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":232,"discountPrice":183,"id":"1087622193703161856"},{"title":"太阳芦荟社玻尿酸透明质保湿原液(80ml/瓶+10ml*2瓶)/组","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/tWQWe5We2cczvtWOW5022XOwwc0Eww.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":228,"discountPrice":180,"id":"1087622186019196928"}],"totalCount":105,"pageNo":1,"pageSize":10,"last":11}
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
         * list : [{"title":"Melty Wink 大眼立显眼霜 17毫升","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/YO9JozSo98KKuwSK8SwWWwpK1PWqWO.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":99,"discountPrice":78,"id":"1087622200783147008"},{"title":"SK-II 微肌因修护焕采眼霜 大眼眼霜 15克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/U621011WRZMz8309MryE62i5m24f7w.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":635,"discountPrice":500,"id":"1087622200460185600"},{"title":"nepia 妮飘 超柔鼻炎感冒抽纸 16包/组","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/FOY2ZLSz5sylL5L5yUrLoLrYLU55lj.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":28,"discountPrice":22,"id":"1087622197998129152"},{"title":"【2双装】ATSUGI 厚木 弹力发热显瘦保暖140D连裤袜丝袜","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/I50AJb510BVa30JnVoMB3vVnvOv4Ba.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":76,"discountPrice":60,"id":"1087622196844695552"},{"title":"HABA 鲨烷保湿洁面乳100g(无添加 温和保湿洗面奶 日本)","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/c3Dn3TjZdlgDvBZBb3r0x3xg5n23Jn.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":152,"discountPrice":120,"id":"1087622195540267008"},{"title":"ÍpsΛ 茵芙莎 粘土按摩面膜 100克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/HI1S3t4I8Y036b8ZZ66hiAb8zQzSpq.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":258,"discountPrice":258,"id":"1087622194902732800"},{"title":"POLA 宝丽 B.A洁面膏 100克 奢侈护肤","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/VY2NCpnjZ4nNxGP5afKyEy7cANMCZA.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":616,"discountPrice":485,"id":"1087622194613325824"},{"title":"LION 狮王旗下CLINICA 齿力佳 酵素珍珠亮白 花香薄荷牙膏 130克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/ylzV00G7rMV8zX15Z7rCGVrcz17cii.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":20,"discountPrice":16,"id":"1087622194265198592"},{"title":"怡丽丝尔ELIXIR WHITE 亮白睡眠凝胶啫喱面膜紧致收毛孔 105克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/EZ2QxsapYwYAXPxiiX4pIa2wWFaJi2.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":232,"discountPrice":183,"id":"1087622193703161856"},{"title":"太阳芦荟社玻尿酸透明质保湿原液(80ml/瓶+10ml*2瓶)/组","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/tWQWe5We2cczvtWOW5022XOwwc0Eww.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"isSpecsUsable":"0","priceSrc":228,"discountPrice":180,"id":"1087622186019196928"}]
         * totalCount : 105
         * pageNo : 1
         * pageSize : 10
         * last : 11
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
             * title : Melty Wink 大眼立显眼霜 17毫升
             * singlePhoto : http://imgs.weekfan.com/images/3/2019/01/YO9JozSo98KKuwSK8SwWWwpK1PWqWO.jpg?x-oss-process=style/zhoufan2018
             * sellNum : 0
             * isSpecsUsable : 0
             * priceSrc : 99.0
             * discountPrice : 78.0
             * id : 1087622200783147008
             */

            private String title;
            private String singlePhoto;
            private int sellNum;
            private String isSpecsUsable;
            private double priceSrc;
            private double discountPrice;
            private String id;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSinglePhoto() {
                return singlePhoto;
            }

            public void setSinglePhoto(String singlePhoto) {
                this.singlePhoto = singlePhoto;
            }

            public int getSellNum() {
                return sellNum;
            }

            public void setSellNum(int sellNum) {
                this.sellNum = sellNum;
            }

            public String getIsSpecsUsable() {
                return isSpecsUsable;
            }

            public void setIsSpecsUsable(String isSpecsUsable) {
                this.isSpecsUsable = isSpecsUsable;
            }

            public double getPriceSrc() {
                return priceSrc;
            }

            public void setPriceSrc(double priceSrc) {
                this.priceSrc = priceSrc;
            }

            public double getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(double discountPrice) {
                this.discountPrice = discountPrice;
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
