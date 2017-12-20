package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class AddCartMode {

    public void addData(String uid,String pid){
      String url =  Api.ADD_CART_API+"?uid=" + uid + "&pid="+pid;
        HttpOkUntils.getHttpUtils().OkGet(url, BaseBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                    getDataApi.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                    getDataApi.Error(baseBean);
            }
        });

    }

    public getDataApi getDataApi ;

    public void setGetDataApi(AddCartMode.getDataApi getDataApi) {
        this.getDataApi = getDataApi;
    }

    public interface getDataApi{
        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);
    }


}
