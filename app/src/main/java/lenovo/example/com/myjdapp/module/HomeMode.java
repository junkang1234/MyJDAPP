package lenovo.example.com.myjdapp.module;


import android.util.Log;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.FenLeiBean;
import lenovo.example.com.myjdapp.bean.HomeBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class HomeMode {

    private HomeData homedata ;
    private HomeData1 homedata1 ;



    public void HomeLoadData(){

        HttpOkUntils.getHttpUtils().OkGet(Api.SHOU_YE_BANNER_API, HomeBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                //log测试输出
                        Log.d("qq","请求的code值:"+baseBean.getCode());
                homedata.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                homedata.Erroe(baseBean);
            }
        });

    }

    public void HomeLoadData1(){

        HttpOkUntils.getHttpUtils().OkGet(Api.SHOP_FENLEI_INFO_API, FenLeiBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                //log测试输出
                Log.d("qq","请求的code值:"+baseBean.getCode());
                homedata1.Success1(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                homedata1.Erroe1(baseBean);
            }
        });

    }

    public void setHomedata(HomeData homedata) {
        this.homedata = homedata;
    }

    public void setHomedata1(HomeData1 homedata1) {
        this.homedata1 = homedata1;
    }

    public interface HomeData {

        void Success(BaseBean baseBean);

        void Erroe(BaseBean baseBean);
    }
    public interface HomeData1 {

        void Success1(BaseBean baseBean);

        void Erroe1(BaseBean baseBean);
    }
}
