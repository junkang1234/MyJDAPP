package lenovo.example.com.myjdapp.view;

import lenovo.example.com.myjdapp.bean.LoginBean;

public interface LoginView {


    void nameError(String msg);

    void passError(String msg);

    void loginSuccess(LoginBean baseBean);

    void loginFail(LoginBean baseBean);





}