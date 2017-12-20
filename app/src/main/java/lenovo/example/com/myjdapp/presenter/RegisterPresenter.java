package lenovo.example.com.myjdapp.presenter;

import android.text.TextUtils;

import lenovo.example.com.myjdapp.module.RegisterModle;
import lenovo.example.com.myjdapp.view.RegisterView;


public class RegisterPresenter implements RegisterModle.RegisterR{

    private RegisterModle registerModle ;
    private RegisterView registerView ;

    public RegisterPresenter(RegisterView registerView) {
        registerModle = new RegisterModle();
        this.registerView = registerView;
        registerModle.setRegisterR(this);
    }

    public void register(String mobile,String pwd){
        if(TextUtils.isEmpty(mobile)){
            registerView.nameError("用户名不能为空");
            return;
        }else if(TextUtils.isEmpty(pwd)){
            registerView.passError("密码不能为空");
            return;
        }
        registerModle.Register(mobile,pwd);
    }

    @Override
    public void registerSuccess(String code, String msg) {
        registerView.registerSuccess(code,msg);
    }

    @Override
    public void registerFail(String code, String msg) {
        registerView.registerFail(code,msg);
    }
}
