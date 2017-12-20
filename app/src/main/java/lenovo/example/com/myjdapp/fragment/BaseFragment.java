package lenovo.example.com.myjdapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.activity.PayActivity;
import lenovo.example.com.myjdapp.activity.UserLoginActivity;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.SelectBean;
import lenovo.example.com.myjdapp.bean.ShopBean;
import lenovo.example.com.myjdapp.presenter.SelectCartPresenter;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.MyTextView;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.untils.ToastUntils;
import lenovo.example.com.myjdapp.view.SelectCartView;


public abstract class BaseFragment extends Fragment {
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见时的回调方法
     */
    protected abstract void onVisible();

    /**
     * 不可见时的回调方法
     */
    protected abstract void onInvisible();


    public static class CartFragment extends Fragment implements SelectCartView {

        private TextView btn_login;
        private ExpandableListView expand;
        private SelectCartPresenter selectCartPresenter ;
        private RelativeLayout relativeLayout;
        private CheckBox checkBox;
        private MyCatAdapter myCatAdapter;
        private TextView tv;
        private Button button;
        private List<SelectBean.DataBean> group_list = new ArrayList<>();
        private List<List<SelectBean.DataBean.ListBean>> child_list = new ArrayList<>();

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View v = View.inflate(getActivity(), R.layout.cart_layout,null);

            initView(v);

            return v;
        }

        private void initView(View v) {
            btn_login = (TextView) v.findViewById(R.id.login);
            expand = (ExpandableListView) v.findViewById(R.id.expand1);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relvone);
            checkBox = (CheckBox) v.findViewById(R.id.check_quanxuan);
            tv = (TextView) v.findViewById(R.id.price);
            button = (Button) v.findViewById(R.id.btn_jiesuan);
        }

        @Override
        public void onResume() {
            super.onResume();
            ifLogin();
        }

    /**
     * 判断是否登录
     */

        public void ifLogin(){
            selectCartPresenter = new SelectCartPresenter(this);
             if(getlag()){
                 btn_login.setVisibility(View.GONE);
                 expand.setVisibility(View.VISIBLE);
                 relativeLayout.setVisibility(View.VISIBLE);
                 selectCartPresenter.selectData(getUid()+"");
             }else {
                 relativeLayout.setVisibility(View.GONE);
                 expand.setVisibility(View.GONE);
                 btn_login.setVisibility(View.VISIBLE);
                 btn_login.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         IntentUntils.setIntent(getActivity(), UserLoginActivity.class);
                         getActivity().overridePendingTransition(R.anim.leftone, R.anim.rightone);
                     }
                 });
             }
        }
        public boolean getlag(){
            return  SpUtils.getSpFlag(getActivity());
        }
        public int getUid(){
            return  SpUtils.getSpUid(getActivity());
        }

        @Override
        public void Success(BaseBean baseBean) {
            SelectBean baseBean1 = (SelectBean) baseBean;
            List<SelectBean.DataBean> group_list1 = baseBean1.getData();
            group_list.addAll(group_list1);
            for (SelectBean.DataBean dataBean : group_list1){
                List<SelectBean.DataBean.ListBean> list = dataBean.getList();
                child_list.add(list);
            }
            if(baseBean1!=null){
                        myCatAdapter = new MyCatAdapter();
                        expand.setAdapter(myCatAdapter);
                        for (int i = 0 ;i < child_list.size();i++){
                            expand.expandGroup(i);
                        }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String[] ￥s = tv.getText().toString().split("￥");
                        for (String s:￥s){
                            //log测试输出
                                    Log.d("qq","s:" + s);
                        }
                         //吐司事件
    //                      Toast.makeText(getActivity(),￥s.,Toast.LENGTH_SHORT).show();
                        boolean isflag = false ;
                        List<ShopBean> shoplist = new ArrayList<ShopBean>();
                        for (SelectBean.DataBean dd : group_list){
                            List<SelectBean.DataBean.ListBean> list = dd.getList();
                            for (SelectBean.DataBean.ListBean ddl : list){
                                if(ddl.isItemCheck()) {
                                    isflag = true ;
                                    ShopBean shopBean = new ShopBean();
                                    String[] split = ddl.getImages().split("\\|");
                                    shopBean.setImage(split[0]);
                                    shopBean.setName(ddl.getTitle());
                                    shopBean.setNum(ddl.getNum()+"");
                                    shopBean.setPrice(ddl.getPrice()+"");
                                    shoplist.add(shopBean);
                                }
                            }
                        }
                        if(isflag){
                            IntentUntils.setIntent(getActivity(),PayActivity.class,shoplist);
                            getActivity().overridePendingTransition(R.anim.leftone, R.anim.rightone);
                        }else {
                            ToastUntils.setToast(getActivity(),"请选择商品");
                        }
                    }
                });
                        checkBox.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(checkBox.isChecked()){
                                    for (int i = 0 ; i < group_list.size() ; i ++ ){
                                        List<SelectBean.DataBean.ListBean> list = group_list.get(i).getList();
                                        group_list.get(i).setAllCheck(true);
                                        for (int y = 0 ;y<list.size();y++){
                                            list.get(y).setItemCheck(true);
                                        }
                                        shuaxin();
                                        sum();
                                    }
                                }else {
                                    for (int i = 0 ; i < group_list.size() ; i ++ ){
                                        List<SelectBean.DataBean.ListBean> list = group_list.get(i).getList();
                                        group_list.get(i).setAllCheck(false);
                                        for (int y = 0 ;y<list.size();y++){
                                            list.get(y).setItemCheck(false);
                                        }
                                        shuaxin();
                                        sum();
                                    }
                                }
                            }
                        });
            }
        }

        @Override
        public void Error(BaseBean baseBean) {
            ToastUntils.setToast(getActivity(),baseBean.getMsg());
        }


        public  class MyCatAdapter extends BaseExpandableListAdapter {



            @Override
            public int getGroupCount() {
                return group_list.size();
            }

            @Override
            public int getChildrenCount(int i) {
                return child_list.get(i).size();
            }

            @Override
            public Object getGroup(int i) {
                return group_list.get(i);
            }

            @Override
            public Object getChild(int i, int i1) {
                return child_list.get(i).get(i1);
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {

                view = View.inflate(getActivity(), R.layout.mycart_group_layout,null);
                CheckBox check_name = (CheckBox) view.findViewById(R.id.check_name);
                boolean allCheck = group_list.get(i).isAllCheck();
                if(allCheck){
                    check_name.setChecked(true);
                }else {
                    check_name.setChecked(false);
                }
                check_name.setOnClickListener(new GroupLisener(i,check_name));
                TextView tv = (TextView) view.findViewById(R.id.tv_dianpuname);
                tv.setText(group_list.get(i).getSellerName());
                return view;
            }

            @Override
            public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {

                view = View.inflate(getActivity(), R.layout.mycart_child_layout,null);
                ImageView con_image = (ImageView) view.findViewById(R.id.contain_image);

                final CheckBox checkBox = (CheckBox) view.findViewById(R.id.radio_child);

                MyTextView myTextView = (MyTextView) view.findViewById(R.id.mytextview);
    //            child_list.get(i).get(i1).setNum(Integer.parseInt(SpUtils.getSpstring(getActivity())));
                myTextView.setChild_list(child_list);
                myTextView.setGroup_list(group_list);
                myTextView.setI(i);
                myTextView.setI1(i1);

                myTextView.setText(myTextView.getChild_list().get(i).get(i1).getNum()+"");
                child_list.get(i).get(i1).setNum(myTextView.getChild_list().get(i).get(i1).getNum());
                int count = 0;
                for (SelectBean.DataBean dd : group_list){
                    List<SelectBean.DataBean.ListBean> list = dd.getList();
                    for (SelectBean.DataBean.ListBean ddl : list){
                        if(ddl.isItemCheck()){
                            count+=ddl.getNum();
                        }
                    }
                }
                myTextView.setOnclick(new MyTextView.Onclick() {
                    @Override
                    public void setOnClick(int num, int peice) {
                        button.setText("结算(" +num+")");
                        tv.setText("￥"+peice+"");
                    }
                });
                button.setText("结算(" +count+")");
    //            int num = myTextView.getNum();
    //            int price = myTextView.getPrice();
    //            Log.d("qq","打印的数量：" + num + "打印的价格：" + price);
    //            button.setText("结算(" +num+")");
    //            tv.setText("￥"+price+"");
                boolean itemCheck = CartFragment.this.child_list.get(i).get(i1).isItemCheck();
                if(itemCheck){
                    checkBox.setChecked(true);
                }else {
                    checkBox.setChecked(false);
                }
                checkBox.setOnClickListener(new ChildClickLister(i,i1,checkBox));
                TextView chile_name = (TextView) view.findViewById(R.id.child_name);
                TextView chile_price = (TextView) view.findViewById(R.id.child_price);
                String[] split = CartFragment.this.child_list.get(i).get(i1).getImages().split("\\|");
                ImageLoader.getInstance().displayImage(split[0],con_image);
                chile_name.setText(CartFragment.this.child_list.get(i).get(i1).getTitle());
                chile_price.setText("￥"+ CartFragment.this.child_list.get(i).get(i1).getPrice()+"");
                return view;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }

        }
        class ChildClickLister implements View.OnClickListener{


            private int i ;
            private int i1;
            private CheckBox cb_child ;
            public ChildClickLister(int i, int i1, CheckBox cb_child) {
                this.i1 = i1;
                this.i = i;
                this.cb_child = cb_child;
            }







    //        @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    //        public void MyFirstEvent1(FirstEvent firstEvent){
    //            myTextView.setText(firstEvent.getMsg());
    //        }
            @Override
            public void onClick(View view) {
                if(cb_child.isChecked()){
                    child_list.get(i).get(i1).setItemCheck(true);
                }else {
                    child_list.get(i).get(i1).setItemCheck(false);
                }
                //二级联动一级状态
                setParentCheckFlag();
                //判断全选状态
                int num = 0 ;
                for (int i = 0 ; i<group_list.size();i++){
                    if(!group_list.get(i).isAllCheck()){
                        num++;
                    }
                }
                if(num==0){
                    checkBox.setChecked(true);
                }else {
                    checkBox.setChecked(false);
                }
                //计算价格
                sum();
            }

            private void setParentCheckFlag() {
                SelectBean.DataBean dataBean = group_list.get(i);
                List<SelectBean.DataBean.ListBean> list = dataBean.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).isItemCheck()) {
                        dataBean.setAllCheck(false);
                        shuaxin();
                        return;
                    }
                    if (i == list.size() - 1) {
                        dataBean.setAllCheck(true);
                        shuaxin();
                        return;
                    }
                }
            }
        }

        class GroupLisener implements View.OnClickListener {

            private int i;
            CheckBox cb_group;

            public GroupLisener(int i, CheckBox cb_group) {
                this.i = i;
                this.cb_group = cb_group;
            }

            @Override
            public void onClick(View view) {

                if(cb_group.isChecked()){
                    setCheck(true);
                }else {
                    setCheck(false);
                    checkBox.setChecked(false);
                }
                    shuaxin();
            }

            private void setCheck(boolean b) {

                SelectBean.DataBean dataBean = group_list.get(i);
                List<SelectBean.DataBean.ListBean> list = dataBean.getList();
                dataBean.setAllCheck(b);
                for (SelectBean.DataBean.ListBean list1 : list){
                    list1.setItemCheck(b);
                }

                int num = 0 ;
                for (int i = 0 ; i<group_list.size();i++){
                    if(!group_list.get(i).isAllCheck()){
                        num++;
                    }
                }
                if(num==0){
                    checkBox.setChecked(true);
                }else {
                    checkBox.setChecked(false);
                }
                sum();
            }


        }

        private void shuaxin(){
            expand.setAdapter(new MyCatAdapter());
            int count = expand.getCount();
            for (int i=0;i<count;i++){
                expand.expandGroup(i);
            }
        }

        private void sum(){
            int price = 0 ;
            for (SelectBean.DataBean dd : group_list){
                List<SelectBean.DataBean.ListBean> list = dd.getList();
                for (SelectBean.DataBean.ListBean ddl : list){
                    if(ddl.isItemCheck()){
                        price+=ddl.getPrice()*ddl.getNum();
                    }
                }
            }
            tv.setText("￥"+price+"");
        }

    }
}