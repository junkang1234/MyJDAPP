package lenovo.example.com.myjdapp.untils;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;

import lenovo.example.com.myjdapp.bean.BaseBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpOkUntils {

    private static volatile HttpOkUntils httpUtils ;
    private final Gson gson;
    private Context context ;
    private Handler handler ;

    public HttpOkUntils(){

        gson = new Gson();
        handler = new Handler();

    }

        public static HttpOkUntils getHttpUtils(){

            if(httpUtils == null){

                synchronized (HttpOkUntils.class){

                    if(httpUtils == null){

                        httpUtils = new HttpOkUntils();

                    }

                }

        }
        return  httpUtils ;
    }

    public void OkGet(String url, final Class clazz, final NetClickListener netclicklistener){
        OkHttpClient.Builder ok = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor());
        Request request = new Request.Builder()
                .url(url)
                .build();
        ok.build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                            BaseBean baseBean = (BaseBean) gson.fromJson(string, clazz);
                            if(baseBean.getCode().equals("0")){
                                if(netclicklistener!=null){
                                    netclicklistener.Suesses(baseBean);
                                }
                            }
                    }
                });
            }
        });
    }

}
