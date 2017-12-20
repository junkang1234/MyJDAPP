package lenovo.example.com.myjdapp.presenter;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.ShopListMode;
import lenovo.example.com.myjdapp.view.ShopListView;

public class ShopListPresenter implements ShopListMode.shopListapi{

    private ShopListView shopListView ;
    private ShopListMode shopListMode ;

    public ShopListPresenter(ShopListView shopListView) {
        this.shopListView = shopListView;
        this.shopListMode = new ShopListMode();
        shopListMode.setShopListapi(this);
    }

    public void getData(int pscid,int page){
            shopListMode.getData(pscid,page);
    }

    @Override
    public void Success(BaseBean baseBean) {
            shopListView.Success(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
            shopListView.Error(baseBean);
    }
}
