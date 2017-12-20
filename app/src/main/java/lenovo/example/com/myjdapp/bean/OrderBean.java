package lenovo.example.com.myjdapp.bean;

import java.util.List;


public class OrderBean  {


    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-10-22T15:08:23","orderid":885,"price":99.99,"status":0,"title":"","uid":106},{"createtime":"2017-10-22T15:33:38","orderid":899,"price":99.99,"status":0,"title":"","uid":106},{"createtime":"2017-10-22T15:33:53","orderid":900,"price":99.99,"status":0,"title":"","uid":106},{"createtime":"2017-10-30T15:12:25","orderid":1353,"price":888,"status":0,"title":"","uid":106},{"createtime":"2017-11-10T15:05:05","orderid":1466,"price":99.99,"status":0,"title":null,"uid":106},{"createtime":"2017-11-10T15:05:56","orderid":1467,"price":2499,"status":0,"title":null,"uid":106},{"createtime":"2017-11-13T13:15:13","orderid":1481,"price":2499,"status":0,"title":null,"uid":106},{"createtime":"2017-11-13T18:18:04","orderid":1492,"price":2499,"status":0,"title":null,"uid":106},{"createtime":"2017-11-13T19:18:48","orderid":1495,"price":2499,"status":0,"title":null,"uid":106},{"createtime":"2017-11-13T19:25:30","orderid":1497,"price":2499,"status":0,"title":null,"uid":106}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-10-22T15:08:23
         * orderid : 885
         * price : 99.99
         * status : 0
         * title :
         * uid : 106
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
