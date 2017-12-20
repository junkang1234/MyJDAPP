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
import lenovo.example.com.myjdapp.adapter.MyShopListAdapter;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.ShopListBean;
import lenovo.example.com.myjdapp.presenter.ShopListPresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.view.ShopListView;


public class ShopListActivity extends AppCompatActivity implements View.OnClickListener,ShopListView {

        private ImageView exits;
        private Button edit_search;
        private ImageView chage_list;
        private RecyclerView recyclerView;
        private ShopListPresenter shopListPresenter ;
        private int page = 1 ;
        private MyShopListAdapter myShopListAdapter ;
        private boolean flag = true ;
        private LinearLayoutManager linearlayoutmanager ;
        private GridLayoutManager gridlayoutmanager ;
        private Handler handler = new Handler();
        private int visiableItem ;
        private int itemCount ;
        private int firstItem ;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_shop_list);
            getSupportActionBar().hide();
            int pscid = getPscid();
            //log测试输出
                    Log.d("qq","获取的pscid值:" + pscid);
            initView();
            loadData(pscid);
        }

    private void loadData(int pscid) {
        shopListPresenter = new ShopListPresenter(this);
        shopListPresenter.getData(pscid,page);
    }

    private void initView() {

            exits = (ImageView) findViewById(R.id.normal_code);
            exits.setOnClickListener(this);
            edit_search = (Button) findViewById(R.id.button_search);
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
        private int getPscid(){
            Intent intent = getIntent();
            return   intent.getIntExtra("pscid", 0);
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
           ShopListBean shopListBean = (ShopListBean) baseBean;
            final List<ShopListBean.DataBean> data = shopListBean.getData();
            myShopListAdapter = new MyShopListAdapter(this,data,R.layout.shop_list_layout);
            linearlayoutmanager =     new LinearLayoutManager(ShopListActivity.this);
            recyclerView.setAdapter(myShopListAdapter);
            onclik(data);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    visiableItem = recyclerView.getChildCount();
                    itemCount = linearlayoutmanager.getItemCount();
                    firstItem = linearlayoutmanager.findFirstVisibleItemPosition();
                    //log测试输出
                            Log.d("qq","可见条目:" + visiableItem);
                    //log测试输出
                    Log.d("qq","总条目:" + itemCount);
                    //log测试输出
                    Log.d("qq","第一条目:" + firstItem);
                    if(visiableItem + firstItem == itemCount){
                                 page++;
                                shopListPresenter.getData(getPscid(),page);
                                myShopListAdapter.adData(data);
                    }

                }
            });

        chage_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    linearlayoutmanager = new LinearLayoutManager(ShopListActivity.this,LinearLayoutManager.VERTICAL,false) ;
                    recyclerView.setLayoutManager(linearlayoutmanager);
                    recyclerView.addItemDecoration(new DividerItemDecoration(ShopListActivity.this,DividerItemDecoration.HORIZONTAL));
                    myShopListAdapter = new MyShopListAdapter(ShopListActivity.this,data,R.layout.shop_list_layout);
                    chage_list.setBackgroundResource(R.mipmap.ic_goods_list_hor);
                    flag=true;
                }else {
                    chage_list.setBackgroundResource(R.mipmap.ic_goods_list_ver);
                    gridlayoutmanager = new GridLayoutManager(ShopListActivity.this,2) ;
                    recyclerView.setLayoutManager(gridlayoutmanager);
                    myShopListAdapter = new MyShopListAdapter(ShopListActivity.this,data,R.layout.shop_list_layout_gride);
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
   private void onclik(final List<ShopListBean.DataBean> data){
       myShopListAdapter.setOnitemClickLisenter(new MyShopListAdapter.OnitemClickListenter() {
           @Override
           public void setOnclick(int position) {
               int pid = data.get(position).getPid();
               IntentUntils.setIntent(ShopListActivity.this,DetailPageActivity.class,"pid",pid);
           }
       });
   }
}
