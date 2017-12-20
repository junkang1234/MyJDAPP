package lenovo.example.com.myjdapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.FenLeiChildBean;


public class MyGridViewAdapter extends BaseAdapter {

     Context context ;
    List<List<FenLeiChildBean.DataBean>> data ;
         int layout ;

      public MyGridViewAdapter(Context context, List<List<FenLeiChildBean.DataBean>> data, int layout){

          this.context = context;
          this.data = data;
          this.layout = layout;

      }

         @Override
         public int getCount() {
             return data!=null?data.size():0;
         }

         @Override
         public FenLeiChildBean.DataBean getItem(int i) {
             return data.get(i).get(i);
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

//            holder.t.setText(data.get(i).getList().get(i).getName());
//             ImageLoader.getInstance().displayImage(data.get(i).getList().get(i).getIcon(),holder.i);

             return view;
         }

         class ViewHolder{
             private  ImageView i;
             private  TextView t;

             //重复性的控件
             public ViewHolder(View v){
                i = (ImageView) v.findViewById(R.id.imagefenlei);
                 t = (TextView) v.findViewById(R.id.textfenlei);
             }


         }

}
