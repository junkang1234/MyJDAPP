package lenovo.example.com.myjdapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.common.Api;
import lenovo.example.com.myjdapp.fragment.Fragment1;
import lenovo.example.com.myjdapp.fragment.Fragment2;
import lenovo.example.com.myjdapp.fragment.Fragment3;
import lenovo.example.com.myjdapp.untils.ToastUntils;


public class DetailPageActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView pingjia;
    private TextView pingjia1;
    private TextView xiangq;
    private TextView shangpin;
    private ImageView image_left;
    private ImageView image_fenxaing;
    private ImageView more;
    private Fragment[] fragments = new Fragment[3];
    private ViewPager vip;
    private Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        getSupportActionBar().hide();
        initView();
        loadData();
    }

    private void initView() {
        pingjia1 = (TextView) findViewById(R.id.pingjia);
        pingjia1.setOnClickListener(this);
        xiangq = (TextView) findViewById(R.id.xiangq);
        xiangq.setOnClickListener(this);
        shangpin = (TextView) findViewById(R.id.shangpin);
        shangpin.setOnClickListener(this);
        image_left = (ImageView) findViewById(R.id.image_left);
        image_left.setOnClickListener(this);
        image_fenxaing = (ImageView) findViewById(R.id.image_fenxaing);
        image_fenxaing.setOnClickListener(this);
        more = (ImageView) findViewById(R.id.more);
        more.setOnClickListener(this);
        vip = (ViewPager) findViewById(R.id.vip2);
        showFragment();
    }

    private void showFragment() {
        vip.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
    }

    private void loadData() {
        fragment1 = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putInt("pid",getPid());
        fragment1.setArguments(bundle);
        fragments[0] = fragment1;
        fragments[1] = new Fragment2();
        fragments[2] = new Fragment3();

    }

    private int getPid(){
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        return pid ;
    }

    @Override
    public void onClick(View view) {
        //switch判断
        int i = view.getId();
        if (i == R.id.pingjia) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.vip2, new Fragment2()).commit();

        } else if (i == R.id.xiangq) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.vip2, fragment1).commit();

        } else if (i == R.id.shangpin) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.vip2, new Fragment3()).commit();

        } else if (i == R.id.image_left) {
            exit();

        } else if (i == R.id.image_fenxaing) {
            fenxiang();

        } else if (i == R.id.more) {
            gengduo();
        }
    }

    private void gengduo() {
        ToastUntils.setToast(this,"嗒嗒");
    }

    private void fenxiang() {
        UMWeb web = new UMWeb(Api.URL);
        web.setTitle("This is music title");//标题
        web.setDescription("my description");//描述

        new ShareAction(this)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(DetailPageActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(DetailPageActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DetailPageActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

    private void exit() {
        finish();
        overridePendingTransition(R.anim.leftone, R.anim.rightone);
    }

}
