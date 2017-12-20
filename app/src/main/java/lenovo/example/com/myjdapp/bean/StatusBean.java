package lenovo.example.com.myjdapp.bean;


public class StatusBean {

    private boolean group_check ;
    private boolean child_check ;
    private int num ;
    private float price ;

    public boolean isGroup_check() {
        return group_check;
    }

    public void setGroup_check(boolean group_check) {
        this.group_check = group_check;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isChild_check() {
        return child_check;
    }

    public void setChild_check(boolean child_check) {
        this.child_check = child_check;
    }
}
