package lenovo.example.com.myjdapp.module;


import android.util.Log;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class ChuangJianMode {

    public void getData(String uid,String price){
        String url = Api.CHUANGJIAN + "?uid=" + uid+ "&price=" + price ;
        //log测试输出
                Log.d("liuhao","string: " + url);
        HttpOkUntils.getHttpUtils().OkGet(url, BaseBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                chuangj.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                chuangj.Error(baseBean);
            }
        });

    }
    chuangj chuangj ;

    public void setChuangj(ChuangJianMode.chuangj chuangj) {
        this.chuangj = chuangj;
    }

    public interface chuangj {

        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);

    }
}
