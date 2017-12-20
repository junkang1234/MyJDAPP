package lenovo.example.com.myjdapp.bean;

import java.util.List;


public class TakeGoodsBean extends BaseBean{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : 北京市海淀区中关村
         * addrid : 2
         * mobile : 1356678765
         * name : 627B07D61CA4885A015F54A09963D902
         * status : 0
         * uid : 71
         */
        private String addr;
        private int addrid;
        private long mobile;
        private String name;
        private int status;
        private int uid;
        private boolean isFlag = false ;

        public boolean isFlag() {
            return isFlag;
        }

        public void setFlag(boolean flag) {
            isFlag = flag;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(int mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
