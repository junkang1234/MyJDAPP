package lenovo.example.com.myjdapp.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import lenovo.example.com.myjdapp.bean.OrderBean;
import lenovo.example.com.myjdapp.module.OrderModle;
import lenovo.example.com.myjdapp.view.OrderView;


public class OrderPresenter implements OrderModle.loadData {

    private OrderModle orderModle ;
    private WeakReference<OrderView> weak ;

    public OrderPresenter(OrderView orderView) {
        this.orderModle = new OrderModle();
        orderModle.setLoadData(this);
        this.weak = new WeakReference<OrderView>(orderView);
    }

    public void deatch(){
        weak.clear();
    }
    public void getData(Context context,String uid){
        orderModle.getData(context,uid);
    }
    @Override
    public void Success(OrderBean basebean) {
    weak.get().Success(basebean);
    }

}
