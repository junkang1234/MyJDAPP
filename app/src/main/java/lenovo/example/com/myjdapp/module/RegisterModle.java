package lenovo.example.com.myjdapp.module;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.common.Api;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RegisterModle {
    //创建handler对象并且实现其方法
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String resault = data.getString("register");
            Gson g = new Gson();
            BaseBean baseBean = g.fromJson(resault, BaseBean.class);
            if (baseBean.getCode().equals("0")){
                registerR.registerSuccess(baseBean.getCode(),baseBean.getMsg());
            }else{
                registerR.registerFail(baseBean.getCode(),baseBean.getMsg());
            }
        }
    };
    public void Register(final String mobile, final String pwd){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",pwd);
        builder.add("token","0");
        FormBody build = builder.build();
        Request request = new Request.Builder()
                .post(build)
                .url(Api.REGISTER_API)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    String result = response.body().string();
                    System.out.println("result======="+result);
                    //走解析逻辑，解析完成走如下逻辑
                    Bundle b = new Bundle();
                    b.putString("register",result);
                    Message message = new Message();
                    message.setData(b);
                    handler.sendMessage(message);
                }
            }
        });
    }
    private RegisterR registerR ;
    public interface RegisterR{
        void registerSuccess(String code, String msg);
        void registerFail(String code, String msg);
    }
    public void setRegisterR(RegisterR registerR) {
        this.registerR = registerR;
    }
}
