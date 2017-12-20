package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.TakeGoodsBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class TakeModle {

    public void getDaa(String uid){

        HttpOkUntils.getHttpUtils().OkGet(Api.SELECT_ADDRESS + "?uid=" + uid, TakeGoodsBean.class, new NetClickListener() {
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

    public void setLoaddate(TakeModle.loaddate loaddate) {
        this.loaddate = loaddate;
    }

    public interface loaddate{

        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);

    };

}
