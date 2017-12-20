package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.ShopListBean;


public class MyShopListAdapter extends RecyclerView.Adapter{

    private Context context ;
    private List<ShopListBean.DataBean> list = new ArrayList<>();
    private int layout ;
    public MyShopListAdapter(Context context, List<ShopListBean.DataBean> list,int layout) {
        this.context = context;
        this.list = list;
        this.layout=layout;
    }

    public void adData(List<ShopListBean.DataBean> l){
        list.addAll(l);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, layout,null);
        MyItemViewholder myItemViewholder = new MyItemViewholder(view);
        return myItemViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyItemViewholder){
            ((MyItemViewholder) holder).tt.setText(list.get(position).getTitle());
            ((MyItemViewholder) holder).tp.setText("￥"+list.get(position).getPrice());
            String images = list.get(position).getImages();
            String[] split = images.split("\\|");
            ImageLoader.getInstance().displayImage(split[0],((MyItemViewholder) holder).i);
            ((MyItemViewholder) holder).ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onitemClickListenter.setOnclick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyItemViewholder extends RecyclerView.ViewHolder{

        private  TextView tt;
        private  TextView tp;
        private  ImageView i;
        private  LinearLayout ll;

        public MyItemViewholder(View itemView) {
            super(itemView);
            tt = (TextView) itemView.findViewById(R.id.tv_shoplist_title);
            tp = (TextView) itemView.findViewById(R.id.tv_shoplist_price);
            i = (ImageView) itemView.findViewById(R.id.image_left);
            ll = (LinearLayout) itemView.findViewById(R.id.linear4);
        }
    }

    private OnitemClickListenter onitemClickListenter ;

    public void setOnitemClickLisenter(OnitemClickListenter onClick) {
        this.onitemClickListenter = onClick;
    }

    public interface OnitemClickListenter{
        void setOnclick(int position);
    }
}
