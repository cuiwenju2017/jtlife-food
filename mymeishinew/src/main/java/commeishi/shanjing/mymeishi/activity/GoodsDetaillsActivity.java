package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
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
import commeishi.shanjing.mymeishi.adapter.SpjugelistAdapter;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.bean.AddShopBean;
import commeishi.shanjing.mymeishi.bean.CommentNumBean;
import commeishi.shanjing.mymeishi.model.Buydiatelybean;
import commeishi.shanjing.mymeishi.model.Deletecolle;
import commeishi.shanjing.mymeishi.model.Goosmessges;
import commeishi.shanjing.mymeishi.model.Putscbean;
import commeishi.shanjing.mymeishi.model.Shopcollbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GoodsDetaillsActivity extends BaseActivity implements View.OnClickListener{
    private String goodsid;

    private int mid;
    private TextView tv_title;
    private RelativeLayout rl_back;
    private String goodshopid;
    private String discountPrice;
    private ImageView convenientBanner_detail;
    private LoadingDialog loadingDialog;

    private TextView goodstitle;
    private TextView zhekoujia;
    private TextView yuanjia;
    private ImageView img_colle;
    private TextView text_colle;
    private Boolean ischeck=false;
    private Button details_btn_jion_cart;
    private SmartRefreshLayout mRefresh;

    private Button details_btn_buy;

    private TextView detail_txt_pjrs;
    private WebView details_wv_picture_text;
    private static final String WEBVIEW_CONTENT_NIGHT = "<html><head></head><body style=\"text-indent:0em;\">%s</body></html>";
    private RelativeLayout details_rl_pingjia_goods;
    private ListView spjudgeList;
    private List<Goosmessges.DataBean.JudgeListBean>date1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detaills);
        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsID");//商品id
        goodshopid = intent.getStringExtra("goodshopid");
        discountPrice = intent.getStringExtra("discountPrice");

        Log.d("dwwaaa",goodshopid);
        initView();
        initDate();
        initDiss();
    }




    private void initView() {
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title =(TextView) findViewById(R.id.tv_title);
        tv_title.setText("商品详情");
        loadingDialog = new LoadingDialog(this);
        mRefresh = findViewById(R.id.refresh);
        convenientBanner_detail =(ImageView) findViewById(R.id.convenientBanner_detail);
        goodstitle = (TextView)findViewById(R.id.goodstitle);
        zhekoujia = (TextView)findViewById(R.id.zhekoujia);
        yuanjia = (TextView)findViewById(R.id.yuanjia);
        img_colle =(ImageView)findViewById(R.id.img_colle);
        text_colle =(TextView) findViewById(R.id.text_colle);
        //加入购物车
        details_btn_jion_cart =(Button) findViewById(R.id.details_btn_jion_cart);
        img_colle.setOnClickListener(this);
        details_btn_jion_cart.setOnClickListener(this);
        details_btn_buy = (Button)findViewById(R.id.details_btn_buy);
        details_btn_buy.setOnClickListener(this);

        detail_txt_pjrs =(TextView) findViewById(R.id.detail_txt_pjrs);
        details_wv_picture_text = (WebView)findViewById(R.id.details_wv_picture_text);
        details_rl_pingjia_goods =(RelativeLayout) findViewById(R.id.details_rl_pingjia_goods);
        details_rl_pingjia_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spzj = detail_txt_pjrs.getText().toString();
                Intent intent=new Intent();
                intent.setClass(GoodsDetaillsActivity.this,SppjlistActivity.class);
                intent.putExtra("goddsspid",goodsid);
                intent.putExtra("spzj",spzj);
                startActivity(intent);
            }
        });

        spjudgeList = (ListView)findViewById(R.id.spjudgeList);


    }
    private void initDate() {
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initDiss();
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
            }
        });

    }
    private void initDiss() {
        loadingDialog = new LoadingDialog(this);

        RxHttpUtils
                .createApi(GitHubService.class)
                .goodsmessage(goodsid)
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
                        Glide.with(GoodsDetaillsActivity.this).load(goosmessges.getData().getIcon()).into(convenientBanner_detail);
                        date1=goosmessges.getData().getJudgeList();
                        SpjugelistAdapter spjugelistAdapter=new SpjugelistAdapter(GoodsDetaillsActivity.this,date1);
                        spjudgeList.setAdapter(spjugelistAdapter);
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
                .dianpu(goodsid,0)
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
                .getCommentNum(goodsid)
                .compose(Transformer.<BaseData<CommentNumBean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<CommentNumBean>() {

                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.showShort(GoodsDetaillsActivity.this, errorMsg);
                    }

                    @Override
                    protected void onSuccess(CommentNumBean data) {
                        detail_txt_pjrs.setText("评价(" + data.getJudgeNum() + ")");



                    }
                });
        RxHttpUtils
                .createApi(GitHubService.class)
                .goodsmessage(goodsid)
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
            loadingDialog = new LoadingDialog(this);
            if (ischeck == false) {
                Gson gson = new Gson();
                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("resouce_id", goodsid);
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
                                    Toast.makeText(GoodsDetaillsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                    img_colle.setImageResource(R.drawable.ic_collect_active);
                                    ischeck = true;
                                    Log.d("dddww", ischeck + "");
                                }

                            }
                        });
            } else if (ischeck == true) {
                loadingDialog = new LoadingDialog(this);
                RxHttpUtils
                        .createApi(GitHubService.class)
                        .deletesc(goodsid)
                        .compose(Transformer.<Deletecolle>switchSchedulers(loadingDialog))
                        .subscribe(new Observer<Deletecolle>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(Deletecolle deletecolle) {
                                loadingDialog.dismiss();
                                if (deletecolle.getStatus().equals("1")) {
                                    Toast.makeText(GoodsDetaillsActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();

                                    ischeck = false;
                                    img_colle.setImageResource(R.drawable.ic_collect);
                                } else {
                                    Toast.makeText(GoodsDetaillsActivity.this, "取消失败", Toast.LENGTH_SHORT).show();
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
            loadingDialog = new LoadingDialog(this);
            Gson gson = new Gson();
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("shop_id", goodshopid);//店铺ID
            paramsMap.put("collocation_id", "0");//来源搭配ID
            paramsMap.put("resource_type", "0");//来源类型(0、单品 1、搭配)
            paramsMap.put("goods_id", goodsid);//商频ID
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
                            ToastUtil.showShort(GoodsDetaillsActivity.this, errorMsg);
                        }
                        @Override
                        protected void onSuccess(AddShopBean data) {

                            if ("1".equals(data.getIsAdd())) {
                                ToastUtil.showShort(GoodsDetaillsActivity.this, "添加成功");
                            }
                        }
                    });

        } else if (i == R.id.details_btn_buy) {
            loadingDialog = new LoadingDialog(this);
            Gson gsonbuy = new Gson();
            HashMap<String, String> paramsMapbuy = new HashMap<>();
            paramsMapbuy.put("shop_id", goodshopid);
            paramsMapbuy.put("goods_id", goodsid);
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
                            Payclass.wechatpay(buydiatelybean, GoodsDetaillsActivity.this);

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }else if(i == R.id.convenientBanner_detail){

        }
    }


}
