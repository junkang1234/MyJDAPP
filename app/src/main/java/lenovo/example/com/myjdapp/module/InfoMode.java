package lenovo.example.com.myjdapp.module;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.InfoBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;
public class InfoMode {

    public  void getInfo(int uid){
        if(uid==0){
            getinfoapi.Error("uid==0");
        }else {
            HttpOkUntils.getHttpUtils().OkGet(Api.USER_INFO_API + "?uid=" + uid, InfoBean.class, new NetClickListener() {
                @Override
                public void Suesses(BaseBean baseBean) {
                        getinfoapi.Success(baseBean);
                }

                @Override
                public void Error(BaseBean baseBean) {
                        getinfoapi.Error(baseBean.getMsg());
                }
            });
        }
        }


    private getinfoapi getinfoapi ;

    public void setGetinfoapi(InfoMode.getinfoapi getinfoapi) {
        this.getinfoapi = getinfoapi;
    }

    public interface getinfoapi{
        void Success(BaseBean baseBean);
        void Error(String msg);
    }

}
