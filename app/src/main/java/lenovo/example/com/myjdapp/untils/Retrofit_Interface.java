package lenovo.example.com.myjdapp.untils;


import lenovo.example.com.myjdapp.bean.OrderBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Retrofit_Interface {
    @GET("product/getOrders")//在这里我们用到了Retrofit的get请求
    Observable<OrderBean> getBean(@Query("uid") String uid);
}