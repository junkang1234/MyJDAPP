package lenovo.example.com.myjdapp.presenter;

import java.lang.ref.WeakReference;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.MorenAddressMode;
import lenovo.example.com.myjdapp.view.MorenView;


public class MorenPresenter implements MorenAddressMode.loaddata {

    private MorenAddressMode morenAddressMode ;
    private WeakReference<MorenView> weak ;

    public MorenPresenter(MorenView morenView) {
        this.morenAddressMode = new MorenAddressMode();
        morenAddressMode.setLoaddata(this);
        this.weak = new WeakReference<MorenView>(morenView);
    }



    public  void getData(String uid,String addrid,String status){

        morenAddressMode.getData(uid,addrid,status);

    }

    @Override
    public void Success(BaseBean baseBean) {
            weak.get().Suceess(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
        weak.get().Error(baseBean);
    }
    public void deatch(){
        weak.clear();
    }
}
