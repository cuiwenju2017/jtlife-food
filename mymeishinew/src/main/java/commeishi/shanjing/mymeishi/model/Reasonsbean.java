package commeishi.shanjing.mymeishi.model;

import java.util.List;

public class Reasonsbean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : ["买错了，不想要了","质量问题","商品破损","7天无理由退货","商品与描述不符","缺少件","其他"]
     */

    private String status;
    private String errorCode;
    private String errorMsg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
