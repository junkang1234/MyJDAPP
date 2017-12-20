package lenovo.example.com.myjdapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.adapter.MyFourAdapter;
import lenovo.example.com.myjdapp.bean.OrderBean;
import lenovo.example.com.myjdapp.presenter.OrderPresenter;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.OrderView;


public class FourFragment extends Fragment implements OrderView {
    private RelativeLayout relv;
    private RecyclerView recy;
    private OrderPresenter orderPresenter ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fourfragment,null);
        initViw(view);
        loadData();

        return view;
    }
    private void loadData() {
        orderPresenter.getData(getActivity(), SpUtils.getSpUid(getActivity())+"");
    }
    private void initViw(View view) {

        relv = view.findViewById(R.id.linear1);
        recy = view.findViewById(R.id.recy1);
        orderPresenter = new OrderPresenter(this);

    }

    @Override
    public void Success(OrderBean baseBean) {
        List<OrderBean.DataBean> data = baseBean.getData();
        //log测试输出
        List<OrderBean.DataBean> data1 = new ArrayList<>();
        for (int j = 0; j < data.size(); j++){
            if(data.get(j).getStatus()==1){
                data1.add(data.get(j));
            }
        }
        if(data1!=null&&data1.size()!=0){
            recy.setVisibility(View.VISIBLE);
            recy.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            recy.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            recy.setAdapter(new MyFourAdapter(getActivity(),data1));
        }else {
            relv.setVisibility(View.VISIBLE);
        }
    }
}
