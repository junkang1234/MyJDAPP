package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import lenovo.example.com.myjdapp.R;


public class Shouye_Grid_Layout extends BaseAdapter{

     Context context ;
         String[] listiamge ;
         String[] lsittitle ;
         int layout ;

      public Shouye_Grid_Layout(Context context,String[] lsittitle,String[] listiamge, int layout){

          this.context = context;
          this.lsittitle = lsittitle;
          this.listiamge = listiamge ;
          this.layout = layout;

      }

         @Override
         public int getCount() {
             return listiamge!=null?listiamge.length:0;
         }

         @Override
         public String getItem(int i) {
             return listiamge[i];
         }

         @Override
         public long getItemId(int i) {
             return i;
         }

         @Override
         public View getView(int i, View view, ViewGroup viewGroup) {

             ViewHolder holder = null;
             if(view==null) {
                 view = View.inflate(context, layout, null);
                 holder = new ViewHolder(view);
                 //查找ID操作

                 view.setTag(holder);
             }else {

                 holder = (ViewHolder) view.getTag();

             }
             //赋值操作
             ImageLoader.getInstance().displayImage(listiamge[i],holder.i);
             holder.t.setText(lsittitle[i]);

             return view;
         }

         class ViewHolder{

             private  ImageView i;
             private  TextView t;

             //重复性的控件
                public ViewHolder(View v){

                    i = (ImageView) v.findViewById(R
                            .id.image);
                    t = (TextView) v.findViewById(R.id.text);
                }

         }


}
