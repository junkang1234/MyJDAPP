package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.HomeBean;


public class MyMiaoShaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


   private HomeBean.MiaoshaBean miaoshabean = new HomeBean.MiaoshaBean();
    private Context context ;

    public MyMiaoShaAdapter(HomeBean.MiaoshaBean miaoshabean, Context context) {
        this.miaoshabean = miaoshabean;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.miaosha_layout,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if(holder instanceof MyViewHolder){
                ((MyViewHolder) holder).t1.setText("￥"+miaoshabean.getList().get(position).getPrice()+"");
                SpannableString sp = new SpannableString(miaoshabean.getList().get(position).getBargainPrice()+"");
                sp.setSpan(new StrikethroughSpan(), 0, (miaoshabean.getList().get(position).getBargainPrice()+"").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((MyViewHolder) holder).t2.setText(sp);
                String images = miaoshabean.getList().get(position).getImages();
                String str = "\\|";
                String[] split = images.split(str);
//                List<String> list = new ArrayList<>();
                    //log测试输出
//                    list.add(s);
                ImageLoader.getInstance().displayImage(split[0],((MyViewHolder) holder).i);
            }
    }

    @Override
    public int getItemCount() {
        return miaoshabean.getList().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private  TextView t1;
        private  TextView t2;
        private  ImageView i;

        public MyViewHolder(View itemView) {
            super(itemView);
           t1 = (TextView) itemView.findViewById(R.id.text_price1);
            t2 = (TextView) itemView.findViewById(R.id.tv_price2);
            i = (ImageView) itemView.findViewById(R.id.image_icon);
        }

    }

}
