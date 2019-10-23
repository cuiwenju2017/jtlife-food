package commeishi.shanjing.mymeishi.bean;

import java.util.List;

public class HomeLeftBean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : [{"categoryName":"家常菜","categoryTag":"jc","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"1"},{"categoryName":"烘焙","categoryTag":"hp","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"2"},{"categoryName":"日本料理","categoryTag":"rl","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"3"},{"categoryName":"火锅","categoryTag":"hg","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"4"},{"categoryName":"西餐","categoryTag":"sc","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"5"},{"categoryName":"烧烤","categoryTag":"sk","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"6"},{"categoryName":"饮品","categoryTag":"yp","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"7"},{"categoryName":"甜品","categoryTag":"tp","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"9"},{"categoryName":"自助餐","categoryTag":"cz","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"10"},{"categoryName":"小吃快餐","categoryTag":"kc","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"11"},{"categoryName":"生鲜水果","categoryTag":"sx","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"12"},{"categoryName":"小龙虾","categoryTag":"lx","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"13"},{"categoryName":"大闸蟹","categoryTag":"zx","categoryIcon":"http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018","categoryBanner":"http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018","id":"14"}]
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
         * categoryName : 家常菜
         * categoryTag : jc
         * categoryIcon : http://imgs.weekfan.com/main/?x-oss-process=style/zhoufan2018
         * categoryBanner : http://imgs.weekfan.com/cms/?x-oss-process=style/zhoufan2018
         * id : 1
         */

        private String categoryName;
        private String categoryTag;
        private String categoryIcon;
        private String categoryBanner;
        private String id;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryTag() {
            return categoryTag;
        }

        public void setCategoryTag(String categoryTag) {
            this.categoryTag = categoryTag;
        }

        public String getCategoryIcon() {
            return categoryIcon;
        }

        public void setCategoryIcon(String categoryIcon) {
            this.categoryIcon = categoryIcon;
        }

        public String getCategoryBanner() {
            return categoryBanner;
        }

        public void setCategoryBanner(String categoryBanner) {
            this.categoryBanner = categoryBanner;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
