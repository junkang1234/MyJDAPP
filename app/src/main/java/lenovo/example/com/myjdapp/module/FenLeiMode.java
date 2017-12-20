package lenovo.example.com.myjdapp.module;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.FenLeiChildBean;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.untils.HttpOkUntils;
import lenovo.example.com.myjdapp.untils.NetClickListener;

public class FenLeiMode {

    public void getData(int cid){

        HttpOkUntils.getHttpUtils()
                .OkGet(Api.FENLEI_CHILD_API+"?"+"cid="+cid, FenLeiChildBean.class, new NetClickListener() {
                    @Override
                    public void Suesses(BaseBean baseBean) {
                        fenLeiapi.Success(baseBean);
                    }

                    @Override
                    public void Error(BaseBean baseBean) {
                        fenLeiapi.Error(baseBean);
                    }
                });

    }

    private FenLeiapi fenLeiapi ;

    public void setFenLeiapi(FenLeiapi fenLeiapi) {
        this.fenLeiapi = fenLeiapi;
    }

    public interface FenLeiapi{

        void Success(BaseBean baseBean);
        void Error(BaseBean baseBean);

    }


}
