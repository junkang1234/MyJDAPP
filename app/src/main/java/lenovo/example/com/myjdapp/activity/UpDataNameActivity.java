package lenovo.example.com.myjdapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.presenter.UpdataNickPresenter;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.UpdataNickNameView;


public class UpDataNameActivity extends AppCompatActivity implements UpdataNickNameView {

    private TextView tvok;
    private ImageView i;
    private EditText edit;
    private UpdataNickPresenter up ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_data_name);
        getSupportActionBar().hide();
        initView();
        if(getIntent().getStringExtra("key1")!=null){
            edit.setText(getIntent().getStringExtra("key1"));
        }
        tvok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    up.updata(SpUtils.getSpUid(UpDataNameActivity.this)+"",edit.getText().toString());
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
    }

    private void initView() {
        up = new UpdataNickPresenter(this);
        tvok = (TextView) findViewById(R.id.tv_ok);
        i = (ImageView) findViewById(R.id.image_left);
        edit = (EditText) findViewById(R.id.edit_nick);
    }

    @Override
    public void UpdataSuccess(BaseBean baseBean) {
         //吐司事件
          Toast.makeText(UpDataNameActivity.this, baseBean.getMsg(),Toast.LENGTH_SHORT).show();
          finish();
            overridePendingTransition(R.anim.leftone, R.anim.rightone);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        up.detach();
    }
}
