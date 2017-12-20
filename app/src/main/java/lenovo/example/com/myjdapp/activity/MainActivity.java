package lenovo.example.com.myjdapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.jkyeo.splashview.SplashView;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.fragment.BaseFragment;
import lenovo.example.com.myjdapp.fragment.FenLeiFragment;
import lenovo.example.com.myjdapp.fragment.Home;
import lenovo.example.com.myjdapp.fragment.UserFragment;


public class MainActivity extends AppCompatActivity {

    private BottomTabBar tabbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        getSupportActionBar().hide();
        initView();
        splashView();
        initData();


    }

    private void splashView() {
        // call after setContentView(R.layout.activity_sample);
        SplashView.showSplashView(this, 6, R.drawable.log, new SplashView.OnSplashViewActionListener() {
            @Override
            public void onSplashImageClick(String actionUrl) {
                Log.d("SplashView", "img clicked. actionUrl: " + actionUrl);
                Toast.makeText(MainActivity.this, "img clicked.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSplashViewDismiss(boolean initiativeDismiss) {
                Log.d("SplashView", "dismissed, initiativeDismiss: " + initiativeDismiss);
            }
        });

        // call this method anywhere to update splash view data
        // SplashView.updateSplashData(this, "http://ww2.sinaimg.cn/large/72f96cbagw1f5mxjtl6htj20g00sg0vn.jpg", "http://jkyeo.com");
    }

    private void initData() {
        tabbar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(15)
                .setTabPadding(4,6,10)
                .addTabItem("首页", R.mipmap.ic_nav_home,Home.class)
                .addTabItem("分类",R.mipmap.ic_nav_class,FenLeiFragment.class)
             //   .addTabItem("发现",R.mipmap.ic_nav_class,FenLeiFragment.class)
                .addTabItem("购物车",R.mipmap.gou_wu_che, BaseFragment.CartFragment.class)
                .addTabItem("我的",R.mipmap.ic_nav_user,UserFragment.class)
                .isShowDivider(true);
    }


    private void initView() {

        tabbar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);

    }
}
