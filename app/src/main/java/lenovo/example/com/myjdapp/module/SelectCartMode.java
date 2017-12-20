package lenovo.example.com.myjdapp.module;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.SelectBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class SelectCartMode {

    public void SelectData(String uid){
        String url = Api.SELECT_CART_API + "?uid=" + uid+ "&source=" + "android"+"&token=" + "0";
        HttpOkUntils.getHttpUtils().OkGet(url, SelectBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                selectDataApi.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                selectDataApi.Error(baseBean);
            }
        });

    }

    public selectDataApi selectDataApi ;

    public void setSelectDataApi(SelectCartMode.selectDataApi selectDataApi) {
        this.selectDataApi = selectDataApi;
    }

    public interface selectDataApi{

        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);

    }


}
