package lenovo.example.com.myjdapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.app.MyApp;
import lenovo.example.com.myjdapp.bean.LoginBean;
import lenovo.example.com.myjdapp.presenter.LoginPresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.untils.ToastUntils;
import lenovo.example.com.myjdapp.view.LoginView;


public class UserLoginActivity extends AppCompatActivity implements LoginView {

    private TextView exit_login;
    private EditText edname;
    private EditText edname1;
    private Button btnlogin;
    private TextView tvzhuche;
    private ImageView qqlogin;
    private TextView tvforgrtmima;
    private MyApp app;
    private LoginPresenter presenter ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        app = (MyApp) getApplication();
        getSupportActionBar().hide();
        initView();

    }

    private void initView() {

        exit_login = (TextView) findViewById(R.id.exit_login);
        edname = (EditText) findViewById(R.id.editname);
        edname1 = (EditText) findViewById(R.id.editpwd);
        btnlogin = (Button) findViewById(R.id.buttonlogin);
        tvzhuche = (TextView) findViewById(R.id.zhuche);
        tvforgrtmima = (TextView) findViewById(R.id.zhaohuimima);
        qqlogin = (ImageView) findViewById(R.id.qqlogin);
        tvforgrtmima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUntils.setToast(UserLoginActivity.this,"本京东暂时无法提供找回密码服务");
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter = new LoginPresenter(UserLoginActivity.this);
                presenter.login(edname.getText().toString(),edname1.getText().toString());
            }
        });
        exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
        tvzhuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUntils.setIntent(UserLoginActivity.this,RegisterActivity.class);
            }
        });
        qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI umShareAPI = app.getUmShareAPI();
                umShareAPI.getPlatformInfo(UserLoginActivity.this, SHARE_MEDIA.SINA, authListener);
            }
        });
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(UserLoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(UserLoginActivity.this, "失败：" + t.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(UserLoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void nameError(String msg) {
        ToastUntils.setToast(UserLoginActivity.this,msg);
    }

    @Override
    public void passError(String msg) {
        ToastUntils.setToast(UserLoginActivity.this,msg);
    }

    @Override
    public void loginSuccess(LoginBean baseBean) {
        ToastUntils.setToast(UserLoginActivity.this,baseBean.getMsg());
        SpUtils.getSp(UserLoginActivity.this,baseBean.getData().getUid(),true);
        finish();
        overridePendingTransition(R.anim.leftone, R.anim.rightone);
    }

    @Override
    public void loginFail(LoginBean baseBean) {
        ToastUntils.setToast(UserLoginActivity.this,baseBean.getMsg());
    }

}
