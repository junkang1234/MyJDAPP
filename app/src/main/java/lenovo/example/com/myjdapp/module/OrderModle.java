package lenovo.example.com.myjdapp.module;

import android.content.Context;

import lenovo.example.com.myjdapp.bean.OrderBean;
import lenovo.example.com.myjdapp.untils.RetrofitUntils;
import lenovo.example.com.myjdapp.view.NetClickListener1;


public class OrderModle {

    public void getData(Context context, String uid){

        RetrofitUntils.getRetrofitUntils(context).get(uid, new NetClickListener1() {
            @Override
            public void Suesses(OrderBean baseBean) {
               loadData.Success(baseBean);
            }
        });

    }

    loadData loadData ;

    public void setLoadData(OrderModle.loadData loadData) {
        this.loadData = loadData;
    }

    public interface loadData{

        void Success(OrderBean basebean);

    }

}
