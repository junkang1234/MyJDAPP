package lenovo.example.com.myjdapp.untils;

import android.content.Context;
import android.widget.Toast;


public class ToastUntils {

    public static  void setToast(Context context,String s){
         //吐司事件
          Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }

}
