package lenovo.example.com.myjdapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bawei.pay.PayDemoActivity;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.OrderBean;
import lenovo.example.com.myjdapp.presenter.OrderPresenter;
import lenovo.example.com.myjdapp.presenter.UpdateStaOnePresenter;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.OrderView;
import lenovo.example.com.myjdapp.view.UpdateStaOneView;


public class FuKuanActivity extends AppCompatActivity implements UpdateStaOneView,OrderView {

    private UpdateStaOnePresenter updateStaOnePresenter ;
    private OrderPresenter orderpresenter ;
    private List<OrderBean.DataBean> data = new ArrayList<>();
    private ImageView i;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_kuan);

        initView();
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zhifu();
            }
        });
    }

    public void zhifu(){

        Intent intent = new Intent(FuKuanActivity.this, PayDemoActivity.class);
        startActivity(intent);

    }
    public void showDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("确认要离开京东收银台？");
        alert.setMessage("您的订单将在24个小时内被取消，请尽快完成支付。");
        alert.setNegativeButton("继续支付",null);
        alert.setPositiveButton("确认离开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
        alert.show();
    }
    //捕捉返回键
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){

            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {//按键的按下事件
                showDialog();
                return  false ;
            }
        }
        return super.dispatchKeyEvent(event);
    }
    private void initView() {

        updateStaOnePresenter =     new UpdateStaOnePresenter(this);
        orderpresenter = new OrderPresenter(this);
        i = (ImageView) findViewById(R.id.image_exit);
        bt = (Button) findViewById(R.id.zhifua);
    }

    public String getPrice(){
        return getIntent().getStringExtra("pricea");
    }

    @Override
    protected void onResume() {
        super.onResume();
        orderpresenter.getData(FuKuanActivity.this, SpUtils.getSpUid(FuKuanActivity.this)+"");
    }

    @Override
    public void Success(BaseBean baseBean) {

    }

    @Override
    public void Error(BaseBean baseBean) {

    }

    @Override
    public void Success(OrderBean baseBean) {
        List<OrderBean.DataBean> data = baseBean.getData();
//        updateStaOnePresenter.getData(SpUtils.getSpUid(FuKuanActivity.this)+"","0",);

    }
}
