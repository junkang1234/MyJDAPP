package lenovo.example.com.myjdapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.activity.SousouActivity;
import lenovo.example.com.myjdapp.adapter.MyMiaoShaAdapter;
import lenovo.example.com.myjdapp.adapter.MyPagerAdapter;
import lenovo.example.com.myjdapp.adapter.MyTuiJianShaAdapter;
import lenovo.example.com.myjdapp.adapter.Shouye_Grid_Layout;
import lenovo.example.com.myjdapp.adapter.ViewPagerIndicator;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.FenLeiBean;
import lenovo.example.com.myjdapp.bean.HomeBean;
import lenovo.example.com.myjdapp.presenter.HomePresenter;
import lenovo.example.com.myjdapp.untils.GlideImageLoader;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.ObservableScrollView;
import lenovo.example.com.myjdapp.untils.ToastUntils;
import lenovo.example.com.myjdapp.untils.ViewUnitls;
import lenovo.example.com.myjdapp.view.HomeView;


public class Home extends Fragment implements HomeView,View.OnClickListener{

    private HomePresenter homePresenter ;
    private List<String> bannerimages ;
    private List<String> bannertitles ;
    private Banner banner;
    private ViewPager vip;
    private List<View> views ;
    private MyPagerAdapter pageradapter ;
    private LinearLayout ll;
    private RecyclerView sy_miaoSha;
    private TextView mstv;
    private MyMiaoShaAdapter myMiaoShaAdapter;
    private RecyclerView retwo;
    private MyTuiJianShaAdapter mytuijianadapter ;
    private TextView scanner;
    private Button button;
    private TextView tv_hour;
    private TextView tv_minute;
    private TextView tv_second;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    tv_hour.setText("0"+mHour+"");
                }else {
                    tv_hour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    tv_minute.setText("0"+mMin+"");
                }else {
                    tv_minute.setText(mMin+"");
                }
                if (mSecond<10){
                    tv_second.setText("0"+mSecond+"");
                }else {
                    tv_second.setText(mSecond+"");
                }
            }
        }
    };

    private ViewFlipper view ;
    private ObservableScrollView scro;
    private LinearLayout lin;
    private int imageHeight=300; //设置渐变高度，一般为导航图片高度，自己控制
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.home_layout,null);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);

        homePresenter = new HomePresenter(this);

        initView(view);
        smartrefresh(view);
        loadData();

        return view;
    }

    private void smartrefresh(View view) {
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void initView(View v) {
        bannerimages = new ArrayList<>();
        bannertitles = new ArrayList<>();
        views = new ArrayList<>();
        banner = (Banner) v.findViewById(R.id.banner);
        sy_miaoSha = (RecyclerView) v.findViewById(R.id.recyone);
        retwo = (RecyclerView) v.findViewById(R.id.recytwo);
        vip = (ViewPager) v.findViewById(R.id.vip);
        ll = (LinearLayout) v.findViewById(R.id.ll);
        scanner = (TextView) v.findViewById(R.id.normal_code);
        button = (Button) v.findViewById(R.id.button_search);
        button.setOnClickListener(this);
        scanner.setOnClickListener(this);
        tv_hour = (TextView) v.findViewById(R.id.tv_hour);
        tv_minute = (TextView) v.findViewById(R.id.tv_minute);
        tv_second = (TextView) v.findViewById(R.id.tv_second);
        view = (ViewFlipper) v.findViewById(R.id.vf);
        View view1 = View.inflate(getActivity(),R.layout.viewfiller,null);
        view.addView(view1);
        view.setFlipInterval(2000);
        view.startFlipping();
        view.setInAnimation(inFromRightAnimation());//设置View进入屏幕时候使用的动画
        view.setOutAnimation(outToLeftAnimation());
        scro = (ObservableScrollView) v.findViewById(R.id.scro);
        lin = (LinearLayout) v.findViewById(R.id.linear);
        lin.bringToFront();
        scro.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    lin.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    lin.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                } else {
                    lin.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }
        });
    }
    /**
     * 定义从右侧进入的动画效果
     * @return
     */
    protected Animation inFromRightAnimation() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(1000);
        LinearInterpolator lir = new LinearInterpolator();
        animation.setInterpolator(lir);
        return animation;
    }

    /**
     * 定义从左侧退出的动画效果
     * @return
     */
    protected Animation outToLeftAnimation() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(1000);
        LinearInterpolator lir = new LinearInterpolator();
        animation.setInterpolator(lir);
        return animation;
    }
    private void showSYmiaosha(HomeBean.MiaoshaBean miaoshaBean){

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        sy_miaoSha.setLayoutManager(layoutManager);
        myMiaoShaAdapter = new MyMiaoShaAdapter(miaoshaBean,getActivity());
        sy_miaoSha.setAdapter(myMiaoShaAdapter);

    }
    private void loadData() {
        homePresenter.LoadData();
        homePresenter.LoadData1();
    }
    private void newViews(List<FenLeiBean.DataBean> data) {
         String[] icon= new String[10];
         String[] iconName = new String[10];
        for (int i = 0 ; i<10;i++){
                icon[i] = data.get(i).getIcon();
                iconName[i] = data.get(i).getName();
        }
        for (int i = 0 ; i < 2 ;i++){
            View v = ViewUnitls.setVeiw(getActivity(),R.layout.gride_layout);
            GridView  grid = (GridView) v.findViewById(R.id.grid);
            Shouye_Grid_Layout shouye_adapter = new Shouye_Grid_Layout(getActivity(),iconName,icon,R.layout.gride);
            grid.setAdapter(shouye_adapter);
            views.add(v);
        }
        pageradapter = new MyPagerAdapter(views);
        vip.setAdapter(pageradapter);
        vip.setOnPageChangeListener(new ViewPagerIndicator(getActivity(),vip,ll,2));
    }

    @Override
    public void Success(BaseBean baseBean) {
        HomeBean baseBean1 = (HomeBean) baseBean;
        List<HomeBean.DataBean> data = baseBean1.getData();
        HomeBean.TuijianBean tuijian = baseBean1.getTuijian();
        HomeBean.MiaoshaBean miaoshaBean = baseBean1.getMiaosha();
        for (HomeBean.DataBean dataBean : data){
            bannerimages.add(dataBean.getIcon());
            bannertitles.add(dataBean.getTitle());
        }
        showBanner();
        showSYmiaosha(miaoshaBean);
        showTuiJian(tuijian);
        startRun();
    }
    private void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

    private void showTuiJian(HomeBean.TuijianBean tuijian) {
        retwo.setLayoutManager(new GridLayoutManager(getActivity(),2));
        retwo.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mytuijianadapter = new MyTuiJianShaAdapter(tuijian,getActivity());
        retwo.setAdapter(mytuijianadapter);
    }

    /**
     * 显示banner
     */
    private void showBanner() {
        banner.setImages(bannerimages)
                .setImageLoader(new GlideImageLoader())
                .setBannerTitles(bannertitles)
                .isAutoPlay(true)
                .start();
    }

    @Override
    public void Erroe(BaseBean baseBean) {
        ToastUntils.setToast(getActivity(),"请求失败");
    }

    @Override
    public void Success1(BaseBean baseBean) {
        FenLeiBean fenLeiBean = (FenLeiBean) baseBean;
        List<FenLeiBean.DataBean> data = fenLeiBean.getData();
        newViews(data);
    }

    @Override
    public void Erroe1(BaseBean baseBean) {
        ToastUntils.setToast(getActivity(),"请求失败");
    }


    @Override
    public void onClick(View view) {
         //switch判断
         switch (view.getId()) {
         		case R.id.normal_code:
                    saoMiao();
         			break;
             case R.id.button_search:
                 souSuo();

                 break;
         		default:
         			break;
         		}
    }

    private void souSuo() {
        IntentUntils.setIntent(getActivity(), SousouActivity.class);
        getActivity().overridePendingTransition(R.anim.right, R.anim.left);
    }

    /**
     * 扫描二维码
     */
    private void saoMiao() {

        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent,0);
        getActivity().overridePendingTransition(R.anim.right, R.anim.left);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
