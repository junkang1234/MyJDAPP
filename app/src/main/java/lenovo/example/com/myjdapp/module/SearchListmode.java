package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.SearListBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class SearchListmode {

    public void getData(String keywords){

        HttpOkUntils.getHttpUtils().OkGet(Api.SEARCH_API + "?keywords=" + keywords + "&source=android", SearListBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                searListapi.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {
                searListapi.Error(baseBean);
            }
        });

    }

    private searListapi searListapi ;

    public void setSearListapi(SearchListmode.searListapi searListapi) {
        this.searListapi = searListapi;
    }

    public interface searListapi{
        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);
    }

}
