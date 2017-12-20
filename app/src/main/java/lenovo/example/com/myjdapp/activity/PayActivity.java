package lenovo.example.com.myjdapp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.HuoQuBean;
import lenovo.example.com.myjdapp.bean.ShopBean;
import lenovo.example.com.myjdapp.presenter.CJpresenter;
import lenovo.example.com.myjdapp.presenter.HuoQupresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.ChuanJianview;
import lenovo.example.com.myjdapp.view.HuoQuView;


public class PayActivity extends AppCompatActivity implements ChuanJianview,HuoQuView {


    private TextView name;
    private ImageView i;
    private ImageView i1;
    private TextView t1name;
    private TextView t1price;
    private TextView t1num;
    private LinearLayout linear;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private TextView t1_gong;
    private TextView tv;
    private Button btn;
    private CJpresenter cJpresenter ;
    private TextView address;
    private HuoQupresenter huoQupresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_pay);
        getSupportActionBar().hide();
        List<ShopBean> shopList = getShopList();
        initView();
        showName();
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
        }
        });
        showShopInfo(shopList);
        xiadan();
    }

    @Override
    protected void onResume() {
        super.onResume();

        huoQupresenter.getData(SpUtils.getSpUid(PayActivity.this)+"");

    }

    private void xiadan() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cJpresenter.getData(SpUtils.getSpUid(PayActivity.this)+"",getThisPrice());
            }
        });

    }

    private void showShopInfo(List<ShopBean> shopList) {
            if(shopList.size()==1){
                linear1.setVisibility(View.VISIBLE);
                linear.setVisibility(View.GONE);
                ImageLoader.getInstance().displayImage(shopList.get(0).getImage(),i1);
                t1name.setText(shopList.get(0).getName());
                String price = shopList.get(0).getPrice();
                String num = shopList.get(0).getNum();
                float a = Float.parseFloat(price)*Float.parseFloat(num);

                t1price.setText("￥" + shopList.get(0).getPrice());
                t1num.setText("×"+shopList.get(0).getNum());
                tv.setText("实付款:￥" + a);
            }else {
                linear1.setVisibility(View.GONE);
                linear.setVisibility(View.VISIBLE);
                int num = 0 ;
                float price = 0 ;
                //log测试输出
                        Log.d("qq","长度：" + shopList.size());
                for (int i=0;i<shopList.size();i++){
                    ImageView i1 = new ImageView(this);
                    i1.setPadding(10,10,10,10);
                    i1.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
                    ImageLoader.getInstance().displayImage(shopList.get(i).getImage(),i1);
                    num+=Integer.parseInt(shopList.get(i).getNum());
                    linear2.addView(i1);
                    String num1 = shopList.get(i).getNum();
                    String price1 = shopList.get(i).getPrice();
                    float i2 = Float.parseFloat(num1) * Float.parseFloat(price1);
                    price   +=  i2 ;
                }
                float i = Math.round(price * 100) / 100;
                t1_gong.setText("共" + num + "件");
                tv.setText("实付款:￥" + i);
            }
    }

    public List<ShopBean> getShopList(){
       return (List<ShopBean>) getIntent().getSerializableExtra("shoplist");
    }
    private void showName() {
        String spName = SpUtils.getSpName(PayActivity.this);
        String spPhone = SpUtils.getSpPhone(PayActivity.this);
        if(!TextUtils.isEmpty(spPhone) && spPhone.length() == 11 ){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < spPhone.length(); i++) {
                char c = spPhone.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            name.setText(spName + "       "+ sb.toString());
        }
    }

    private void initView() {
        address = (TextView) findViewById(R.id.address);
        name = (TextView) findViewById(R.id.name_phone);
        i1 = (ImageView) findViewById(R.id.i1);
        i = (ImageView) findViewById(R.id.image_exit);
        t1name = (TextView) findViewById(R.id.t1_name);
        t1price = (TextView) findViewById(R.id.t1_price);
        t1num = (TextView) findViewById(R.id.t1_num);
        linear = (LinearLayout) findViewById(R.id.l4);
        linear2 = (LinearLayout) findViewById(R.id.l5);
        linear1 = (LinearLayout) findViewById(R.id.l3);
        t1_gong = (TextView) findViewById(R.id.tv_gong);
        tv = (TextView) findViewById(R.id.price);
        btn = (Button) findViewById(R.id.btn_jiesuan);
        cJpresenter = new CJpresenter(this);
        huoQupresenter = new HuoQupresenter(this);
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

    public void showDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("便宜不等人，请三思而行～");
        alert.setNegativeButton("我再想想",null);
        alert.setPositiveButton("去意已决", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
        alert.show();
    }

    public String getThisPrice(){

        String a =   tv.getText().toString();
        String[] split = a.split("实付款:￥");
        StringBuffer sb = new StringBuffer();
        for (String s : split){
            sb.append(s);
        }
        return sb.toString() ;
    }

    @Override
    public void Success1(BaseBean baseBean) {
          //吐司事件
        Toast.makeText(PayActivity.this, baseBean.getMsg(),Toast.LENGTH_SHORT).show();
        IntentUntils.setIntent(PayActivity.this,FuKuanActivity.class,"pricea",getThisPrice());
    }

    @Override
    public void Error1(BaseBean baseBean) {
        //吐司事件
        Toast.makeText(PayActivity.this, baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Success(BaseBean baseBean) {
           HuoQuBean huoQuBean = (HuoQuBean) baseBean;
        String addr = huoQuBean.getData().getAddr();
        address.setText(addr);
    }

    @Override
    public void Error(BaseBean baseBean) {
         //吐司事件
          Toast.makeText(PayActivity.this, baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }
}
