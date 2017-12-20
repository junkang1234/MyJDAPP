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


public class MyFourAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context ;
    private List<OrderBean.DataBean> data = new ArrayList<>();

    public MyFourAdapter(Context context, List<OrderBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyTYPEtwo myTYPEtwo = new MyTYPEtwo(LayoutInflater.from(context).inflate(R.layout.twoadapter
                ,
                parent, false));
        return myTYPEtwo ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyTYPEtwo){
            ((MyTYPEtwo) holder).t_price.setText("￥" + data.get(position).getPrice());
            ((MyTYPEtwo) holder).t_time.setText("订单时间：" + data.get(position).getCreatetime());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyTYPEtwo extends RecyclerView.ViewHolder {
        private TextView t_time;
        private  TextView t_price;
        public MyTYPEtwo(View itemView) {
            super(itemView);

            t_time = itemView.findViewById(R.id.one_time);
            t_price = itemView.findViewById(R.id.one_price);

        }
    }
}
