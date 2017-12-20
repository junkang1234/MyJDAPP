package lenovo.example.com.myjdapp.presenter;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.AddCartMode;
import lenovo.example.com.myjdapp.view.AddCartView;

public class AddCartPresenter implements AddCartMode.getDataApi{

    private AddCartMode addCartMode ;
    private AddCartView addCartView ;

    public AddCartPresenter(AddCartView addCartView) {
        this.addCartMode = new AddCartMode();
        this.addCartView = addCartView;
        addCartMode.setGetDataApi(this);
    }

    public void addData(String uid,String pid){
        addCartMode.addData(uid,pid);
    }

    @Override
    public void Success(BaseBean baseBean) {
        addCartView.addSuccess(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
        addCartView.addError(baseBean);
    }
}
