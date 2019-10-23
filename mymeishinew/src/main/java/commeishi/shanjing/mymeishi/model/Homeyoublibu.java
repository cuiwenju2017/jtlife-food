package commeishi.shanjing.mymeishi.model;

import java.util.List;

public class Homeyoublibu {



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
             * title : 亲爱的消费主，感谢您光临 “牛很鲜”,感谢您的用心评价及指正，不足之处，我们会自查原因，出现这样的失误，是我们的错，我们正在对员工培训及培养，及时改进，本季度新品已上线， “牛很鲜”全体成员期待您的再
             * listPhoto : http://imgs.weekfan.com/00a84b5245130a59e0ddb509ef44386f?x-oss-process=style/zhoufan2018
             * videoUrl : http://v.weekfan.com/00a84b5245130a59e0ddb509ef44386f?x-oss-process=style/zhoufan2018
             * defaultPv : 0
             * pv : 131
             * shareNum : 0
             * orderNum : 0
             * createDate : 0001-01-01 00:00:00
             * publisherId : 1059396544446468096
             * publisherName : 牛很鲜潮汕牛肉火锅
             * publisherLogo : https://foodcms.oss-cn-hangzhou.aliyuncs.com/3ea57167ded05aa66b0acdee91d367f166663.jpg
             * longitude : 120.1628510000
             * latitude : 30.2693080000
             * address : 下城区朝晖路198号和家院2楼
             * pgcGoodsList : [{"title":"脂流茶","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/NJAGgtG9X98ATozjVBVjg2xGgeeePG.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":0,"discountPrice":0,"discountFilterScale":0,"id":"1087622086186373120"},{"title":"Mandom曼丹 Bifesta缤若诗 保湿卸妆湿巾浸润型 46枚","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/u0eFFkmDKC22OnnXe0HgeTFmN2DgdD.png?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":0,"discountPrice":0,"discountFilterScale":0,"id":"1087622095908769792"},{"title":"tiger虎牌保温杯MBR-S06C儿童保冷吸管杯600ML两用小学生可爱 儿童两用保温杯","singlePhoto":"http://imgs.weekfan.com/images/3/2018/12/SB3DKH159zM9k99c1K9CP953h9Y52k.jpg?x-oss-process=style/zhoufan2018","sellNum":0,"priceSrc":0,"discountPrice":0,"discountFilterScale":0,"id":"1087622098899308544"}]
             * pgcJudgeList : [{"usercode":"13093765908","nickName":"asong","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrQCLnxDLNOzsPniaSTr7nLjUQuLlmSiaLXvb6HNbrBOALas3Wyia8FDH","context":"可以diy一碗牛肉面~~牛腩很好吃~汤要一开始喝味道不错，吃到后来会咸，豆腐皮也很不错~就是感觉金针菇不太新鲜，吃到一个酸的。。。其他都挺好的~环境也安静~","createDate":"2019-05-20 15:11:05","judgeMediaList":[],"id":"1059396505267474489"}]
             * id : 0
             */

            private String title;
            private String listPhoto;
            private String videoUrl;
            private int defaultPv;
            private int pv;
            private int shareNum;
            private int orderNum;
            private String createDate;
            private String publisherId;
            private String publisherName;
            private String publisherLogo;
            private String longitude;
            private String latitude;
            private String address;
            private String id;
            private List<PgcGoodsListBean> pgcGoodsList;
            private List<PgcJudgeListBean> pgcJudgeList;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getListPhoto() {
                return listPhoto;
            }

            public void setListPhoto(String listPhoto) {
                this.listPhoto = listPhoto;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public int getDefaultPv() {
                return defaultPv;
            }

            public void setDefaultPv(int defaultPv) {
                this.defaultPv = defaultPv;
            }

            public int getPv() {
                return pv;
            }

            public void setPv(int pv) {
                this.pv = pv;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getPublisherId() {
                return publisherId;
            }

            public void setPublisherId(String publisherId) {
                this.publisherId = publisherId;
            }

            public String getPublisherName() {
                return publisherName;
            }

            public void setPublisherName(String publisherName) {
                this.publisherName = publisherName;
            }

            public String getPublisherLogo() {
                return publisherLogo;
            }

            public void setPublisherLogo(String publisherLogo) {
                this.publisherLogo = publisherLogo;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<PgcGoodsListBean> getPgcGoodsList() {
                return pgcGoodsList;
            }

            public void setPgcGoodsList(List<PgcGoodsListBean> pgcGoodsList) {
                this.pgcGoodsList = pgcGoodsList;
            }

            public List<PgcJudgeListBean> getPgcJudgeList() {
                return pgcJudgeList;
            }

            public void setPgcJudgeList(List<PgcJudgeListBean> pgcJudgeList) {
                this.pgcJudgeList = pgcJudgeList;
            }

            public static class PgcGoodsListBean {
                /**
                 * title : 脂流茶
                 * singlePhoto : http://imgs.weekfan.com/images/3/2018/12/NJAGgtG9X98ATozjVBVjg2xGgeeePG.jpg?x-oss-process=style/zhoufan2018
                 * sellNum : 0
                 * priceSrc : 0.0
                 * discountPrice : 0.0
                 * discountFilterScale : 0.0
                 * id : 1087622086186373120
                 */

                private String title;
                private String singlePhoto;
                private int sellNum;
                private double priceSrc;
                private double discountPrice;
                private double discountFilterScale;
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

            public static class PgcJudgeListBean {
                /**
                 * usercode : 13093765908
                 * nickName : asong
                 * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrQCLnxDLNOzsPniaSTr7nLjUQuLlmSiaLXvb6HNbrBOALas3Wyia8FDH
                 * context : 可以diy一碗牛肉面~~牛腩很好吃~汤要一开始喝味道不错，吃到后来会咸，豆腐皮也很不错~就是感觉金针菇不太新鲜，吃到一个酸的。。。其他都挺好的~环境也安静~
                 * createDate : 2019-05-20 15:11:05
                 * judgeMediaList : []
                 * id : 1059396505267474489
                 */

                private String usercode;
                private String nickName;
                private String avatar;
                private String context;
                private String createDate;
                private String id;
                private List<?> judgeMediaList;

                public String getUsercode() {
                    return usercode;
                }

                public void setUsercode(String usercode) {
                    this.usercode = usercode;
                }

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getContext() {
                    return context;
                }

                public void setContext(String context) {
                    this.context = context;
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

                public List<?> getJudgeMediaList() {
                    return judgeMediaList;
                }

                public void setJudgeMediaList(List<?> judgeMediaList) {
                    this.judgeMediaList = judgeMediaList;
                }
            }
        }

}
