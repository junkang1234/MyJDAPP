package lenovo.example.com.myjdapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.activity.PhotoViewActivity;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.DetailPageBean;
import lenovo.example.com.myjdapp.presenter.AddCartPresenter;
import lenovo.example.com.myjdapp.presenter.DetailPagePresenter;
import lenovo.example.com.myjdapp.untils.GlideImageLoader;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.untils.ToastUntils;
import lenovo.example.com.myjdapp.untils.ViewUnitls;
import lenovo.example.com.myjdapp.view.AddCartView;
import lenovo.example.com.myjdapp.view.DetailPageView;


public class Fragment1 extends Fragment implements DetailPageView,View.OnClickListener,AddCartView {


    private Banner vip;
    private DetailPagePresenter detailPagePrensenter;
    private TextView tv_title;
    private TextView sub;
    private TextView price;
    private Button addCart;
    private AddCartPresenter addCartPresenter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = ViewUnitls.setVeiw(getActivity(), R.layout.fragment1);

        initView(view);
        loadData();



        return view;
    }

    private void loadData() {
        detailPagePrensenter = new DetailPagePresenter(this);
        detailPagePrensenter.getData(getPid());
    }
    private void initView(View v) {
        addCartPresenter = new AddCartPresenter(this);
        vip = (Banner) v.findViewById(R.id.banner1);
        tv_title = (TextView) v.findViewById(R.id.text_title);
        sub = (TextView) v.findViewById(R.id.sub_head);
        price = (TextView) v.findViewById(R.id.tv_price);
        ImageView jimi = (ImageView) v.findViewById(R.id.image_jimi);
        jimi.setOnClickListener(this);
        RelativeLayout dianpu = (RelativeLayout) v.findViewById(R.id.relv1);
        dianpu.setOnClickListener(this);
        RelativeLayout guanzhu = (RelativeLayout) v.findViewById(R.id.relv1);
        guanzhu.setOnClickListener(this);
        RelativeLayout gouWuChe = (RelativeLayout) v.findViewById(R.id.relv1);
        gouWuChe.setOnClickListener(this);
        addCart = (Button) v.findViewById(R.id.addcart);
    }

    private int getPid(){
        int pid = getArguments().getInt("pid");
        return pid ;
    }

    @Override
    public void Success(BaseBean baseBean) {
        DetailPageBean baseBean1 = (DetailPageBean) baseBean;
        final DetailPageBean.DataBean data = baseBean1.getData();
        final DetailPageBean.SellerBean seller = baseBean1.getSeller();
        final String[] split = data.getImages().split("\\|");
        final List<String> images = new ArrayList<>();
        for (String s : split){
            images.add(s);
        }
        vip.setImageLoader(new GlideImageLoader());
        vip.setImages(images);
        vip.isAutoPlay(false);
        vip.setBannerStyle(BannerConfig.NUM_INDICATOR);
        vip.start();
        vip.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(),PhotoViewActivity.class);
                intent.putExtra("position",position);
                intent.putStringArrayListExtra("stringlist", (ArrayList<String>) images);
                startActivity(intent);
            }
        });
        tv_title.setText(data.getTitle());
        sub.setText(data.getSubhead());
        price.setText("￥"+data.getPrice());
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getUid()!=0){
                    addCartPresenter.addData(getUid()+"",data.getPid()+"");
                }else {
                    ToastUntils.setToast(getActivity(),"请先登录");
                }
            }
        });
    }

    public int getUid(){
        return SpUtils.getSpUid(getActivity());
    }

    @Override
    public void Error(BaseBean baseBean) {

    }

    @Override
    public void onClick(View view) {
        //switch判断
        switch (view.getId()) {
        		case R.id.image_jimi:
                    jimi();
        			break;
            case R.id.relv2:
                break;
            case R.id.relv3:
                break;
            case R.id.relv4:
                break;
        		}
    }
    private void jimi() {

//        connect("70ig7l90atAvYhhmo1RmpCcgo0II0FsT9NgqG8mIsbCJr3Opv25NaPXFslmrFQcl0xWAob0KYu4tuY5WJw/Y6g==");

    }
//    private void connect(String token) {
//        RongIM.connect(token, new RongIMClient.ConnectCallback() {
//            @Override
//            public void onTokenIncorrect() {
//                Log.e("LoginActivity", "--onTokenIncorrect");
//            }
//            @Override
//            public void onSuccess(String userid) {
//                Log.e("LoginActivity", "--onSuccess--" + userid);
//                Toast.makeText(getActivity(), "登录成功,用户：" + userid, Toast.LENGTH_SHORT).show();
//                //服务器连接成功，跳转消息列表
//                startActivity(new Intent(getActivity(), ConversationListActivity.class));
//            }
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                Log.e("LoginActivity", "--onError");
//            }
//        });
//    }

    @Override
    public void addSuccess(BaseBean baseBean) {
        ToastUntils.setToast(getActivity(),baseBean.getMsg());
    }

    @Override
    public void addError(BaseBean baseBean) {
        ToastUntils.setToast(getActivity(),baseBean.getMsg());
    }

}
