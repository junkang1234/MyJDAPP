package lenovo.example.com.myjdapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.activity.OrderActivity;
import lenovo.example.com.myjdapp.activity.SettingPage;
import lenovo.example.com.myjdapp.activity.UserLoginActivity;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.InfoBean;
import lenovo.example.com.myjdapp.presenter.InfoPresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.untils.ToastUntils;
import lenovo.example.com.myjdapp.untils.ViewUnitls;
import lenovo.example.com.myjdapp.view.InfoView;


public class UserFragment extends Fragment implements InfoView,View.OnClickListener{

    private LinearLayout lin;
    private InfoPresenter infopresenter ;
    private ImageView user_head;
    private TextView tv_name;
    private TextView tv_login;
    private TextView tv_zhunx;
    private RelativeLayout myorder;
    private RelativeLayout waitzhifu;
    private RelativeLayout waitshouhuo;
    private RelativeLayout waitpingjia;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = ViewUnitls.setVeiw(getActivity(), R.layout.userfragment);
        initView(view);



        return view;
    }
    public int getUid(){
        int spUid = SpUtils.getSpUid(getActivity());
        return spUid ;
    }
    public boolean getFlag(){
        boolean falg = SpUtils.getSpFlag(getActivity());
        return falg ;
    }
    private void loadData() {
            infopresenter.getInfo(getUid());
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getFlag()){
            loadData();

        }else {
            tv_login.setVisibility(View.VISIBLE);
            tv_name.setVisibility(View.INVISIBLE);
            tv_zhunx.setVisibility(View.INVISIBLE);
            tv_name.setText("登录/注册 >");
            user_head.setImageResource(R.mipmap.touxiang);
        }
    }

    private void initView(View view) {

        infopresenter = new InfoPresenter(this);
        lin = (LinearLayout) view.findViewById(R.id.linone);
        user_head = (ImageView) view.findViewById(R.id.user_head);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_login = (TextView) view.findViewById(R.id.tv_login);
        tv_zhunx = (TextView) view.findViewById(R.id.tv_zhunxaing);
        myorder = (RelativeLayout) view.findViewById(R.id.myorder);
        waitzhifu = (RelativeLayout) view.findViewById(R.id.waitzhifu);
        waitshouhuo = (RelativeLayout) view.findViewById(R.id.waitshouhuo);
        waitpingjia = (RelativeLayout) view.findViewById(R.id.waitpingjia);
        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });

    }

    @Override
    public void Success(BaseBean baseBean) {
        InfoBean baseBean1 = (InfoBean) baseBean;
        InfoBean.DataBean data = baseBean1.getData();
        tv_login.setVisibility(View.INVISIBLE);
        tv_name.setVisibility(View.VISIBLE);
        tv_zhunx.setVisibility(View.VISIBLE);
        tv_name.setText(data.getUsername());
        myorder.setOnClickListener(this);
        waitzhifu.setOnClickListener(this);

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .displayer(new Displayer(0))
                    .showImageForEmptyUri(R.mipmap.touxiang)
                    .showImageOnLoading(R.mipmap.touxiang)
                    .showImageOnFail(R.mipmap.touxiang)
                    .build();
            ImageLoader.getInstance().displayImage((String) data.getIcon(),user_head,options);

        SpUtils.getSpPutString(getActivity(),data.getNickname()+"",data.getUsername());
        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFlag()){
                    IntentUntils.setIntent(getActivity(), SettingPage.class,"uidkey",getUid(),"flagkey",getFlag());
                    getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                }else {
                            Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                            startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                }
            }
        });
    }

    @Override
    public void Error(String msg) {
        ToastUntils.setToast(getActivity(),msg);
    }

    @Override
    public void onClick(View view) {
        //switch判断
        switch (view.getId()) {
        		case R.id.myorder:
        			IntentUntils.setIntent(getActivity(), OrderActivity.class,"canshu",0);
                    getActivity().overridePendingTransition(R.anim.right, R.anim.left);
        			break;
            case R.id.waitzhifu:
                IntentUntils.setIntent(getActivity(), OrderActivity.class,"canshu",1);
                getActivity().overridePendingTransition(R.anim.right, R.anim.left);
                break;
        		}
    }
}
