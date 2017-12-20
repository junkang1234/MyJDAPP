package lenovo.example.com.myjdapp.presenter;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.FenLeiMode;
import lenovo.example.com.myjdapp.view.FenLeiView;

public class FenLeiPresenter implements FenLeiMode.FenLeiapi {

    private FenLeiMode fenLeiMode ;
    public FenLeiView fenLeiView ;

    public FenLeiPresenter(FenLeiView fenLeiView) {
        this.fenLeiMode = new FenLeiMode();
        this.fenLeiView = fenLeiView;
        fenLeiMode.setFenLeiapi(this);
    }

    public void getData(int cid){

        fenLeiMode.getData(cid);

    }


    @Override
    public void Success(BaseBean baseBean) {
            fenLeiView.Success(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
            fenLeiView.Error(baseBean);
    }
}
