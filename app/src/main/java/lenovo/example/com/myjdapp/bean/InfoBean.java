package lenovo.example.com.myjdapp.bean;


public class InfoBean extends BaseBean {


    /**
     * data : {"age":null,"createtime":"2017-10-14T00:00:00","gender":0,"icon":null,"mobile":"18514823236","money":0,"nickname":null,"password":"123456","uid":387,"username":"18514823236"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * createtime : 2017-10-14T00:00:00
         * gender : 0
         * icon : null
         * mobile : 18514823236
         * money : 0
         * nickname : null
         * password : 123456
         * uid : 387
         * username : 18514823236
         */

        private Object age;
        private String createtime;
        private int gender;
        private Object icon;
        private String mobile;
        private int money;
        private Object nickname;
        private String password;
        private int uid;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
