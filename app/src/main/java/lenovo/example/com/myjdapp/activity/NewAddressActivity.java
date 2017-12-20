package lenovo.example.com.myjdapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.presenter.NewsPresenter;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.TakeView;


public class NewAddressActivity extends AppCompatActivity implements TakeView {

    private ImageView i;
    private EditText edit_name;
    private EditText edit_mobile;
    private EditText edit_address;
    private Button btn;
    private NewsPresenter newsPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        getSupportActionBar().hide();
        initView();
        exit();
        fanhui();
    }

    private void fanhui() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 newsPresenter.getData(SpUtils.getSpUid(NewAddressActivity.this)+""
                        ,edit_address.getText().toString().trim()
                        ,edit_mobile.getText().toString().trim()
                        ,edit_name.getText().toString().trim());
            }
        });
    }

    private void initView() {
        i = (ImageView) findViewById(R.id.image_left);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_mobile = (EditText) findViewById(R.id.edit_mobile);
        edit_address = (EditText) findViewById(R.id.edit_address);
        btn = (Button) findViewById(R.id.btn_caocun);
        newsPresenter = new NewsPresenter(this);
    }
    public void exit (){

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });

    }

    @Override
    public void Success(BaseBean baseBean) {
        //吐司事件
        Toast.makeText(NewAddressActivity.this, baseBean.getMsg(),Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(R.anim.leftone, R.anim.rightone);
    }

    @Override
    public void Error(BaseBean baseBean) {
         //吐司事件
          Toast.makeText(NewAddressActivity.this, baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsPresenter.deatch();
    }
}
