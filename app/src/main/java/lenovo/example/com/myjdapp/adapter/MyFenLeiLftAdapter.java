package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.FenLeiBean;


public class MyFenLeiLftAdapter extends RecyclerView.Adapter {


    private Context context ;
    private List<FenLeiBean.DataBean> data = new ArrayList<>();

    public MyFenLeiLftAdapter(Context context, List<FenLeiBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.fenleileft_layout,null);
        MyItemHolder itemHolder = new MyItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if(holder instanceof MyItemHolder){
                ((MyItemHolder) holder).t.setText(data.get(position).getName());
                ((MyItemHolder) holder).l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onclickListener.setOnClickListener(position);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyItemHolder extends RecyclerView.ViewHolder{

        private  TextView t;
        private  LinearLayout l;

        public MyItemHolder(View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.tv_fenlei_left);
            l = itemView.findViewById(R.id.linaer1);
        }
    }
    private OnclickListener onclickListener ;

    public interface OnclickListener{
          void setOnClickListener(int position);
    }

    public void setOnItemclickListener(OnclickListener onItemclickListener){
        this.onclickListener =onItemclickListener ;
    }

}
