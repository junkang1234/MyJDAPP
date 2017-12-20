package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class MorenAddressMode {

    public void getData(String uid,String addrid,String status){

        HttpOkUntils.getHttpUtils().OkGet(Api.MOREN_ADDRESS + "?uid=" + uid + "&addrid=" + addrid + "&status=" + status, BaseBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                    loaddata.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                    loaddata.Error(baseBean);
            }
        });

    }

    loaddata loaddata ;

    public void setLoaddata(MorenAddressMode.loaddata loaddata) {
        this.loaddata = loaddata;
    }

    public  interface loaddata{
        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);
    };

}
