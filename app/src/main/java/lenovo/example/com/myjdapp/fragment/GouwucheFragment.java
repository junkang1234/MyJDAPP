package lenovo.example.com.myjdapp.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.activity.PayActivity;
import lenovo.example.com.myjdapp.adapter.CartAdapter;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.CarEntity;
import lenovo.example.com.myjdapp.bean.ChildBean;
import lenovo.example.com.myjdapp.bean.ParentBean;
import lenovo.example.com.myjdapp.presenter.CJpresenter;
import lenovo.example.com.myjdapp.untils.HttpUtils;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.ChuanJianview;
import lenovo.example.com.myjdapp.view.ICartView;


public class GouwucheFragment extends Fragment implements View.OnClickListener,ICartView,ChuanJianview {
    private View view;

    private ExpandableListView expandableListView;

    private CartAdapter adapter;

    private List<ParentBean> parentList;
    private List<List<ChildBean>> childList;
    private CJpresenter cJpresenter ;
    private CheckBox gouwuche_footer_check;
    private TextView gouwuche_footer_jiesuan;
    private TextView gouwuche_footer_price;
    private TextView gouwuche_footer_heji;

    private HttpUtils utils;

    private TextView gouwuche_tv;

    private SharedPreferences sp;

    private int sum=0;//总价

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gouwuche, container, false);
        initView(view);

      //  initData();
        createEvent();
        return view;
    }

    private void initData() {
        boolean spFlag = SpUtils.getSpFlag(getActivity());
        if(spFlag)
        {
            gouwuche_tv.setVisibility(View.GONE);
            expandableListView.setVisibility(View.VISIBLE);

            //调用P层获取数据
            int spUid = SpUtils.getSpUid(getActivity());
            HashMap<String,String> params=new HashMap<>();
            params.put("uid",spUid+"");
            utils=new HttpUtils();
            utils.postMap("http://120.27.23.105/product/getCarts", params, new HttpUtils.HttpCallBack() {
                @Override
                public void onSusscess(String data) {
                    CarEntity carEntity=new Gson().fromJson(data,CarEntity.class);

                    parentList = new ArrayList<>();
                    childList = new ArrayList<>();

                    //获取数据
                    List<CarEntity.DataBean> dataBeen = carEntity.getData();
                    for (int i=0;i<dataBeen.size();i++)
                    {
                        ParentBean parentBean=new ParentBean(dataBeen.get(i).getSellerName(),false,true);
                        parentList.add(parentBean);

                        List<ChildBean> childBeen=new ArrayList<ChildBean>();
                        List<CarEntity.DataBean.ListBean> listBeen = dataBeen.get(i).getList();
                        for(int j=0;j<listBeen.size();j++)
                        {
                            CarEntity.DataBean.ListBean listBean=listBeen.get(j);
                            int spUid1 = SpUtils.getSpUid(getActivity());
                            ChildBean bean=new ChildBean(spUid1+"",listBean.getPid()+"",listBean.getTitle(),listBean.getNum(),false,(int)listBean.getPrice(),true,listBean.getImages().split("\\|")[0]);
                            childBeen.add(bean);
                        }
                        childList.add(childBeen);
                    }

                    adapter = new CartAdapter(getContext(), parentList, childList,GouwucheFragment.this);
                    expandableListView.setAdapter(adapter);

                    expandableListView.setGroupIndicator(null);
                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                        expandableListView.expandGroup(i);
                    }
                }
            });

        }
        else
        {
            //显示购物车为空
            gouwuche_tv.setVisibility(View.VISIBLE);
            expandableListView.setVisibility(View.GONE);
        }
    }

    private void initView(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.gouwuche_expanded);
        gouwuche_footer_check = (CheckBox) view.findViewById(R.id.gouwuche_footer_check);
        gouwuche_footer_jiesuan = (TextView) view.findViewById(R.id.gouwuche_footer_jiesuan);
        gouwuche_footer_jiesuan.setOnClickListener(this);
        gouwuche_footer_price = (TextView) view.findViewById(R.id.gouwuche_footer_price);
        gouwuche_footer_heji = (TextView) view.findViewById(R.id.gouwuche_footer_heji);
        gouwuche_tv= (TextView) view.findViewById(R.id.gouwuche_tv);
    }

    private void createEvent() {

        //设置选中监听去实现全选
        gouwuche_footer_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true)
                {
                    adapter.allCheck(true);
                }
            }
        });
        //设置点击监听去实现反选
        gouwuche_footer_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取购物车的CheckBox的选中状态
                boolean isCheck=gouwuche_footer_check.isChecked();
                if(!isCheck)
                {
                    adapter.allCheck(false);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gouwuche_footer_jiesuan:
                cJpresenter.getData(SpUtils.getSpUid(getActivity())+"",sum+"");
                IntentUntils.setIntent(getActivity(),PayActivity.class,"price",sum);
                break;
        }
    }

    //修改全选按钮的状态
    @Override
    public void changeCheckBtn(boolean flag) {
        gouwuche_footer_check.setChecked(flag);
    }

    //计算总价的方法
    @Override
    public void addPrice() {
        //初始化总价
        sum=0;
        //遍历所有的子集合
        for(int i=0;i<adapter.getGroupCount();i++)
        {
            for (int j=0;j<adapter.getChildrenCount(i);j++)
            {
                ChildBean child=adapter.getChild(i,j);
                //如果该对象被选中,则加上这个对象中的价钱
                if(child.isCheck)
                {
                    sum+=child.price*child.saleNum;
                }
            }
        }
        //得到总价,更新UI控件
        gouwuche_footer_price.setText(sum+"");
    }

    @Override
    public void addCount() {
        //初始化总价
        int count=0;
        //遍历所有的子集合
        for(int i=0;i<adapter.getGroupCount();i++)
        {
            for (int j=0;j<adapter.getChildrenCount(i);j++)
            {
                ChildBean child=adapter.getChild(i,j);
                //如果该对象被选中,则加上这个对象中的价钱
                if(child.isCheck)
                {
                    count+=1;
                }
            }
        }
        gouwuche_footer_jiesuan.setText("结算"+"("+count+")");
    }

    @Override
    public void delete(String uid,String pid) {
        //删除的接口回调
        HashMap<String,String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("pid",pid);
        utils.postMap("http://120.27.23.105/product/deleteCart", params, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                parentList.clear();
                childList.clear();
                initData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void Success1(BaseBean baseBean) {

    }

    @Override
    public void Error1(BaseBean baseBean) {

    }
}