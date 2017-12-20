package lenovo.example.com.myjdapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.adapter.MyTakeAdapter;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.TakeGoodsBean;
import lenovo.example.com.myjdapp.presenter.TakePresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.TakeView;


public class TakeGoodsActivity extends AppCompatActivity implements TakeView {

    private ImageView i;
    private RecyclerView recy;
    private Button btn_build;
    private TakePresenter takepresnter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_goods);
        getSupportActionBar().hide();
        initView();
        exit();
        news();
    }
    /**
     * 退出
     */
    public void exit (){

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });

    }

    /**
     * 新建收货地址
     */
    public void news(){

        btn_build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUntils.setIntent(TakeGoodsActivity.this,NewAddressActivity.class);
            }
        });

    }

    private void initView() {

        i = (ImageView) findViewById(R.id.image_left);
        recy = (RecyclerView) findViewById(R.id.recy);
        btn_build = (Button) findViewById(R.id.build);
        takepresnter = new TakePresenter(this);

    }


    @Override
    public void Success(BaseBean baseBean) {

          TakeGoodsBean taebean  = (TakeGoodsBean) baseBean;
        List<TakeGoodsBean.DataBean> data = taebean.getData();

        recy.setLayoutManager(new LinearLayoutManager(TakeGoodsActivity.this,LinearLayoutManager.VERTICAL,false));
        recy.addItemDecoration(new DividerItemDecoration(TakeGoodsActivity.this,DividerItemDecoration.VERTICAL));
        MyTakeAdapter takeAdapter = new MyTakeAdapter(data,TakeGoodsActivity.this);
        recy.setAdapter(takeAdapter);
    }

    @Override
    public void Error(BaseBean baseBean) {
         //吐司事件
          Toast.makeText(TakeGoodsActivity.this, baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        takepresnter.getData(SpUtils.getSpUid(TakeGoodsActivity.this)+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        takepresnter.deatch();
    }
}
