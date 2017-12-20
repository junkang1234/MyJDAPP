package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.HuoQuBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class HuoQuModle {

    public void getData(String uid){

        HttpOkUntils.getHttpUtils().OkGet(Api.GET_MOREN_ADDRESS+"?uid="+uid, HuoQuBean.class, new NetClickListener() {
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

    public void setGetDataApi(HuoQuModle.getDataApi getDataApi) {
        this.getDataApi = getDataApi;
    }

    public interface getDataApi{
        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);
    }

}
