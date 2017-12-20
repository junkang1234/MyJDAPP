package lenovo.example.com.myjdapp.presenter;

import java.lang.ref.WeakReference;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.NewsModle;
import lenovo.example.com.myjdapp.view.TakeView;


public class NewsPresenter implements NewsModle.loaddate{

    private NewsModle takeModle ;
    private WeakReference<TakeView> weak ;

    public NewsPresenter(TakeView takeView) {
        this.takeModle = new NewsModle();
        takeModle.setLoaddate(this);
        weak = new WeakReference<TakeView>(takeView);
    }

    public void getData(String uid,String addr,String mobile,String name){
         takeModle.getDaa(uid,addr,mobile,name);
    }

    @Override
    public void Success(BaseBean baseBean) {
        weak.get().Success(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
        weak.get().Error(baseBean);
    }
    public void deatch(){
        weak.clear();
    }

}
