package commeishi.shanjing.mymeishi.model;

import java.util.List;

public class Sppjlistbean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"list":[{"produceLevel":"4","context":"吸收很好\n","createDate":"2019-04-09 16:54:08","judgePhotoList":[],"id":1115538373193371648},{"usercode":"13093765908","nickname":"asong","gravatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrQCLnxDLNOzsPniaSTr7nLjUQuLlmSiaLXvb6HNbrBOALas3Wyia8FDHb0VgFwEADXP3UsXO9t6b7A/132","produceLevel":"1","context":"hhahah","createDate":"2019-06-20 17:48:25","judgePhotoList":[],"id":1141643960389865472},{"usercode":"13093765908","nickname":"asong","gravatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrQCLnxDLNOzsPniaSTr7nLjUQuLlmSiaLXvb6HNbrBOALas3Wyia8FDHb0VgFwEADXP3UsXO9t6b7A/132","produceLevel":"2","context":"11111","createDate":"2019-06-20 18:09:09","judgePhotoList":[],"id":1141649177026105344}],"totalCount":3,"pageNo":1,"pageSize":10,"last":1}
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
         * list : [{"produceLevel":"4","context":"吸收很好\n","createDate":"2019-04-09 16:54:08","judgePhotoList":[],"id":1115538373193371648},{"usercode":"13093765908","nickname":"asong","gravatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrQCLnxDLNOzsPniaSTr7nLjUQuLlmSiaLXvb6HNbrBOALas3Wyia8FDHb0VgFwEADXP3UsXO9t6b7A/132","produceLevel":"1","context":"hhahah","createDate":"2019-06-20 17:48:25","judgePhotoList":[],"id":1141643960389865472},{"usercode":"13093765908","nickname":"asong","gravatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrQCLnxDLNOzsPniaSTr7nLjUQuLlmSiaLXvb6HNbrBOALas3Wyia8FDHb0VgFwEADXP3UsXO9t6b7A/132","produceLevel":"2","context":"11111","createDate":"2019-06-20 18:09:09","judgePhotoList":[],"id":1141649177026105344}]
         * totalCount : 3
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
             * produceLevel : 4
             * context : 吸收很好

             * createDate : 2019-04-09 16:54:08
             * judgePhotoList : []
             * id : 1115538373193371648
             * usercode : 13093765908
             * nickname : asong
             * gravatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrQCLnxDLNOzsPniaSTr7nLjUQuLlmSiaLXvb6HNbrBOALas3Wyia8FDHb0VgFwEADXP3UsXO9t6b7A/132
             */

            private String produceLevel;
            private String context;
            private String createDate;
            private long id;
            private String usercode;
            private String nickname;
            private String gravatar;
            private List<?> judgePhotoList;

            public String getProduceLevel() {
                return produceLevel;
            }

            public void setProduceLevel(String produceLevel) {
                this.produceLevel = produceLevel;
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

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getUsercode() {
                return usercode;
            }

            public void setUsercode(String usercode) {
                this.usercode = usercode;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getGravatar() {
                return gravatar;
            }

            public void setGravatar(String gravatar) {
                this.gravatar = gravatar;
            }

            public List<?> getJudgePhotoList() {
                return judgePhotoList;
            }

            public void setJudgePhotoList(List<?> judgePhotoList) {
                this.judgePhotoList = judgePhotoList;
            }
        }
    }
}
