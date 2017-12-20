package lenovo.example.com.myjdapp.view;


import lenovo.example.com.myjdapp.bean.BaseBean;

public interface InfoView {

    void Success(BaseBean baseBean);
    void Error(String msg);

}
