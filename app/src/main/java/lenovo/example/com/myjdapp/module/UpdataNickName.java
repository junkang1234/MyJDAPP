package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class UpdataNickName {

    public void updataName(String uid,String nickname){

        HttpOkUntils.getHttpUtils().OkGet(Api.UPDATA_NICKNAME+"?uid="+uid+"&nickname="+nickname+"&token="+"0", BaseBean.class, new NetClickListener() {
            @Override
            public void Suesses(BaseBean baseBean) {
                getData.Success(baseBean);
            }

            @Override
            public void Error(BaseBean baseBean) {

            }
        });

    }

    GetData getData ;

    public void setGetData(GetData getData) {
        this.getData = getData;
    }

    public interface GetData{
        void Success(BaseBean baseBean);
    }

}
