package lenovo.example.com.myjdapp.presenter;

import java.lang.ref.WeakReference;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.TakeModle;
import lenovo.example.com.myjdapp.view.TakeView;


public class TakePresenter implements TakeModle.loaddate{

    private TakeModle takeModle ;
    private WeakReference<TakeView> weak ;

    public TakePresenter(TakeView takeView) {
        this.takeModle = new TakeModle();
        takeModle.setLoaddate(this);
        weak = new WeakReference<TakeView>(takeView);
    }

    public void getData(String uid){
         takeModle.getDaa(uid);
    }

    @Override
    public void Success(BaseBean baseBean) {
        if(baseBean!=null){
            weak.get().Success(baseBean);
        }
    }

    @Override
    public void Error(BaseBean baseBean) {
        weak.get().Error(baseBean);
    }
    public void deatch(){
        weak.clear();
    }

}
