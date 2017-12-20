package lenovo.example.com.myjdapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import lenovo.example.com.myjdapp.R;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener{


    private boolean isStatus = false ; //沉浸式状态栏是否支持透明
    private boolean isActionbar = false ; //actionbar是否显示
    private boolean isFullScreen = false ; //是否全屏展示
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        
    }

    /**
     * 设置是否显示沉浸式
     */
    public void setStatus(boolean isstatus){
        isStatus = isstatus ;
        if(isStatus){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }
    /**
     * 设置是否显示actionbar
     */
    public void setActionbar(boolean isAction){
        isActionbar = isAction ;
        if(isActionbar){
            getSupportActionBar().show();
        }else {
            getSupportActionBar().hide();
        }
    }
    /**
     * 设置是否显示全屏
     */
    public void setFullScreen(boolean isFull){
        isFullScreen = isFull ;
        if(isFullScreen){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onClick(View view) {



    }
    /**
     * 设置点击事件
     */


}
