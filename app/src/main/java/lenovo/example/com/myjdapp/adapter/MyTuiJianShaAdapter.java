package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.HomeBean;


public class MyTuiJianShaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


   private HomeBean.TuijianBean miaoshabean = new HomeBean.TuijianBean();
    private Context context ;

    public MyTuiJianShaAdapter(HomeBean.TuijianBean miaoshabean, Context context) {
        this.miaoshabean = miaoshabean;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.tujian_layout,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if(holder instanceof MyViewHolder){
                List<HomeBean.TuijianBean.ListBean> list = miaoshabean.getList();
                ((MyViewHolder) holder).t1.setText(list.get(position).getTitle());
                String images = list.get(position).getImages();
                String str = "\\|";
                String[] split = images.split(str);
                ImageLoader.getInstance().displayImage(split[0],((MyViewHolder) holder).i);
                ((MyViewHolder) holder).t2.setText("￥" + list.get(position).getPrice());
            }
    }

    @Override
    public int getItemCount() {
        return miaoshabean.getList().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private  TextView t1;
        private  ImageView i;
        private  TextView t2;

        public MyViewHolder(View itemView) {
            super(itemView);
           t1 = (TextView) itemView.findViewById(R.id.text_name);
            i = (ImageView) itemView.findViewById(R.id.image_info);
            t2 = (TextView) itemView.findViewById(R.id.ttt_price);
        }

    }

}
