package lenovo.example.com.myjdapp.presenter;

import java.lang.ref.WeakReference;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.UpdataNickName;
import lenovo.example.com.myjdapp.view.UpdataNickNameView;


public class UpdataNickPresenter implements UpdataNickName.GetData {

    private UpdataNickName nickName ;
    private WeakReference<UpdataNickNameView> weak ;

    public UpdataNickPresenter(UpdataNickNameView view) {
        this.nickName = new UpdataNickName();
        nickName.setGetData(this);
        weak = new WeakReference<UpdataNickNameView>(view);
    }

    public void updata(String uid,String name){
        nickName.updataName(uid,name);
    }

    @Override
    public void Success(BaseBean baseBean) {
        weak.get().UpdataSuccess(baseBean);
    }
    public void detach(){
        weak.clear();
    }
}
