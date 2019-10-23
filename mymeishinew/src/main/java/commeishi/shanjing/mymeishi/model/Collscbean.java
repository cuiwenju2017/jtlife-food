package commeishi.shanjing.mymeishi.model;

import java.util.List;

public class Collscbean {




        /**
         * list : [{"goodsId":"1087622087465635840","title":"肌美精3D面膜（粉色保湿款）","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":45,"discountPrice":45,"discountFilterScale":0,"id":"1130936033312116736"},{"goodsId":"1087622103928279040","title":"SHISEIDO 资生堂 UNO 男士洗面奶 保湿温和型 绿色 130克","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/tOoMIM5RSN8RL8asa55gNL1O5amDra.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":26,"discountPrice":26,"discountFilterScale":0,"id":"1130953667017576448"},{"goodsId":"1087622116683157504","title":"SHISEIDO 资生堂 UNO 男士多效保湿面霜 90克","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/fGhldMguMM3Lo85OmDjdNAmm5O65Wg.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":63,"discountPrice":63,"discountFilterScale":0,"id":"1130953456287354880"},{"goodsId":"1087622154360590336","title":"Shiseido/资生堂 怡丽丝尔新版眼霜 提拉紧致抗皱精华 眼部护理霜22g","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/b766ZX6lF3zffhFULStC73HosyMqeh.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":527,"discountPrice":527,"discountFilterScale":0,"id":"1130947791850442752"},{"goodsId":"1087622155346251776","title":"PHILIPS 飞利浦 电动剃须刀干湿两用全身水洗刮胡须刀S5072（S5076升级款）黑色","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/F8OeEclHeST668cT2es7TTZENZt8l6.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":622,"discountPrice":622,"discountFilterScale":0,"id":"1130947752109412352"},{"goodsId":"1087622181304799232","title":"COVERMARK 傲丽中草修护粉底霜 SPF18PA++ #YO00 30克","singlePhoto":"http://imgs.weekfan.com/images/3/2019/01/Uy3Bb3Lyo8oEL1LL3o0gS0n0v3oESh.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":400,"discountPrice":400,"discountFilterScale":0,"id":"1130947844002418688"}]
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
             * goodsId : 1087622087465635840
             * title : 肌美精3D面膜（粉色保湿款）
             * singlePhoto : http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018
             * sellNum : 0
             * priceSrc : 45.0
             * discountPrice : 45.0
             * discountFilterScale : 0.0
             * id : 1130936033312116736
             */

            private String goodsId;
            private String title;
            private String singlePhoto;
            private int sellNum;
            private double priceSrc;
            private double discountPrice;
            private double discountFilterScale;
            private String id;

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

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

            public double getDiscountFilterScale() {
                return discountFilterScale;
            }

            public void setDiscountFilterScale(double discountFilterScale) {
                this.discountFilterScale = discountFilterScale;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

}
