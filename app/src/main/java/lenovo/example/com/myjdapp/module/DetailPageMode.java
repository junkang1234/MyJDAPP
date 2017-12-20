package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.DetailPageBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class DetailPageMode {

    public void geteData(int pid){

        HttpOkUntils.getHttpUtils().OkGet(Api.SHOP_XINXI_API + "?pid=" + pid, DetailPageBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                detailPageApi.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                detailPageApi.Error(baseBean);
            }
        });

    }

    private detailPageApi detailPageApi ;

    public void setDetailPageApi(DetailPageMode.detailPageApi detailPageApi) {
        this.detailPageApi = detailPageApi;
    }

    public interface detailPageApi{
        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);
    }


}
