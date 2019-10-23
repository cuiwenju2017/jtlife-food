package commeishi.shanjing.mymeishi.model;

public class AppoiExpressbean {

    /**
     * status : 1
     * errorCode :
     * errorMsg :
     * data : {"Order":{"ShipperCode":"STO","OrderCode":"1135864737830146048"},"EBusinessID":"1402573","UniquerRequestNumber":"95153b53-5e5d-4acf-b51c-2c178a1e339f","ResultCode":"E-LOGISTICSEX-907","Reason":"重复操作[该订单号或快递单号已经存在，请勿重复操作]","Success":false}
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
         * Order : {"ShipperCode":"STO","OrderCode":"1135864737830146048"}
         * EBusinessID : 1402573
         * UniquerRequestNumber : 95153b53-5e5d-4acf-b51c-2c178a1e339f
         * ResultCode : E-LOGISTICSEX-907
         * Reason : 重复操作[该订单号或快递单号已经存在，请勿重复操作]
         * Success : false
         */

        private OrderBean Order;
        private String EBusinessID;
        private String UniquerRequestNumber;
        private String ResultCode;
        private String Reason;
        private boolean Success;

        public OrderBean getOrder() {
            return Order;
        }

        public void setOrder(OrderBean Order) {
            this.Order = Order;
        }

        public String getEBusinessID() {
            return EBusinessID;
        }

        public void setEBusinessID(String EBusinessID) {
            this.EBusinessID = EBusinessID;
        }

        public String getUniquerRequestNumber() {
            return UniquerRequestNumber;
        }

        public void setUniquerRequestNumber(String UniquerRequestNumber) {
            this.UniquerRequestNumber = UniquerRequestNumber;
        }

        public String getResultCode() {
            return ResultCode;
        }

        public void setResultCode(String ResultCode) {
            this.ResultCode = ResultCode;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public boolean isSuccess() {
            return Success;
        }

        public void setSuccess(boolean Success) {
            this.Success = Success;
        }

        public static class OrderBean {
            /**
             * ShipperCode : STO
             * OrderCode : 1135864737830146048
             */

            private String ShipperCode;
            private String OrderCode;

            public String getShipperCode() {
                return ShipperCode;
            }

            public void setShipperCode(String ShipperCode) {
                this.ShipperCode = ShipperCode;
            }

            public String getOrderCode() {
                return OrderCode;
            }

            public void setOrderCode(String OrderCode) {
                this.OrderCode = OrderCode;
            }
        }
    }
}
