package lenovo.example.com.myjdapp.untils;

import android.content.Context;
import android.content.SharedPreferences;


public class SpUtils {

        public static void getSp(Context context,int uid,boolean flag){
            SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
            SharedPreferences.Editor edit = user.edit();
            edit.putInt("key",uid);
            edit.putBoolean("flag",flag);
            edit.commit();
        }
    public static void getSpPutString(Context context,String name,String phone){
        SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
            edit.putString("name",name);
            edit.putString("phone",phone);
        edit.commit();
    }
    public static String getSpName(Context context){
        SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
        String name = user.getString("name", "无名氏");
        return name ;
    }

    public static String getSpPhone(Context context){
        SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
        String phone = user.getString("phone", "110");
        return phone ;
    }
    public static void getSp(Context context,String s1){
        SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("string",s1);
        edit.commit();
    }
    public static String getSpstring(Context context){
        SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
        String string = user.getString("string", "1");
        return string ;
    }
        public static int getSpUid(Context context){
            SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
            int key = user.getInt("key", 0);
             return key ;
        }
        public static boolean getSpFlag(Context context){
            SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
            boolean flag = user.getBoolean("flag", false);
            return flag ;
        }
    public static void removeFlag(Context context){
        SharedPreferences user = context.getSharedPreferences("user", context.MODE_PRIVATE);
        SharedPreferences.Editor flag = user.edit()
                .remove("flag")
                .remove("uid");
        flag.commit();
    }
}
