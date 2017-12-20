package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.TakeGoodsBean;
import lenovo.example.com.myjdapp.presenter.MorenPresenter;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.view.MorenView;


public class MyTakeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MorenView {

    private List<TakeGoodsBean.DataBean> list = new ArrayList<>();
    private Context context ;
    private MorenPresenter morenPresenter ;

    public MyTakeAdapter(List<TakeGoodsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        morenPresenter = new MorenPresenter(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.take_item, parent, false);
        MyViewHolder m = new MyViewHolder(inflate);
        return m;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
                if(holder instanceof MyViewHolder){
                     ((MyViewHolder) holder).t_name.setText(list.get(position).getName());
                    ((MyViewHolder) holder).t_mobile.setText(list.get(position).getMobile()+"");
                    ((MyViewHolder) holder).t_address.setText(list.get(position).getAddr());
//                    if(list.get(position).getStatus()==1){
//                        ((MyViewHolder) holder).c_moren.setChecked(true);
//                        ((MyViewHolder) holder).c_moren.setText("默认地址");
//                        list.get(position).setFlag(true);
//                    }else {
//                        ((MyViewHolder) holder).c_moren.setChecked(false);
//                        ((MyViewHolder) holder).c_moren.setText("设为默认");
//                        list.get(position).setFlag(false);
//                    }
                    if(list.get(position).isFlag()){
                            ((MyViewHolder) holder).c_moren.setChecked(true);
                            ((MyViewHolder) holder).c_moren.setText("默认地址");
                    }else {
                        ((MyViewHolder) holder).c_moren.setChecked(false);
                        ((MyViewHolder) holder).c_moren.setText("设为默认");
                    }
                    ((MyViewHolder) holder).c_moren.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (int i=0;i<list.size();i++){
                                list.get(i).setFlag(false);
                            }
                            list.get(position).setFlag(true);

                            if(((MyViewHolder) holder).c_moren.isChecked()){
                                // 1默认地址状态
                                morenPresenter.getData(SpUtils.getSpUid(context)+"",list.get(position).getAddrid()+"",1+"");
                                ((MyViewHolder) holder).c_moren.setText("默认地址");
                            }else {
                                ((MyViewHolder) holder).c_moren.setText("设为默认");
                            }
                            notifyDataSetChanged();
                        }
                    });
                }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void Suceess(BaseBean baseBean) {
          //吐司事件
           Toast.makeText(context,"设置成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Error(BaseBean baseBean) {
        //吐司事件
        Toast.makeText(context,"设置失败",Toast.LENGTH_SHORT).show();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private  TextView t_name;
        private  TextView t_mobile;
        private  TextView t_address;
        private  CheckBox c_moren;
        private  RelativeLayout r_bianji;
        private  RelativeLayout r_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            t_name = itemView.findViewById(R.id.tv_name);
            t_mobile = itemView.findViewById(R.id.tv_mobile);
            t_address = itemView.findViewById(R.id.tv_address);
            c_moren = itemView.findViewById(R.id.cb_moren);
            r_bianji = itemView.findViewById(R.id.r_bianji);
            r_delete = itemView.findViewById(R.id.r_delete);
        }
    }


}
