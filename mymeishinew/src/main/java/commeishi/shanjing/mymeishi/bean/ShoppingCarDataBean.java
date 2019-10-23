package commeishi.shanjing.mymeishi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车bean类
 */
public class ShoppingCarDataBean implements Serializable {

    /**
     * data : [{"shopId":1059396465337700352,"shopName":"罗森商超","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png","cartList":[{"memberId":0,"shopId":"1059396465337700352","collocationId":0,"goodsId":"1087622125189206016","produceId":"1087622125310840832","num":2,"title":"Transino/第一三共 祛斑提亮肤色面膜 20毫升/片 4枚入","produceTitle":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/xBBXFMram10DxtliTaFEOLnbrTB2tB.jpg?x-oss-process=style/zhoufan2018","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/xBBXFMram10DxtliTaFEOLnbrTB2tB.jpg?x-oss-process=style/zhoufan2018","priceSrc":108,"discountPrice":108,"id":"1130953327601913856"},{"memberId":0,"shopId":"1059396465337700352","collocationId":0,"goodsId":"1087622110685302784","produceId":"1087622110811131904","num":4,"title":"【明星同款】WELLNESS BEAUTE Gel Pack 撕拉去黑头毛孔清洁面膜 90克","produceTitle":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/UFwwO0kAfkaAC066K9k60922mfa6Mj.jpg?x-oss-process=style/zhoufan2018","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/UFwwO0kAfkaAC066K9k60922mfa6Mj.jpg?x-oss-process=style/zhoufan2018","priceSrc":66,"discountPrice":66,"id":"1130953414449172480"},{"memberId":0,"shopId":"1059396465337700352","collocationId":0,"goodsId":"1087622103928279040","produceId":"1087622104028942336","num":1,"title":"SHISEIDO 资生堂 UNO 男士洗面奶 保湿温和型 绿色 130克","produceTitle":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/tOoMIM5RSN8RL8asa55gNL1O5amDra.jpg?x-oss-process=style/zhoufan2018","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/tOoMIM5RSN8RL8asa55gNL1O5amDra.jpg?x-oss-process=style/zhoufan2018","priceSrc":26,"discountPrice":26,"id":"1130953676928716800"}],"id":"0"},{"shopId":1059396544446468096,"shopName":"牛很鲜潮汕牛肉火锅","shopLogo":"https://foodcms.oss-cn-hangzhou.aliyuncs.com/3ea57167ded05aa66b0acdee91d367f166663.jpg","cartList":[{"memberId":0,"shopId":"1059396544446468096","collocationId":0,"goodsId":"1087622086186373120","produceId":"0","num":26,"title":"脂流茶","icon":"http://imgs.weekfan.com/images/3/2018/12/NJAGgtG9X98ATozjVBVjg2xGgeeePG.jpg?x-oss-process=style/zhoufan2018","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/NJAGgtG9X98ATozjVBVjg2xGgeeePG.jpg?x-oss-process=style/zhoufan2018","discountPrice":0,"id":"1131492111267532800"}],"id":"0"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * shopId : 1059396465337700352
         * shopName : 罗森商超
         * shopLogo : https://foodcms.oss-cn-hangzhou.aliyuncs.com/logo1.png
         * cartList : [{"memberId":0,"shopId":"1059396465337700352","collocationId":0,"goodsId":"1087622125189206016","produceId":"1087622125310840832","num":2,"title":"Transino/第一三共 祛斑提亮肤色面膜 20毫升/片 4枚入","produceTitle":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/xBBXFMram10DxtliTaFEOLnbrTB2tB.jpg?x-oss-process=style/zhoufan2018","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/xBBXFMram10DxtliTaFEOLnbrTB2tB.jpg?x-oss-process=style/zhoufan2018","priceSrc":108,"discountPrice":108,"id":"1130953327601913856"},{"memberId":0,"shopId":"1059396465337700352","collocationId":0,"goodsId":"1087622110685302784","produceId":"1087622110811131904","num":4,"title":"【明星同款】WELLNESS BEAUTE Gel Pack 撕拉去黑头毛孔清洁面膜 90克","produceTitle":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/UFwwO0kAfkaAC066K9k60922mfa6Mj.jpg?x-oss-process=style/zhoufan2018","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/UFwwO0kAfkaAC066K9k60922mfa6Mj.jpg?x-oss-process=style/zhoufan2018","priceSrc":66,"discountPrice":66,"id":"1130953414449172480"},{"memberId":0,"shopId":"1059396465337700352","collocationId":0,"goodsId":"1087622103928279040","produceId":"1087622104028942336","num":1,"title":"SHISEIDO 资生堂 UNO 男士洗面奶 保湿温和型 绿色 130克","produceTitle":"标准规格","icon":"http://imgs.weekfan.com/images/3/2018/12/tOoMIM5RSN8RL8asa55gNL1O5amDra.jpg?x-oss-process=style/zhoufan2018","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/tOoMIM5RSN8RL8asa55gNL1O5amDra.jpg?x-oss-process=style/zhoufan2018","priceSrc":26,"discountPrice":26,"id":"1130953676928716800"}]
         * id : 0
         */

        private long shopId;
        private String shopName;
        private String shopLogo;
        private String id;
        private List<CartListBean> cartList;
        private boolean isSelect_shop;      //店铺是否在购物车中被选中

        public DataBean clone() {
            DataBean o = null;
            try {
                o = (DataBean) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return o;
        }

        public boolean isSelect_shop() {
            return isSelect_shop;
        }

        public void setSelect_shop(boolean select_shop) {
            isSelect_shop = select_shop;
        }

        public long getShopId() {
            return shopId;
        }

        public void setShopId(long shopId) {
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<CartListBean> getCartList() {
            return cartList;
        }

        public void setCartList(List<CartListBean> cartList) {
            this.cartList = cartList;
        }


        public static class CartListBean implements Serializable{
            /**
             * memberId : 0
             * shopId : 1059396465337700352
             * collocationId : 0
             * goodsId : 1087622125189206016
             * produceId : 1087622125310840832
             * num : 2
             * title : Transino/第一三共 祛斑提亮肤色面膜 20毫升/片 4枚入
             * produceTitle : 标准规格
             * icon : http://imgs.weekfan.com/images/3/2018/12/xBBXFMram10DxtliTaFEOLnbrTB2tB.jpg?x-oss-process=style/zhoufan2018
             * singlePhoto : http://imgs.weekfan.com/images/3/2018/12/xBBXFMram10DxtliTaFEOLnbrTB2tB.jpg?x-oss-process=style/zhoufan2018
             * priceSrc : 108.0
             * discountPrice : 108.0
             * id : 1130953327601913856
             */

            private int memberId;
            private String shopId;
            private int collocationId;
            private String goodsId;
            private String produceId;
            private int num;
            private String title;
            private String produceTitle;
            private String icon;
            private String singlePhoto;
            private double priceSrc;
            private double discountPrice;
            private String id;
            private boolean isSelect;        //商品是否在购物车中被选中

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public int getCollocationId() {
                return collocationId;
            }

            public void setCollocationId(int collocationId) {
                this.collocationId = collocationId;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getProduceTitle() {
                return produceTitle;
            }

            public void setProduceTitle(String produceTitle) {
                this.produceTitle = produceTitle;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getSinglePhoto() {
                return singlePhoto;
            }

            public void setSinglePhoto(String singlePhoto) {
                this.singlePhoto = singlePhoto;
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
