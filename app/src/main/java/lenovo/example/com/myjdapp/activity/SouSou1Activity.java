package lenovo.example.com.myjdapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.adapter.MySearListAdapter;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.SearListBean;
import lenovo.example.com.myjdapp.presenter.SearListPresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.view.SearListView;


public class SouSou1Activity extends AppCompatActivity implements View.OnClickListener,SearListView {

    private ImageView exits;
    private Button edit_search;
    private ImageView chage_list;
    private RecyclerView recyclerView;
    private SearListPresenter shopListPresenter;
    private int page = 1 ;
    private MySearListAdapter myShopListAdapter ;
    private boolean flag = true ;
    private LinearLayoutManager linearlayoutmanager ;
    private GridLayoutManager gridlayoutmanager ;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        getSupportActionBar().hide();
        String keywords = getKyWords();
        initView();
        loadData(keywords);
    }

    private void loadData(String keywords) {
        shopListPresenter = new SearListPresenter(this);
        shopListPresenter.getData(keywords);
    }

    private void initView() {

        exits = (ImageView) findViewById(R.id.normal_code);
        exits.setOnClickListener(this);
        edit_search = (Button) findViewById(R.id.button_search);
        edit_search.setText(getKyWords());
        edit_search.setOnClickListener(this);
        chage_list = (ImageView) findViewById(R.id.qiehuan);
//            chage_list.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyfour);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
    }

    /**
     * 获取传过来的pscid值
     * @return
     */
    private String getKyWords(){
        Intent intent = getIntent();
        return   intent.getStringExtra("keywords");
    }

    @Override
    public void onClick(View view) {
        //switch判断
        switch (view.getId()) {
            case R.id.normal_code:
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
                break;
            case R.id.button_search:
                IntentUntils.setIntent(this,SousouActivity.class);
                overridePendingTransition(R.anim.right, R.anim.left);
                break;
            default:
                break;
        }}
    @Override
    public void Success(BaseBean baseBean) {
        SearListBean shopListBean = (SearListBean) baseBean;
        final List<SearListBean.DataBean> data = shopListBean.getData();
        myShopListAdapter = new MySearListAdapter(this,data,R.layout.shop_list_layout);
        linearlayoutmanager =     new LinearLayoutManager(SouSou1Activity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(myShopListAdapter);
        onclik(data);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //log测试输出
                Log.d("qq","newState " + newState);
                //log测试输出
                Log.d("qq","RecyclerView.SCROLL_STATE_IDLE " + RecyclerView.SCROLL_STATE_IDLE);
                //log测试输出
                Log.d("qq","linearlayoutmanager.findLastVisibleItemPosition() " + linearlayoutmanager.findLastVisibleItemPosition());
                //log测试输出
                Log.d("qq","myShopListAdapter.getItemCount()-1 " + (myShopListAdapter.getItemCount()-1));

                if(linearlayoutmanager.findLastVisibleItemPosition() == myShopListAdapter.getItemCount()-1){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            page++;
                            shopListPresenter.getData(getKyWords());
                            myShopListAdapter.adData(data);
                        }
                    },1500);
                }
            }
        });

        chage_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    linearlayoutmanager = new LinearLayoutManager(SouSou1Activity.this,LinearLayoutManager.VERTICAL,false) ;
                    recyclerView.setLayoutManager(linearlayoutmanager);
                    recyclerView.addItemDecoration(new DividerItemDecoration(SouSou1Activity.this,DividerItemDecoration.HORIZONTAL));
                    myShopListAdapter = new MySearListAdapter(SouSou1Activity.this,data,R.layout.shop_list_layout);
                    chage_list.setBackgroundResource(R.mipmap.ic_goods_list_hor);
                    flag=true;
                }else {
                    chage_list.setBackgroundResource(R.mipmap.ic_goods_list_ver);
                    gridlayoutmanager = new GridLayoutManager(SouSou1Activity.this,2) ;
                    recyclerView.setLayoutManager(gridlayoutmanager);
                    myShopListAdapter = new MySearListAdapter(SouSou1Activity.this,data,R.layout.shop_list_layout_gride);
                    flag=false;
                }
                recyclerView.setAdapter(myShopListAdapter);
                onclik(data);
            }
        });
    }

    @Override
    public void Error(BaseBean baseBean) {

    }
    private void onclik(final List<SearListBean.DataBean> data){
        myShopListAdapter.setOnitemClickLisenter(new MySearListAdapter.OnitemClickListenter() {
            @Override
            public void setOnclick(int position) {
                int pid = data.get(position).getPid();
                IntentUntils.setIntent(SouSou1Activity.this,DetailPageActivity.class,"pid",pid);
            }
        });
    }
}
