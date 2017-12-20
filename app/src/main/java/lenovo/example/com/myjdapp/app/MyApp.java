package lenovo.example.com.myjdapp.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import lenovo.example.com.myjdapp.R;


public class MyApp extends Application {

    private UMShareAPI umShareAPI;

    public UMShareAPI getUmShareAPI() {
        return umShareAPI;
    }

    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        umShareAPI = UMShareAPI.get(this);

        ZXingLibrary.initDisplayOpinion(this);

                        //设置图片的显示
                                DisplayImageOptions options = new DisplayImageOptions.Builder()
                                        .showImageOnFail(R.mipmap.ic_launcher)
                                        .showImageForEmptyUri(R.mipmap.ic_launcher)
                                        .showImageOnLoading(R.mipmap.ic_launcher)
                                        .showImageOnFail(R.mipmap.ic_launcher)
                                        .build();
                                ImageLoaderConfiguration i = new ImageLoaderConfiguration.Builder(this)
                                        .defaultDisplayImageOptions(options)
                                        .build();
                                        //初始化
                                ImageLoader.getInstance().init(i);

//        RongIM.init(this);//初始化,然后记得在清单文件配置此类。



    }
}
