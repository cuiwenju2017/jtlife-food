package commeishi.shanjing.mymeishi.api;

import com.base.commonlib.bean.BaseData;
import commeishi.shanjing.mymeishi.bean.AddShopBean;
import commeishi.shanjing.mymeishi.bean.CommentNumBean;
import commeishi.shanjing.mymeishi.bean.EditShopBean;
import commeishi.shanjing.mymeishi.bean.HomeLeftBean;
import commeishi.shanjing.mymeishi.bean.HomeRightBean;
import commeishi.shanjing.mymeishi.bean.MessageBean;
import commeishi.shanjing.mymeishi.bean.MessageNotificationBean;
import commeishi.shanjing.mymeishi.bean.ShoppingCarDataBean;
import commeishi.shanjing.mymeishi.bean.TheOrderDetailsBean;
import commeishi.shanjing.mymeishi.bean.Wholebeandd;
import commeishi.shanjing.mymeishi.model.Aftershxqbean;
import commeishi.shanjing.mymeishi.model.AppoiExpressbean;
import commeishi.shanjing.mymeishi.model.Buydiatelybean;
import commeishi.shanjing.mymeishi.model.Collscbean;
import commeishi.shanjing.mymeishi.model.Deletecolle;
import commeishi.shanjing.mymeishi.model.Dologin;
import commeishi.shanjing.mymeishi.model.GetAddressbean;
import commeishi.shanjing.mymeishi.model.GetKayue;
import commeishi.shanjing.mymeishi.model.GetReturnOrderTypeList;
import commeishi.shanjing.mymeishi.model.Getordernum;
import commeishi.shanjing.mymeishi.model.Goosmessges;
import commeishi.shanjing.mymeishi.model.Homeyoublibu;
import commeishi.shanjing.mymeishi.model.Putscbean;
import commeishi.shanjing.mymeishi.model.Qxdingdanbean;
import commeishi.shanjing.mymeishi.model.Reasonsbean;
import commeishi.shanjing.mymeishi.model.Registbean;
import commeishi.shanjing.mymeishi.model.Serchbeangoods;
import commeishi.shanjing.mymeishi.model.Settlementbean;
import commeishi.shanjing.mymeishi.model.Shopcollbean;
import commeishi.shanjing.mymeishi.model.Shouhoubean;
import commeishi.shanjing.mymeishi.model.Sppjlistbean;
import commeishi.shanjing.mymeishi.model.Tjyhpinglun;
import commeishi.shanjing.mymeishi.model.Touxiangbean;
import commeishi.shanjing.mymeishi.model.Tuihuobean;
import commeishi.shanjing.mymeishi.model.Wlgslistbean;
import commeishi.shanjing.mymeishi.model.Xqwupwc;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    // 首页导航
    @GET("Category/GetCategoryListIndex?shownum=100")
    Call<HomeLeftBean> getCall();
    //首页内容右部里层
    @GET("Index/GetCmsPageList/{page}?cid=1")
    Call<HomeRightBean> getHomeRight(@Path("page") int page);
    @GET("Index/GetCmsPageList/{page}")
    Observable<BaseData<Homeyoublibu>> getHomeneibu(@Path("page") int page, @Query("cid") String id);
    @GET("Index/GetCmsPageList/{page}")
    Call<HomeRightBean> getHomeRightddd(@Path("page") int page, @Query("cid") String id);
    //添加购物车
    @POST("ShoppingCart/AddShoppingCart")
    Observable<BaseData<AddShopBean>> getDd(@Body RequestBody route);
    //全部订单
    @GET("Order/GetPageList/{page}")
    Observable<BaseData<Wholebeandd>> orderqb(@Path("page") int page, @Query("orderstatus") String type);
    //订单详情
    @GET("Order/GetOrderDetailByID/{id}")
    Observable<Xqwupwc> Orderdetails(@Path("id") String byid);
    @GET("Order/GetOrderDetailByID/{id}")
    Observable<Xqwupwc> Orderdetailswc(@Path("id") String byid);
    //我的收藏
    @GET("MemberCenter/GetCollectPageList/{page}")
    Observable<BaseData<Collscbean>> calletsc(@Path("page") int page, @Query("type") String type);
    //删除用户收藏
    @DELETE("MemberCenter/DelCollectByID/{id}")
    Observable<Deletecolle> deletesc(@Path("id") String id);
    //购物车列表
    @GET("ShoppingCart/GetCartPageList")
    Observable<BaseData<List<ShoppingCarDataBean.DataBean>>> getShoppCar(@Query("page") int page);
    //删除购物车内容
    @DELETE("ShoppingCart/DelShoppingCart/{id}")
    Observable<BaseData<AddShopBean>> deletShopoCar(@Path("id") String id);
    //编辑购物车
    @PUT("ShoppingCart/EditShoppingCart")
    Observable<BaseData<EditShopBean>> editShoppCar(@Body RequestBody route);
    //是否收藏店铺2，商品0，内容1
    @GET("MemberCenter/isFav/{id}")
    Observable<BaseData<Shopcollbean>> dianpu(@Path("id") String id, @Query("type") int type);
    //添加收藏
    @POST("MemberCenter/AddCollect")
    Observable<BaseData<Putscbean>> putcshoucang(@Body RequestBody route);
    //评论数量
    @GET("Goods/GetGoodsJudgeNum/{id}")
    Observable<BaseData<CommentNumBean>> getCommentNum(@Path("id") String id);
    //商品详情页
    @GET("Goods/GetGoodsDetailByID/{id}")
    Observable<Goosmessges> goodsmessage(@Path("id") String id);
    //头像
    @POST("MemberCenter/UploadAvster")
    Observable<BaseData<Touxiangbean>> touxiang(@Part("file\"; filename=\"avatar.jpg") RequestBody file);
    //取消订单
    @PUT("Order/CancelOrder/{id}")
    Observable<BaseData<Qxdingdanbean>> quxiaodd(@Path("id")String id);
    //消息
    @GET("Msg/GetMsgIndex")
    Observable<MessageBean> getMessage();
    //消息通知
    @GET("Msg/GetMsgPageList/{page}")
    Observable<MessageNotificationBean> getMessageNotification(@Path("page") int page, @Query("type") int type);
    //消息详情
    @GET("Msg/GetMsgByID/{id}")
    Observable<TheOrderDetailsBean> getTheOrderdetails(@Path("id") String id);
    //获取总的订单数
    @GET("Order/GetOrderNum")
    Observable<BaseData<Getordernum>> getordernum();
    //获取售后订单
    @GET("Order/GetReturnOrderPageList/{page}")
    Observable<Shouhoubean> shouhou(@Path("page")int page, @Query("orderstatus")String orderstatus);
    //获取订单售后详情
    @GET("Order/GetReturnOrderDetailByID/{id}")
    Observable<Aftershxqbean> aftershxq(@Path("id") String id);
    //退货订单
    @POST("MemberCenter/AddCollect")
    Observable<BaseData<Tuihuobean>> tuihuo(@Body RequestBody route);
    @GET("Order/GetReturnOrderTypeList")
    Observable<GetReturnOrderTypeList> typeList();
    //获取退货原因列表
    @GET("Order/GetReturnOrderReasonList")
    Observable<Reasonsbean> reaslist();
    //获取物流公司列表
    @GET("Order/GetExpressList")
    Observable<Wlgslistbean>wlgshq();

    //获取默认地址
    @GET("MemberCenter/GetAddressDefault")
    Observable<GetAddressbean>getaddress();
    @POST("Order/AppointmentExpress")
    Observable<AppoiExpressbean> yuyuekuadi(@Body RequestBody route);
    //购物车结算
    @POST("Order/AddCartOrder")
    Observable<Settlementbean> jiesuan(@Body RequestBody route);
    //立即购买
    @POST("Order/ImmediateOrder")
    Observable<Buydiatelybean> Buyimmediately(@Body RequestBody route);
    //获取卡包余额
    @GET("MemberCenter/GetMemberAccount")
    Observable<GetKayue> GetMemberAccount();
   //获取用户卡券使用记录
    @GET("MemberCenter/GetAccountList")
    Observable<GetKayue> GetAccountList();
    //添加用户评价
    @POST("MemberCenter/AddJudge")
    Observable<Tjyhpinglun> AddJudge(@Body RequestBody route);
    //获取商品评价列表
    @GET("Goods/GetGoodsJudgePageList/{page}")
    Observable<Sppjlistbean> sppjlist(@Path("page")int page, @Query("goodsid")String goodsid);

   //添加用户地址
   @POST("MemberCenter/AddAddress")
   Observable<Tjyhpinglun> getaddress(@Body RequestBody route);
  //获取频道页分类商品列表
  @GET("Goods/GetPageList/{page}")
  Observable<Serchbeangoods> shouyeserch(@Path("page") int page, @Query("keywords") String keywords, @Query("maxprice")String maxprice);
    //用户获取验证码
    @GET("Member/registSmsCode")
    Observable<Registbean> regiszc(@Query("phone")String phone, @Query("platform")int platform  );
    //用户注册
    @POST("Member/DoLogin")
    Observable<Dologin> regisbutton(@Body RequestBody route);

}
