package lenovo.example.com.myjdapp.view;


public interface ICartView {
    //修改购物车中全选按钮的状态
    void changeCheckBtn(boolean flag);
    //计算总价的方法
    void addPrice();
    void addCount();

    void delete(String uid, String pid);
}
