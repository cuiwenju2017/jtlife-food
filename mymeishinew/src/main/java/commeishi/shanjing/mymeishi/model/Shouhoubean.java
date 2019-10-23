package commeishi.shanjing.mymeishi.model;

import java.util.List;

public class Shouhoubean {


    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"list":[{"orderId":"1134706898709385216","shopId":"1059396465337700352","shopName":"罗森商超","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png","orderNo":"20190601142301144380","return_orderNo":"201906041903514545108","orderStatus":"6","returnType":"1","orderList":[{"orderId":"1134706898709385216","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"}],"id":"1135864737641402368"},{"orderId":"1134703728125939712","shopId":"1059396465337700352","shopName":"罗森商超","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png","orderNo":"20190601141025218716","return_orderNo":"201906011452536639540","orderStatus":"6","returnType":"0","orderList":[{"orderId":"1134703728125939712","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":3,"renums":3,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"}],"id":"1134714417078996992"},{"orderId":"1134706898692608003","shopId":"1059396465337700352","shopName":"罗森商超","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png","orderNo":"20190601142301140652","return_orderNo":"201906011449554299716","orderStatus":"6","returnType":"0","orderList":[{"orderId":"1134706898692608003","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"},{"orderId":"1134706898692608003","goodsId":"1087622090187739136","produceId":"1087622090284208128","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/XO9nRE0n0NXp0k3EMv0oEWKnV04Z0V.jpg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（黄色）胶原蛋白","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"}],"id":"1134713669511417856"},{"orderId":"1134706903289565184","shopId":"1059396544446468096","shopName":"牛很鲜潮汕牛肉火锅","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/3ea57167ded05aa66b0acdee91d367f166663.jpg","orderNo":"20190601142302236747","return_orderNo":"201906011424438369013","orderStatus":"6","returnType":"0","orderList":[{"orderId":"1134706903289565184","goodsId":"1087622085905354752","produceId":"1087622085997629440","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/E0tmSdALT0iMlialLnYNGNy6DSgLnY.jpg?x-oss-process=style/zhoufan2018","goodsTitle":"大麦若叶","produceName":"","priceSrc":0,"discountBuy":0,"priceBuy":70,"id":"0"}],"id":"1134707329430851584"}],"totalCount":4,"pageNo":1,"pageSize":10,"last":1}
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
         * list : [{"orderId":"1134706898709385216","shopId":"1059396465337700352","shopName":"罗森商超","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png","orderNo":"20190601142301144380","return_orderNo":"201906041903514545108","orderStatus":"6","returnType":"1","orderList":[{"orderId":"1134706898709385216","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"}],"id":"1135864737641402368"},{"orderId":"1134703728125939712","shopId":"1059396465337700352","shopName":"罗森商超","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png","orderNo":"20190601141025218716","return_orderNo":"201906011452536639540","orderStatus":"6","returnType":"0","orderList":[{"orderId":"1134703728125939712","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":3,"renums":3,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"}],"id":"1134714417078996992"},{"orderId":"1134706898692608003","shopId":"1059396465337700352","shopName":"罗森商超","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png","orderNo":"20190601142301140652","return_orderNo":"201906011449554299716","orderStatus":"6","returnType":"0","orderList":[{"orderId":"1134706898692608003","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"},{"orderId":"1134706898692608003","goodsId":"1087622090187739136","produceId":"1087622090284208128","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/XO9nRE0n0NXp0k3EMv0oEWKnV04Z0V.jpg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（黄色）胶原蛋白","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"}],"id":"1134713669511417856"},{"orderId":"1134706903289565184","shopId":"1059396544446468096","shopName":"牛很鲜潮汕牛肉火锅","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/3ea57167ded05aa66b0acdee91d367f166663.jpg","orderNo":"20190601142302236747","return_orderNo":"201906011424438369013","orderStatus":"6","returnType":"0","orderList":[{"orderId":"1134706903289565184","goodsId":"1087622085905354752","produceId":"1087622085997629440","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/E0tmSdALT0iMlialLnYNGNy6DSgLnY.jpg?x-oss-process=style/zhoufan2018","goodsTitle":"大麦若叶","produceName":"","priceSrc":0,"discountBuy":0,"priceBuy":70,"id":"0"}],"id":"1134707329430851584"}]
         * totalCount : 4
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
             * orderId : 1134706898709385216
             * shopId : 1059396465337700352
             * shopName : 罗森商超
             * shopLogo : https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png
             * orderNo : 20190601142301144380
             * return_orderNo : 201906041903514545108
             * orderStatus : 6
             * returnType : 1
             * orderList : [{"orderId":"1134706898709385216","goodsId":"1087622087465635840","produceId":"1087622087549521920","num":1,"renums":1,"icon":"http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018","goodsTitle":"肌美精3D面膜（粉色保湿款）","produceName":"标准规格","priceSrc":0,"discountBuy":0,"priceBuy":45,"id":"0"}]
             * id : 1135864737641402368
             */

            private String orderId;
            private String shopId;
            private String shopName;
            private String shopLogo;
            private String orderNo;
            private String return_orderNo;
            private String orderStatus;
            private String returnType;
            private String id;
            private List<OrderListBean> orderList;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
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

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getReturn_orderNo() {
                return return_orderNo;
            }

            public void setReturn_orderNo(String return_orderNo) {
                this.return_orderNo = return_orderNo;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getReturnType() {
                return returnType;
            }

            public void setReturnType(String returnType) {
                this.returnType = returnType;
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
                 * orderId : 1134706898709385216
                 * goodsId : 1087622087465635840
                 * produceId : 1087622087549521920
                 * num : 1
                 * renums : 1
                 * icon : http://imgs.weekfan.com/images/3/2018/12/rWCsI7NXWgw9SPitE16195Pp36sWW7.jpeg?x-oss-process=style/zhoufan2018
                 * goodsTitle : 肌美精3D面膜（粉色保湿款）
                 * produceName : 标准规格
                 * priceSrc : 0.0
                 * discountBuy : 0.0
                 * priceBuy : 45.0
                 * id : 0
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
    }
}
