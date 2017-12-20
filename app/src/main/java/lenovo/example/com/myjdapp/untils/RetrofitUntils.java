package lenovo.example.com.myjdapp.untils;

import android.content.Context;


import lenovo.example.com.myjdapp.bean.OrderBean;
import lenovo.example.com.myjdapp.view.NetClickListener1;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUntils {
  
    private static RetrofitUntils retrofitUntils;  
  
    private Context context;
  
    private RetrofitUntils(Context context){  
        this.context = context ;  
    }  
  
    public static RetrofitUntils getRetrofitUntils(Context context){  
        if(retrofitUntils==null){  
            synchronized (RetrofitUntils.class){  
                if(retrofitUntils==null){  
                    retrofitUntils = new RetrofitUntils(context);  
                }  
            }  
        }  
        return  retrofitUntils ;  
    }  
  
    public void get(String uid, final NetClickListener1 netClickListener){
        OkHttpClient.Builder ok = new OkHttpClient.Builder();
        ok.addInterceptor(new LogInterceptor());  
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")//这里的url与实现接口中的url拼接成一个完整的URL  
                .client(ok.build())  
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();  
        retrofit.create(Retrofit_Interface.class).getBean(uid)  
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderBean>() {
                    @Override  
                    public void onCompleted() {  
  
                    }  
  
                    @Override  
                    public void onError(Throwable e) {  

                    }  
  
                    @Override  
                    public void onNext(OrderBean baseBean) {
                        if(baseBean.getCode().equals("0")){  
                            if(netClickListener!=null){  
                                netClickListener.Suesses(baseBean);  
                            }  
                        }  
                    }  
                });  
    }  
  
  
}  
