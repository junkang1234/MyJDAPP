package lenovo.example.com.myjdapp.common;


public class Api {

    public static  final String API_IP = "http://120.27.23.105";
    public static  final String API_IP1 = "https://www.zhaoapi.cn/user/";
    public static  final String LOGIN_API = API_IP + "/user/login";
    public static  final String REGISTER_API = API_IP + "/user/reg";
//    public static  final String UP_LOAD_IMAGE_API = API_IP +"/user/upload";
    public static  final String USER_INFO_API = API_IP + "/user/getUserInfo";
    //返回参数说明 type：0 跳转到活动页  type:1 跳转到商品详情页
    public static  final String SHOU_YE_BANNER_API = API_IP + "/ad/getAd";
    //返回参数说明  ishome：1 首页显示  ishome:0 首页不显示
    public static  final String SHOP_FENLEI_INFO_API = API_IP + "/product/getCatagory";
    public static final String FENLEI_CHILD_API = API_IP + "/product/getProductCatagory";
    // 子分类下的商品列表
    public static final String SHOP_LISTVIEW = API_IP + "/product/getProducts";
    //搜索商品 参数 keywords 必须
    public static final String SEARCH_API = API_IP +"/product/searchProducts";
    //详情页的商品信息
    public static final String SHOP_XINXI_API = API_IP + "/product/getProductDetail";
    //添加购物车
    public static final String ADD_CART_API = API_IP + "/product/addCart";
    //查询购物车
    public static final String SELECT_CART_API = API_IP + "/product/getCarts";
    //修改订单的状态   0 待支付 1已支付 2已取消
    public static final String UPDATE_ORDER_STATUS = API_IP1 + "product/updateOrder";
    //创建订单
    public static final String CHUANGJIAN = API_IP + "/product/createOrder";
    //修改昵称
    public static final String UPDATA_NICKNAME = API_IP1 + "product/updateNickName";
    //查询收货地址
    public static final String SELECT_ADDRESS = API_IP1 + "getAddrs";
    //新建收货地址
    public static final String NEWS_ADDRESS = API_IP+"/user/addAddr";
    //修改收货地址
    public static final String UPDATA_ADDRESS = API_IP+"/user/updateAddr";
    //设置默认地址
    public static final String MOREN_ADDRESS = API_IP+"/user/setAddr";
    //获取默认地址
    public static final String GET_MOREN_ADDRESS = API_IP+"/user/getDefaultAddr";
    /**
     * 分享的url
     */
    public static final String URL = "http://mobile.umeng.com/social";

}
