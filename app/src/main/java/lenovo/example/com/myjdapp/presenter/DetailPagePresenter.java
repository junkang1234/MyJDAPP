package lenovo.example.com.myjdapp.presenter;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.DetailPageMode;
import lenovo.example.com.myjdapp.view.DetailPageView;

public class DetailPagePresenter implements DetailPageMode.detailPageApi {

    private DetailPageMode detailPageMode ;
    private DetailPageView detailPageView ;

    public DetailPagePresenter(DetailPageView detailPageView) {
        this.detailPageMode = new DetailPageMode();
        this.detailPageView = detailPageView;
        detailPageMode.setDetailPageApi(this);
    }

    public void getData(int pid){
        detailPageMode.geteData(pid);
    }

    @Override
    public void Success(BaseBean baseBean) {
        detailPageView.Success(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
        detailPageView.Error(baseBean);
    }
}
