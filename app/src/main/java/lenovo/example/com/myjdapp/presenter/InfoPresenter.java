package lenovo.example.com.myjdapp.presenter;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.InfoMode;
import lenovo.example.com.myjdapp.view.InfoView;
public class InfoPresenter implements InfoMode.getinfoapi {
    private InfoMode infoMode ;
    private InfoView infoView ;

    public InfoPresenter(InfoView infoView) {
        this.infoMode = new InfoMode();
        this.infoView = infoView;
        infoMode.setGetinfoapi(this);
    }
    public void getInfo(int uid){
        infoMode.getInfo(uid);
    }
    @Override
    public void Success(BaseBean baseBean) {
            infoView.Success(baseBean);
    }


    @Override
    public void Error(String msg) {
            infoView.Error(msg);
    }
}
