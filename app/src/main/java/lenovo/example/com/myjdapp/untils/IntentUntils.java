package lenovo.example.com.myjdapp.untils;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.List;

import lenovo.example.com.myjdapp.bean.ShopBean;


public class IntentUntils {


    /**
     * 不带参 传值
     * @param context
     * @param z
     */
    public static void setIntent(Context context,Class z){
        Intent intent = new Intent(context,z);
        context.startActivity(intent);
    }
    public static void setIntent(Context context, Class z, List<ShopBean> shoplist){
        Intent intent = new Intent(context,z);
        intent.putExtra("shoplist", (Serializable) shoplist);
        context.startActivity(intent);
    }
    /**
     * Int类型 传值
     * @param context
     * @param z
     */
    public static void setIntent(Context context, Class z,String key,int value){
        Intent intent = new Intent(context,z);
        intent.putExtra(key,value);
        context.startActivity(intent);
    }
    /**
     * String类型 传值
     * @param context
     * @param z
     */

    public static void setIntent(Context context, Class z,String key, String value){
        Intent intent = new Intent(context,z);
        intent.putExtra(key,value);
        context.startActivity(intent);
    }
    public static void setIntent(Context context, Class z,String key, int value,String key1,boolean flag){
        Intent intent = new Intent(context,z);
        intent.putExtra(key,value);
        intent.putExtra(key1,flag);
        context.startActivity(intent);
    }
}
