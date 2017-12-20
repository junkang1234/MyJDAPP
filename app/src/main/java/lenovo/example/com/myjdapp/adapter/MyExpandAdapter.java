package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.activity.ShopListActivity;
import lenovo.example.com.myjdapp.bean.FenLeiChildBean;
import lenovo.example.com.myjdapp.untils.CustomGridView;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.ViewUnitls;


public class MyExpandAdapter extends BaseExpandableListAdapter{

    private List<FenLeiChildBean.DataBean> group_list = new ArrayList<>();
    private List<List<FenLeiChildBean.DataBean.ListBean>> child_list = new ArrayList<>();
    private Context context ;
    List<FenLeiChildBean.DataBean.ListBean> list = new ArrayList<>();
    public MyExpandAdapter(List<FenLeiChildBean.DataBean> group, List<List<FenLeiChildBean.DataBean.ListBean>> child, Context context) {
        this.group_list = group;
        this.child_list = child;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
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
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        View view1 = ViewUnitls.setVeiw(context, R
                .layout.group_layout);
       TextView group = (TextView) view1.findViewById(R.id.group_tv);
        group.setText(group_list.get(i).getName());
        return view1;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {

        for (List<FenLeiChildBean.DataBean.ListBean> l : child_list){
            list.addAll(l);
        }

        View view1 = ViewUnitls.setVeiw(context, R.layout.grideview);
       CustomGridView gridView = (CustomGridView) view1.findViewById(R.id.gride);
        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return child_list.get(i).size();
            }

            @Override
            public Object getItem(int i2) {
                return child_list.get(i).get(i1);
            }



            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int i2, View view, ViewGroup viewGroup) {
                if(view == null){
                     view = View.inflate(context,R.layout.gride1,null);
                  ImageView imageView = (ImageView) view.findViewById(R.id.imagefenlei);
                  TextView textView = (TextView) view.findViewById(R.id.textfenlei);
                    ImageLoader.getInstance().displayImage(child_list.get(i).get(i2).getIcon(),imageView);
                    textView.setText(child_list.get(i).get(i2).getName());
                }

                return view;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int q, long l) {
                int pscid = child_list.get(i).get(q).getPscid();
                IntentUntils.setIntent(context, ShopListActivity.class,"pscid",pscid);


            }
        });

        return view1;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
