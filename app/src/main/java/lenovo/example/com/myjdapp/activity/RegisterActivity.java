package lenovo.example.com.myjdapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.presenter.RegisterPresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.view.RegisterView;


public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private EditText editname;
    private EditText editpwd;
    private RegisterPresenter registerPresenter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        initeView();

        initData();
    }

    private void initData() {

        registerPresenter = new RegisterPresenter(this);

    }

    private void initeView() {

        editname = (EditText) findViewById(R.id.editname);
        editpwd = (EditText) findViewById(R.id.editpwd);
        tv = (TextView) findViewById(R.id.exit_login);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUntils.setIntent(RegisterActivity.this,UserLoginActivity.class);
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
    }

    public void register(View v){
       registerPresenter.register(editname.getText().toString(),editpwd.getText().toString());
    }

    @Override
    public void nameError(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passError(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess(String code, String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(R.anim.leftone, R.anim.rightone);
    }

    @Override
    public void registerFail(String code, String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
