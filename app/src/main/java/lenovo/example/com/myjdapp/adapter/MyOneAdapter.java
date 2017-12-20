package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.OrderBean;


public class MyOneAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context ;
    private List<OrderBean.DataBean> data = new ArrayList<>();

    public MyOneAdapter(Context context, List<OrderBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    private static final int TYPE1 = 0;
    private static final int TYPE2 = 1;
    private static final int TYPE3 = 2;

    @Override
    public int getItemViewType(int position) {

        OrderBean.DataBean dataBean = data.get(position);
        int status = dataBean.getStatus();
        if(status==0){
             return TYPE1 ;
        }else if(status==1){
             return TYPE2;
        }else if(status==2){
             return TYPE3 ;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == TYPE1){

                MyTYPEone myTYPEone = new MyTYPEone(LayoutInflater.from(context).inflate(R.layout.oneadapter
                        ,
                        parent, false));
                return myTYPEone ;
            }else if(viewType == TYPE2){
                MyTYPEtwo myTYPEtwo = new MyTYPEtwo(LayoutInflater.from(context).inflate(R.layout.twoadapter
                        ,
                        parent, false));
                return myTYPEtwo ;
            }else if(viewType == TYPE3){
                MyTYPEthree myTYPEthree = new MyTYPEthree(LayoutInflater.from(context).inflate(R.layout.threeadapter
                        ,
                        parent, false));
                return myTYPEthree ;
            }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyTYPEone){
                ((MyTYPEone) holder).t_price.setText("￥" + data.get(position).getPrice());
                ((MyTYPEone) holder).t_time.setText("订单时间：" + data.get(position).getCreatetime());
        }else if(holder instanceof MyTYPEtwo){
            ((MyTYPEtwo) holder).t_price.setText("￥" + data.get(position).getPrice());
            ((MyTYPEtwo) holder).t_time.setText("订单时间：" + data.get(position).getCreatetime());
        }else if(holder instanceof MyTYPEthree){
            ((MyTYPEthree) holder).t_price.setText("￥" + data.get(position).getPrice());
            ((MyTYPEthree) holder).t_time.setText("订单时间：" + data.get(position).getCreatetime());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyTYPEone extends RecyclerView.ViewHolder {

        private  TextView t_time;
        private  TextView t_price;

        public MyTYPEone(View itemView) {
            super(itemView);

            t_time = itemView.findViewById(R.id.one_time);
            t_price = itemView.findViewById(R.id.one_price);

        }
    }
    class MyTYPEtwo extends RecyclerView.ViewHolder {
        private  TextView t_time;
        private  TextView t_price;
        public MyTYPEtwo(View itemView) {
            super(itemView);

            t_time = itemView.findViewById(R.id.one_time);
            t_price = itemView.findViewById(R.id.one_price);

        }
    }
    class MyTYPEthree extends RecyclerView.ViewHolder {
        private  TextView t_time;
        private  TextView t_price;
        public MyTYPEthree(View itemView) {
            super(itemView);
            t_time = itemView.findViewById(R.id.one_time);
            t_price = itemView.findViewById(R.id.one_price);
        }
    }
}
