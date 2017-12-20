package lenovo.example.com.myjdapp.untils;

import android.content.Context;
import android.view.View;


public class ViewUnitls {

    public static View setVeiw(Context context,int layout){

        View view = View.inflate(context,layout,null);
        return view ;
    }

}
