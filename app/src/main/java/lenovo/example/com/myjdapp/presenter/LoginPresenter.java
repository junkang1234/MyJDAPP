package lenovo.example.com.myjdapp.presenter;


import android.text.TextUtils;

import lenovo.example.com.myjdapp.bean.LoginBean;
import lenovo.example.com.myjdapp.module.LoginModle;
import lenovo.example.com.myjdapp.view.LoginView;

public class LoginPresenter implements LoginModle.LoginI {

    private LoginModle loginModle ;
    private LoginView loginView ;
    public LoginPresenter(LoginView loginView) {
        loginModle = new LoginModle();
        this.loginView = loginView;
        loginModle.setLogini(this);
    }

    public void login(String mobile,String pwd){
        if(TextUtils.isEmpty(mobile)){
            loginView.nameError("用户名不能为空");
            return;
        }else if(TextUtils.isEmpty(pwd)){
            loginView.passError("密码不能为空");
            return;
        }
        loginModle.Login(mobile,pwd);
    }
    @Override
    public void loginSuccess(LoginBean baseBean) {
        loginView.loginSuccess(baseBean);
    }
    @Override
    public void loginFail(LoginBean baseBean) {
        loginView.loginFail(baseBean);
    }
}
