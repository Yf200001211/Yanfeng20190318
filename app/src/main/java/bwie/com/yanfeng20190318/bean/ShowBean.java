package bwie.com.yanfeng20190318.bean;

import java.io.Serializable;
import java.util.List;

public class ShowBean implements Serializable {
    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }
    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String commodityId;
        private String commodityName;
        private String masterPic;
        private String price;
        private String saleNum;

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(String saleNum) {
            this.saleNum = saleNum;
        }
    }
}
