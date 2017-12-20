package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class UpdateStaOne  {

    public void getData(String uid,String status,String orderid){

        HttpOkUntils.getHttpUtils().OkGet(Api.UPDATE_ORDER_STATUS + "?uid=" + uid + "&status=" + status + "&orderid=" + orderid, BaseBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                loadData.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
loadData.Error(baseBean);
            }
        });

    }

    LoadData loadData ;

    public void setLoadData(LoadData loadData) {
        this.loadData = loadData;
    }

    public interface LoadData{
        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);
    }


}
