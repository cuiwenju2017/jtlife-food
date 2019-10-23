package commeishi.shanjing.mymeishi.model;

import java.util.List;

public class Orderdetails {


        /**
         * orderId : 1131015823088029696
         * orderNo : 20190522095600139775
         * orderType : 0
         * shopId : 1059396465337700352
         * shopName : 罗森商超
         * shopLogo : https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png
         * orderStatus : -1
         * statusMember : -1
         * statusSystem : -1
         * statusPay : 0
         * statusJudge : 0
         * statusInvoice : 0
         * closeType : 0
         * fee : 162.00
         * moneyProduce : 162.0
         * moneyLgt : 0.0
         * numBeansDeduct : 0
         * moneyBeansDeduct : 0.0
         * couponId : 0
         * couponAmount : 0.0
         * moneyTotal : 162.0
         * moneyPaid : 0.0
         * payType : 0
         * payChannel : 0
         * receiveName : ASONG
         * receiveTel : 13093765908
         * receiveAreaStr : 浙江省,杭州市,西湖区
         * receiveAreaDetail : 东日晴好6号楼3-101
         * memberRemarks :
         * createDate : 2019-05-22 09:56:00
         * updateDate : 2019-05-22 09:56:21
         * orderList : [{"orderId":"1131015823088029696","goodsId":"2019021915960569411","produceId":"1087622090284204515","num":1,"renums":0,"icon":"http://imgs.weekfan.com/images/3/2018/12/DpzqIjma77AQZa0zkP0AHKK8aK4qzi.jpg?x-oss-process=style/zhoufan2018","goodsTitle":"ISHIZAWA LABS日本石泽研究所毛穴抚子大米化精华面霜/30g","produceName":"标准规格","priceSrc":117,"discountBuy":117,"priceBuy":117,"id":"1131015823222247424"},{"orderId":"1131015823088029696","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":1,"renums":0,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":45,"discountBuy":45,"priceBuy":45,"id":"1131015823222247425"}]
         * id : 1131015823088029696
         */

        private String orderId;
        private String orderNo;
        private String orderType;
        private String shopId;
        private String shopName;
        private String shopLogo;
        private String orderStatus;
        private String statusMember;
        private String statusSystem;
        private String statusPay;
        private String statusJudge;
        private String statusInvoice;
        private String closeType;
        private String fee;
        private double moneyProduce;
        private double moneyLgt;
        private int numBeansDeduct;
        private double moneyBeansDeduct;
        private int couponId;
        private double couponAmount;
        private double moneyTotal;
        private double moneyPaid;
        private String payType;
        private String payChannel;
        private String receiveName;
        private String receiveTel;
        private String receiveAreaStr;
        private String receiveAreaDetail;
        private String memberRemarks;
        private String createDate;
        private String updateDate;
        private String id;
        private List<OrderListBean> orderList;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getStatusMember() {
            return statusMember;
        }

        public void setStatusMember(String statusMember) {
            this.statusMember = statusMember;
        }

        public String getStatusSystem() {
            return statusSystem;
        }

        public void setStatusSystem(String statusSystem) {
            this.statusSystem = statusSystem;
        }

        public String getStatusPay() {
            return statusPay;
        }

        public void setStatusPay(String statusPay) {
            this.statusPay = statusPay;
        }

        public String getStatusJudge() {
            return statusJudge;
        }

        public void setStatusJudge(String statusJudge) {
            this.statusJudge = statusJudge;
        }

        public String getStatusInvoice() {
            return statusInvoice;
        }

        public void setStatusInvoice(String statusInvoice) {
            this.statusInvoice = statusInvoice;
        }

        public String getCloseType() {
            return closeType;
        }

        public void setCloseType(String closeType) {
            this.closeType = closeType;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public double getMoneyProduce() {
            return moneyProduce;
        }

        public void setMoneyProduce(double moneyProduce) {
            this.moneyProduce = moneyProduce;
        }

        public double getMoneyLgt() {
            return moneyLgt;
        }

        public void setMoneyLgt(double moneyLgt) {
            this.moneyLgt = moneyLgt;
        }

        public int getNumBeansDeduct() {
            return numBeansDeduct;
        }

        public void setNumBeansDeduct(int numBeansDeduct) {
            this.numBeansDeduct = numBeansDeduct;
        }

        public double getMoneyBeansDeduct() {
            return moneyBeansDeduct;
        }

        public void setMoneyBeansDeduct(double moneyBeansDeduct) {
            this.moneyBeansDeduct = moneyBeansDeduct;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public double getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(double couponAmount) {
            this.couponAmount = couponAmount;
        }

        public double getMoneyTotal() {
            return moneyTotal;
        }

        public void setMoneyTotal(double moneyTotal) {
            this.moneyTotal = moneyTotal;
        }

        public double getMoneyPaid() {
            return moneyPaid;
        }

        public void setMoneyPaid(double moneyPaid) {
            this.moneyPaid = moneyPaid;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
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

        public String getReceiveAreaStr() {
            return receiveAreaStr;
        }

        public void setReceiveAreaStr(String receiveAreaStr) {
            this.receiveAreaStr = receiveAreaStr;
        }

        public String getReceiveAreaDetail() {
            return receiveAreaDetail;
        }

        public void setReceiveAreaDetail(String receiveAreaDetail) {
            this.receiveAreaDetail = receiveAreaDetail;
        }

        public String getMemberRemarks() {
            return memberRemarks;
        }

        public void setMemberRemarks(String memberRemarks) {
            this.memberRemarks = memberRemarks;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * orderId : 1131015823088029696
             * goodsId : 2019021915960569411
             * produceId : 1087622090284204515
             * num : 1
             * renums : 0
             * icon : http://imgs.weekfan.com/images/3/2018/12/DpzqIjma77AQZa0zkP0AHKK8aK4qzi.jpg?x-oss-process=style/zhoufan2018
             * goodsTitle : ISHIZAWA LABS日本石泽研究所毛穴抚子大米化精华面霜/30g
             * produceName : 标准规格
             * priceSrc : 117.0
             * discountBuy : 117.0
             * priceBuy : 117.0
             * id : 1131015823222247424
             */

            private String orderId;
            private String goodsId;
            private String produceId;
            private int num;
            private int renums;
            private String icon;
            private String goodsTitle;
            private String produceName;
            private double priceSrc;
            private double discountBuy;
            private double priceBuy;
            private String id;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getProduceId() {
                return produceId;
            }

            public void setProduceId(String produceId) {
                this.produceId = produceId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getRenums() {
                return renums;
            }

            public void setRenums(int renums) {
                this.renums = renums;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getGoodsTitle() {
                return goodsTitle;
            }

            public void setGoodsTitle(String goodsTitle) {
                this.goodsTitle = goodsTitle;
            }

            public String getProduceName() {
                return produceName;
            }

            public void setProduceName(String produceName) {
                this.produceName = produceName;
            }

            public double getPriceSrc() {
                return priceSrc;
            }

            public void setPriceSrc(double priceSrc) {
                this.priceSrc = priceSrc;
            }

            public double getDiscountBuy() {
                return discountBuy;
            }

            public void setDiscountBuy(double discountBuy) {
                this.discountBuy = discountBuy;
            }

            public double getPriceBuy() {
                return priceBuy;
            }

            public void setPriceBuy(double priceBuy) {
                this.priceBuy = priceBuy;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

}
