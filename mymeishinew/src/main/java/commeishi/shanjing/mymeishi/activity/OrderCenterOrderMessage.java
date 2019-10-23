package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.adapter.OrderdetailsAdapter;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.Xqwupwc;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderCenterOrderMessage extends BaseActivity {

    private TextView text;
    private String orderID;
    private TextView tv_title;
    private RelativeLayout rl_back;
    private LoadingDialog loadingDialog;
    private ListView orderxqlistview;
    private TextView title;
    private View bottomView;
    private TextView order_no;
    private TextView money_lgt;
    private TextView create_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_center_order_message);
        Intent intent = getIntent();
        orderID = intent.getStringExtra("orderID");//订单id
        initView();
        inData();
    }


    private void initView() {
        bottomView = LayoutInflater.from(OrderCenterOrderMessage.this).inflate(R.layout.item_ordergoods,null);
        order_no =(TextView) bottomView.findViewById(R.id.order_no);
        money_lgt =(TextView) bottomView.findViewById(R.id.money_lgt);
        create_date =(TextView) bottomView.findViewById(R.id.create_date);
        loadingDialog = new LoadingDialog(this);
        tv_title =(TextView) findViewById(R.id.tv_title);
        title = (TextView)findViewById(R.id.title);
        tv_title.setText("查看订单");
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        orderxqlistview = (ListView)findViewById(R.id.orderxqlistview);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void inData() {
        RxHttpUtils
                .createApi(GitHubService.class)
                .Orderdetailswc(orderID)
                .compose(Transformer.<Xqwupwc>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Xqwupwc>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Xqwupwc xqwupwc) {
                        title.setText(xqwupwc.getData().getShopName());
                        order_no.setText(xqwupwc.getData().getOrderNo());
                        String moyunfei=Double.toString(xqwupwc.getData().getMoneyLgt());
                        money_lgt.setText(moyunfei);
                        create_date.setText(xqwupwc.getData().getCreateDate());
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
                .Orderdetails(orderID)
                .compose(Transformer.<Xqwupwc>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Xqwupwc>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Xqwupwc xqwupwc) {
                        List<Xqwupwc.DataBean.OrderListBean> data1 = xqwupwc.getData().getOrderList();
                        OrderdetailsAdapter ordeadapter=new OrderdetailsAdapter(OrderCenterOrderMessage.this,data1);
                        orderxqlistview.addFooterView(bottomView);
                        orderxqlistview.setAdapter(ordeadapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        RxHttpUtils
//                .createApi(GitHubService.class)
//                .Orderdetails(orderID)
//                .compose(Transformer.<BaseData<Orderdetails>>switchSchedulers(loadingDialog))
//                . subscribe(new DataObserver<Orderdetails>(){
//                    @Override
//                    protected void onError(String errorMsg) {
//
//                    }
//                    @Override
//                    protected void onSuccess(Orderdetails data) {
//                        List<Orderdetails.OrderListBean> data1 = data.getOrderList();
//                        OrderdetailsAdapter ordeadapter=new OrderdetailsAdapter(OrderCenterOrderMessage.this,data1);
//                        orderxqlistview.addFooterView(bottomView);
//                        orderxqlistview.setAdapter(ordeadapter);
//                    }
//                });

    }

}
