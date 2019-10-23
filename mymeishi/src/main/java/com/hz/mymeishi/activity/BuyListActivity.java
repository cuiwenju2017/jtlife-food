package com.hz.mymeishi.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.hz.mymeishi.R;
import com.hz.mymeishi.View.PickValueView;
import com.hz.mymeishi.adapter.ShopAdapter;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.bean.ShoppingCarDataBean;
import com.hz.mymeishi.model.Settlementbean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 购买清单
 */
public class BuyListActivity extends BaseActivity implements View.OnClickListener,PickValueView.onSelectedChangeListener{


    private List<ShoppingCarDataBean.DataBean.CartListBean> shoppbean;
    private ListView listviewshopp;
    private View bottomView;
    private String totalnum;
    private TextView texttotalnum;
    private TextView totalprice;
    private String zongjia;
    private RelativeLayout rl_cj_num;
    private Dialog dialog;
    private TextView tv_cancel;
    private PickValueView pickString;
    private TextView tv_cj_num;
    private RelativeLayout rl_zffs;
    private TextView tv_zffs;
    private View inflate;
    private TextView tv_zfb;
    private TextView tv_wx;
    private TextView tv_my_money;
    private TextView hejijiage;
    private Button submentbutton;
    private LoadingDialog loadingDialog;
    private String cartid="0";
    private int zftype=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

            Intent intent=getIntent();

        shoppbean=(List<ShoppingCarDataBean.DataBean.CartListBean>)getIntent().getSerializableExtra("goodsbean");
        totalnum = intent.getStringExtra("totalnum");
        zongjia = intent.getStringExtra("totalprice");
        for (int i = 0; i <shoppbean.size(); i++) {
              cartid+=shoppbean.get(i).getId()+",";

        }
        if(cartid!=null && cartid.length()>1){
            cartid = cartid.substring(1,cartid.length() -1);
        }


        initView();
        initData();
    }

    private void initData() {
//        tv_title.setText("购买清单");

    }

    private void initView() {
        bottomView = LayoutInflater.from(BuyListActivity.this).inflate(R.layout.buylistactivity_item,null);
        texttotalnum = (TextView)bottomView.findViewById(R.id.totalnum);
        totalprice =(TextView) bottomView.findViewById(R.id.totalprice);
        texttotalnum.setText("共"+totalnum+"件商品,");
        totalprice.setText("合计:￥"+zongjia);
        listviewshopp =(ListView) findViewById(R.id.listviewshopp);

        tv_cj_num = (TextView)bottomView.findViewById(R.id.tv_cj_num);
        rl_cj_num = (RelativeLayout)bottomView.findViewById(R.id.rl_cj_num);

        rl_zffs = (RelativeLayout)bottomView.findViewById(R.id.rl_zffs);
        tv_zffs = (TextView)bottomView.findViewById(R.id.tv_zffs);

        hejijiage =(TextView) bottomView.findViewById(R.id.hejijiage);
        hejijiage.setText("总价:￥"+zongjia);
        submentbutton = (Button)bottomView.findViewById(R.id.submentbutton);
        ShopAdapter shopAdapter = new ShopAdapter(shoppbean, this);
        listviewshopp.addFooterView(bottomView);
        listviewshopp.setAdapter(shopAdapter);

        submentbutton.setOnClickListener(this);
        rl_zffs.setOnClickListener(this);
        rl_cj_num.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.rl_cj_num) {//选择对话框
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_cj_num, null);
            //获取组件
            tv_cancel = (TextView)contentView.findViewById(R.id.tv_cancel);
            pickString =(PickValueView) contentView.findViewById(R.id.pickString);
            //获取Dialog的监听
            tv_cancel.setOnClickListener(this);
            pickString.setOnSelectedChangeListener(this);
            String[] valueStr = new String[]{"无需餐具", "1人", "2人", "3人", "4人", "5人",
                    "6人", "7人", "8人"};
            pickString.setValueData(valueStr, valueStr[1]);
            dialog.setContentView(contentView);
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = getResources().getDisplayMetrics().widthPixels;
            contentView.setLayoutParams(layoutParams);
            dialog.getWindow().setGravity(Gravity.BOTTOM);//弹窗位置
            dialog.getWindow().setWindowAnimations(R.style.ActionSheetDialogStyle);//弹窗样式
            dialog.show();//显示弹窗
        }
        else if (i == R.id.rl_zffs) {
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(this).inflate(R.layout.dialog_zffs, null);
            //获取控件
            tv_zfb =(TextView) inflate.findViewById(R.id.tv_zfb);
            tv_wx =(TextView) inflate.findViewById(R.id.tv_wx);
            tv_my_money = (TextView)inflate.findViewById(R.id.tv_my_money);
            tv_cancel = (TextView)inflate.findViewById(R.id.tv_cancel);
            //获取监听
            tv_cancel.setOnClickListener(this);
            tv_zfb.setOnClickListener(this);
            tv_wx.setOnClickListener(this);
            tv_my_money.setOnClickListener(this);
            //将布局设置给Dialog
            dialog.setContentView(inflate);
            //获取当前Activity所在的窗体
            Window dialogWindow = dialog.getWindow();
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.y = 0;//设置Dialog距离底部的距离
            //宽度填满
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
            dialog.show();//显示对话框
        }
        else if (i == R.id.tv_zfb) {
            zftype = 1;
            tv_zffs.setText("支付宝");
            dialog.dismiss();
        } else if (i == R.id.tv_wx) {
            zftype= 0;
            tv_zffs.setText("微信");
            dialog.dismiss();
        } else if (i == R.id.tv_my_money) {
            zftype=2;
            tv_zffs.setText("我的钱包");
            dialog.dismiss();
        } else if (i == R.id.tv_cancel) {
            dialog.dismiss();
        }else if(i==R.id.submentbutton){
            tijiao();
        }
    }
    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        String selectedStr = (String) leftValue;
        tv_cj_num.setText(selectedStr);
    }
    private void tijiao() {
        loadingDialog = new LoadingDialog(BuyListActivity.this);
        Gson gson = new Gson();
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("cart_id",cartid);
        paramsMap.put("pay_type",zftype+"");

        paramsMap.put("address_id","1130755442851581952");
        paramsMap.put("ecv_id","");
        paramsMap.put("coupon_id","");
        paramsMap.put("coupon_fee","");
        paramsMap.put("user_note","");
        String info = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
        RxHttpUtils.createApi(GitHubService.class)
                .jiesuan(body)
                .compose(Transformer.<Settlementbean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Settlementbean>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("ffg2",d+"");
                    }

                    @Override
                    public void onNext(Settlementbean settlementbean) {
                            Log.d("ffg",settlementbean.getStatus());
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
