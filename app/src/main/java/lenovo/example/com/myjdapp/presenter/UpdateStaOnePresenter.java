package lenovo.example.com.myjdapp.presenter;

import java.lang.ref.WeakReference;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.UpdateStaOne;
import lenovo.example.com.myjdapp.view.UpdateStaOneView;


public class UpdateStaOnePresenter implements UpdateStaOne.LoadData {

    private UpdateStaOne updateStaOne ;
    private WeakReference<UpdateStaOneView> weak ;

    public UpdateStaOnePresenter(UpdateStaOneView updateStaOneView) {
        this.updateStaOne = new UpdateStaOne();
        updateStaOne.setLoadData(this);
        weak = new WeakReference<UpdateStaOneView>(updateStaOneView);
    }

    public void getData(String uid,String status,String orderid){
        updateStaOne.getData(uid,status,orderid);
    }

    public void deatch(){
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
