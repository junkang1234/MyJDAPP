package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;


public class NewsModle {

    public void getDaa(String uid,String addr,String mobile,String name){

        HttpOkUntils.getHttpUtils().OkGet(Api.NEWS_ADDRESS + "?uid=" + uid + "&addr="+addr+"&mobile="+mobile+"&name="+name, BaseBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                loaddate.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                loaddate.Error(baseBean);
            }
        });

    }

    loaddate loaddate ;

    public void setLoaddate(NewsModle.loaddate loaddate) {
        this.loaddate = loaddate;
    }

    public interface loaddate{

        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);

    };

}
