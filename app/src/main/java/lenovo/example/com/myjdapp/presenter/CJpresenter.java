package lenovo.example.com.myjdapp.presenter;


import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.module.ChuangJianMode;
import lenovo.example.com.myjdapp.view.ChuanJianview;

public class CJpresenter implements ChuangJianMode.chuangj {

    private ChuangJianMode chuangJianMode ;
    private ChuanJianview chuanJianview ;

    public CJpresenter( ChuanJianview chuanJianview) {
        this.chuangJianMode = new ChuangJianMode();
        this.chuanJianview = chuanJianview;
        chuangJianMode.setChuangj(this);
    }

    public void getData(String uid,String price){

        chuangJianMode.getData(uid,price);

    }

    @Override
    public void Success(BaseBean baseBean) {
            chuanJianview.Success1(baseBean);
    }

    @Override
    public void Error(BaseBean baseBean) {
            chuanJianview.Error1(baseBean);
    }
}
