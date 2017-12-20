package lenovo.example.com.myjdapp.presenter;


import java.lang.ref.WeakReference;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.HuoQuModle;
import lenovo.example.com.myjdapp.view.HuoQuView;

public class HuoQupresenter implements HuoQuModle.getDataApi {

    private HuoQuModle chuangJianMode ;
    private WeakReference<HuoQuView> weak ;

    public HuoQupresenter(HuoQuView chuanJianview) {
        this.chuangJianMode = new HuoQuModle();
        this.weak = new WeakReference<HuoQuView>(chuanJianview);
        chuangJianMode.setGetDataApi(this);
    }

    public void getData(String uid){

        chuangJianMode.getData(uid);

    }

    public void detaach (){
        weak.clear();
    }

    @Override
    public void Success(BaseBean baseBean) {
            weak.get().Success(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
        weak.get().Error(baseBean);
    }
}
