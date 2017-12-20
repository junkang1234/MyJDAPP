package lenovo.example.com.myjdapp.module;


import android.util.Log;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.ShopListBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class ShopListMode {

    private shopListapi shopListapi ;

    public void setShopListapi(ShopListMode.shopListapi shopListapi) {
        this.shopListapi = shopListapi;
    }

    public void getData(int pscid, int page){
            String url = Api.SHOP_LISTVIEW+"?"+"pscid="+pscid+"&"+"page="+page;
        //log测试输出
                Log.d("qq","url:" + url);
        HttpOkUntils.getHttpUtils().OkGet(url, ShopListBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                shopListapi.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                shopListapi.Error(baseBean);
            }
        });

    }

    public interface shopListapi{
        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);
    }
}
