package com.hz.mymeishi.api;

import com.base.commonlib.bean.BaseData;
import com.hz.mymeishi.bean.AddShopBean;
import com.hz.mymeishi.bean.CommentNumBean;
import com.hz.mymeishi.bean.EditShopBean;
import com.hz.mymeishi.bean.HomeLeftBean;
import com.hz.mymeishi.bean.HomeRightBean;
import com.hz.mymeishi.bean.MessageBean;
import com.hz.mymeishi.bean.MessageNotificationBean;
import com.hz.mymeishi.bean.ShoppingCarDataBean;
import com.hz.mymeishi.bean.TheOrderDetailsBean;
import com.hz.mymeishi.bean.Wholebeandd;
import com.hz.mymeishi.model.Aftershxqbean;
import com.hz.mymeishi.model.AppoiExpressbean;
import com.hz.mymeishi.model.Buydiatelybean;
import com.hz.mymeishi.model.Collscbean;
import com.hz.mymeishi.model.Deletecolle;
import com.hz.mymeishi.model.Dologin;
import com.hz.mymeishi.model.GetAddressbean;
import com.hz.mymeishi.model.GetKayue;
import com.hz.mymeishi.model.GetReturnOrderTypeList;
import com.hz.mymeishi.model.Getordernum;
import com.hz.mymeishi.model.Goosmessges;
import com.hz.mymeishi.model.Homeyoublibu;
import com.hz.mymeishi.model.Putscbean;
import com.hz.mymeishi.model.Qxdingdanbean;
import com.hz.mymeishi.model.Reasonsbean;
import com.hz.mymeishi.model.Registbean;
import com.hz.mymeishi.model.Serchbeangoods;
import com.hz.mymeishi.model.Settlementbean;
import com.hz.mymeishi.model.Shopcollbean;
import com.hz.mymeishi.model.Shouhoubean;
import com.hz.mymeishi.model.Sppjlistbean;
import com.hz.mymeishi.model.Tjyhpinglun;
import com.hz.mymeishi.model.Touxiangbean;
import com.hz.mymeishi.model.Tuihuobean;
import com.hz.mymeishi.model.Wlgslistbean;
import com.hz.mymeishi.model.Xqwupwc;

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
    Observable<Shouhoubean> shouhou(@Path("page")int page,@Query("orderstatus")String orderstatus);
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
    Observable<Sppjlistbean> sppjlist(@Path("page")int page,@Query("goodsid")String goodsid);

   //添加用户地址
   @POST("MemberCenter/AddAddress")
   Observable<Tjyhpinglun> getaddress(@Body RequestBody route);
  //获取频道页分类商品列表
  @GET("Goods/GetPageList/{page}")
  Observable<Serchbeangoods> shouyeserch(@Path("page") int page, @Query("keywords") String keywords,@Query("maxprice")String maxprice);

  //用户获取验证码
    @GET("Member/registSmsCode")
    Observable<Registbean> regiszc(@Query("phone")String phone,@Query("platform")int platform  );
    //用户注册
    @POST("Member/DoLogin")
    Observable<Dologin> regisbutton(@Body RequestBody route);

}
