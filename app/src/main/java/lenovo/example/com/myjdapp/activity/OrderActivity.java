package lenovo.example.com.myjdapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.fragment.FiveFragment;
import lenovo.example.com.myjdapp.fragment.FourFragment;
import lenovo.example.com.myjdapp.fragment.OneFragment;
import lenovo.example.com.myjdapp.fragment.ThreeFragment;
import lenovo.example.com.myjdapp.fragment.TwoFragment;


public class OrderActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ImageView image_exit;
    private List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().hide();
        initView();
        loadTitle();
        loadTab();
        ifShowPage();
        tabClick();
        exit();
    }

    private void ifShowPage() {
        if(getCanshu()==0){
            startFragment(new OneFragment());
        }else if(getCanshu()==1){
            //默认选中第一个
            tablayout.getTabAt(1).select();
            startFragment(new TwoFragment());
        }
    }

    public int getCanshu(){
       return  getIntent().getIntExtra("canshu", 0);
    }

    public void startFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
    }

    private void tabClick() {
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().equals("全部")){
                   startFragment(new OneFragment());
                }else if(tab.getText().equals("待支付")){
                    startFragment(new TwoFragment());
                }else if(tab.getText().equals("待收货")){
                    startFragment(new ThreeFragment());
                }else if(tab.getText().equals("已完成")){
                    startFragment(new FourFragment());
                }else if(tab.getText().equals("已取消")){
                    startFragment(new FiveFragment());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void loadTab() {
        for (int i=0;i<strings.size();i++){
            tablayout.addTab(tablayout.newTab().setText(strings.get(i)));
        }
    }

    private void loadTitle() {
        strings.add("全部");
        strings.add("待支付");
        strings.add("待收货");
        strings.add("已完成");
        strings.add("已取消");
    }

    private void initView() {

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        image_exit = (ImageView) findViewById(R.id.image_exit);

    }
    public void exit(){

        image_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });

    }
}
