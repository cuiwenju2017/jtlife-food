package commeishi.shanjing.mymeishi.fragment;


import android.annotation.SuppressLint;

import android.os.Bundle;



import android.util.Log;

import android.view.View;

import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.RxHttpUtils;

import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.View.Payclass;

import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.bean.AddShopBean;
import commeishi.shanjing.mymeishi.bean.CommentNumBean;
import commeishi.shanjing.mymeishi.model.Buydiatelybean;
import commeishi.shanjing.mymeishi.model.Deletecolle;
import commeishi.shanjing.mymeishi.model.Goosmessges;
import commeishi.shanjing.mymeishi.model.Putscbean;

import commeishi.shanjing.mymeishi.model.Shopcollbean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;




@SuppressLint("ValidFragment")
public class Goosxiangqsp extends BaseFragment implements View.OnClickListener{



    private ImageView convenientBanner_detail;
    private LoadingDialog loadingDialog;
    private final String xxmid;
    private TextView goodstitle;
    private TextView zhekoujia;
    private TextView yuanjia;
    private ImageView img_colle;
    private TextView text_colle;
    private Boolean ischeck=false;
    private Button details_btn_jion_cart;
    private SmartRefreshLayout mRefresh;
    private String goodshopid;
    private Button details_btn_buy;
    private String discountPrice;
    private TextView detail_txt_pjrs;
    private WebView details_wv_picture_text;
    private static final String WEBVIEW_CONTENT_NIGHT = "<html><head></head><body style=\"text-indent:0em;\">%s</body></html>";


    public Goosxiangqsp(String goodsid, String goodshopid, String discountPrice) {
        Log.d("ddsadd",goodshopid);
        this.xxmid = goodsid;
        this.goodshopid=goodshopid;
        this.discountPrice=discountPrice;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_goosxiangqsp;
    }
    @Override
    protected void init(View view, Bundle savedInstanceState) {
                initView(view);
                initDate();
    }

    private void initDate() {
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                fetchData();
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
            }
        });


    }

    private void initView(View view) {
        loadingDialog = new LoadingDialog(getActivity());
        mRefresh = view.findViewById(R.id.refresh);
        convenientBanner_detail =(ImageView) view.findViewById(R.id.convenientBanner_detail);
        goodstitle = (TextView)view.findViewById(R.id.goodstitle);
        zhekoujia = (TextView)view.findViewById(R.id.zhekoujia);
        yuanjia = (TextView)view.findViewById(R.id.yuanjia);
        img_colle =(ImageView) view.findViewById(R.id.img_colle);
        text_colle =(TextView) view.findViewById(R.id.text_colle);
        //加入购物车
        details_btn_jion_cart =(Button) view.findViewById(R.id.details_btn_jion_cart);
        img_colle.setOnClickListener(this);
        details_btn_jion_cart.setOnClickListener(this);
        details_btn_buy = (Button)view.findViewById(R.id.details_btn_buy);
        details_btn_buy.setOnClickListener(this);

        detail_txt_pjrs =(TextView) view.findViewById(R.id.detail_txt_pjrs);
        details_wv_picture_text = (WebView)view.findViewById(R.id.details_wv_picture_text);


    }

    @Override
    public void fetchData() {
        loadingDialog = new LoadingDialog(getActivity());
        Log.d("ddsad",xxmid);
        RxHttpUtils
                .createApi(GitHubService.class)
                .goodsmessage(xxmid)
                .compose(Transformer.<Goosmessges>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Goosmessges>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext( final Goosmessges goosmessges) {
                        loadingDialog.dismiss();
                        goodstitle.setText(goosmessges.getData().getTitle());
                        if(!goosmessges.getData().getIsSpecsUsable().equals("0")){
                            String pricesrc=Double.toString(goosmessges.getData().getPriceSrc());
                            yuanjia.setText(pricesrc);
                            String discountprice=Double.toString(goosmessges.getData().getDiscountPrice());
                            zhekoujia.setText(discountprice);
                        }
                        Glide.with(getActivity()).load(goosmessges.getData().getIcon()).into(convenientBanner_detail);
//                        final ArrayList<String> list = new ArrayList<>();
//                        List<Goosmessges.DataBean.GoodsResourcesBean>dataBeans=goosmessges.getData().getGoodsResources();
//                        for (int i = 0; i <dataBeans.size() ; i++) {
//                            list.add(dataBeans.get(i).getUrl());
//                        }
//                        convenientBanner_detail.setImageLoader(new Bannerimage());
//                        convenientBanner_detail.setImages(list);
//                        convenientBanner_detail.setDelayTime(3000);
//                        convenientBanner_detail.start();

                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
        RxHttpUtils
                .createApi(GitHubService.class)
                .dianpu(xxmid,0)
                .compose(Transformer.<BaseData<Shopcollbean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Shopcollbean>() {

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Shopcollbean data) {
                        loadingDialog.dismiss();
                        Log.d("aaaa",data.getIsFav());
                        if(data.getIsFav().equals("1")){
                            img_colle.setImageResource(R.drawable.ic_collect_active);
                        }

                    }
                });

        RxHttpUtils
                .createApi(GitHubService.class)
                .getCommentNum(xxmid)
                .compose(Transformer.<BaseData<CommentNumBean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<CommentNumBean>() {

                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.showShort(getActivity(), errorMsg);
                    }

                    @Override
                    protected void onSuccess(CommentNumBean data) {
                        detail_txt_pjrs.setText("评价(" + data.getJudgeNum() + ")");



                    }
                });
        RxHttpUtils
                .createApi(GitHubService.class)
                .goodsmessage(xxmid)
                .compose(Transformer.<Goosmessges>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Goosmessges>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Goosmessges goosmessges) {
                        String content = goosmessges.getData().getContent();
                        details_wv_picture_text.loadDataWithBaseURL(null, String.format(WEBVIEW_CONTENT_NIGHT, content), "text/html", "utf-8", null);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.img_colle) {
            loadingDialog = new LoadingDialog(getActivity());
            if (ischeck == false) {
                Gson gson = new Gson();
                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("resouce_id", xxmid);
                paramsMap.put("type", "0");
                String info = gson.toJson(paramsMap);
                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                Log.d("dsad", info);
                RxHttpUtils.createApi(GitHubService.class)
                        .putcshoucang(body)
                        .compose(Transformer.<BaseData<Putscbean>>switchSchedulers(loadingDialog))
                        .subscribe(new DataObserver<Putscbean>() {

                            @Override
                            protected void onError(String errorMsg) {

                            }

                            @Override
                            protected void onSuccess(Putscbean data) {
                                loadingDialog.dismiss();
                                if (data.getIsAdd().equals("1")) {
                                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                                    img_colle.setImageResource(R.drawable.ic_collect_active);
                                    ischeck = true;
                                    Log.d("dddww", ischeck + "");
                                }

                            }
                        });
            } else if (ischeck == true) {
                loadingDialog = new LoadingDialog(getActivity());
                RxHttpUtils
                        .createApi(GitHubService.class)
                        .deletesc(xxmid)
                        .compose(Transformer.<Deletecolle>switchSchedulers(loadingDialog))
                        .subscribe(new Observer<Deletecolle>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(Deletecolle deletecolle) {
                                loadingDialog.dismiss();
                                if (deletecolle.getStatus().equals("1")) {
                                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();

                                    ischeck = false;
                                    img_colle.setImageResource(R.drawable.ic_collect);
                                } else {
                                    Toast.makeText(getActivity(), "取消失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }


        } else if (i == R.id.details_btn_jion_cart) {
            loadingDialog = new LoadingDialog(getActivity());
            Gson gson = new Gson();
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("shop_id", goodshopid);//店铺ID
            paramsMap.put("collocation_id", "0");//来源搭配ID
            paramsMap.put("resource_type", "0");//来源类型(0、单品 1、搭配)
            paramsMap.put("goods_id", xxmid);//商频ID
            paramsMap.put("produce_id", "0");//产品ID
            paramsMap.put("num", "1");//数量
            String info = gson.toJson(paramsMap);
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
            RxHttpUtils.createApi(GitHubService.class)
                    .getDd(body)
                    .compose(Transformer.<BaseData<AddShopBean>>switchSchedulers(loadingDialog))
                    .subscribe(new DataObserver<AddShopBean>() {

                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.showShort(getActivity(), errorMsg);
                        }

                        @Override
                        protected void onSuccess(AddShopBean data) {

                            if ("1".equals(data.getIsAdd())) {
                                ToastUtil.showShort(getActivity(), "添加成功");
                            }
                        }
                    });

        } else if (i == R.id.details_btn_buy) {
            loadingDialog = new LoadingDialog(getActivity());
            Gson gsonbuy = new Gson();
            HashMap<String, String> paramsMapbuy = new HashMap<>();
            paramsMapbuy.put("shop_id", goodshopid);
            paramsMapbuy.put("goods_id", xxmid);
            paramsMapbuy.put("produce_id", "0");
            paramsMapbuy.put("nums", "1");
            paramsMapbuy.put("fee", discountPrice);
            paramsMapbuy.put("pay_type", "0");
            paramsMapbuy.put("address_id", "1130755442851581952");
            paramsMapbuy.put("trans_fee", "");
            paramsMapbuy.put("ecv_id", "");
            paramsMapbuy.put("coupon_id", "");
            paramsMapbuy.put("coupon_fee", "");
            paramsMapbuy.put("user_note", "");
            String infobuy = gsonbuy.toJson(paramsMapbuy);
            RequestBody bodybuy = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), infobuy);
            RxHttpUtils.createApi(GitHubService.class)
                    .Buyimmediately(bodybuy)
                    .compose(Transformer.<Buydiatelybean>switchSchedulers(loadingDialog))
                    .subscribe(new Observer<Buydiatelybean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Buydiatelybean buydiatelybean) {
                            Payclass.wechatpay(buydiatelybean, getActivity());

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
    }
}
