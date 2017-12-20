package lenovo.example.com.myjdapp.presenter;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.SelectCartMode;
import lenovo.example.com.myjdapp.view.SelectCartView;

public class SelectCartPresenter implements SelectCartMode.selectDataApi {


    private SelectCartView selectCartView ;
    private SelectCartMode selectCartMode ;

    public SelectCartPresenter(SelectCartView selectCartView) {
        this.selectCartView = selectCartView;
        this.selectCartMode = new SelectCartMode();
        selectCartMode.setSelectDataApi(this);
    }

    public void selectData(String uid){
        selectCartMode.SelectData(uid);
    }

    @Override
    public void Success(BaseBean baseBean) {
        selectCartView.Success(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
        selectCartView.Error(baseBean);
    }
}
