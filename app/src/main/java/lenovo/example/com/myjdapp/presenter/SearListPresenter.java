package lenovo.example.com.myjdapp.presenter;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.SearchListmode;
import lenovo.example.com.myjdapp.view.SearListView;

public class SearListPresenter implements SearchListmode.searListapi {

    private SearchListmode searchListmode ;
    private SearListView searListView ;

    public SearListPresenter(SearListView searListView) {
        this.searchListmode = new SearchListmode();
        this.searListView = searListView;
        searchListmode.setSearListapi(this);
    }

    public void getData(String ketwords){
        searchListmode.getData(ketwords);
    }

    @Override
    public void Success(BaseBean baseBean) {
        searListView.Success(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
        searListView.Error(baseBean);
    }

}
