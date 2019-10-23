package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.adapter.AfterxqAdapter;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.Aftershxqbean;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Aftersaledetails extends BaseActivity {

    private String shid;
    private RelativeLayout rl_back;
    private TextView tv_title;
    private ListView afterrecylist;
    private LoadingDialog loadingDialog;
    private List<Aftershxqbean.DataBean.OrderListBean> data1;
    private List<Aftershxqbean.DataBean>mdata1;
    private AfterxqAdapter afterxqAdapter;
    private View bottomView;
    private TextView after_order_no;
    private TextView orderStatus_name;
    private TextView orderStatus_info;
    private TextView createDate;
    private TextView expressTime;
    private CardView shcardView;
    private TextView Appointment;
    private TextView fillnumber;
    private TextView shname;
    private TextView shphone;
    private TextView shenshiadd;
    private TextView jutiaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftersaledetails);
        Intent intent = getIntent();
        shid = intent.getStringExtra("shid");
        initView();
        qingqiu();
    }



    private void initView() {
        bottomView = LayoutInflater.from(Aftersaledetails.this).inflate(R.layout.shouhouitemxx,null);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("售后详情");
        afterrecylist = (ListView)findViewById(R.id.afterrecylist);
        after_order_no = (TextView)bottomView.findViewById(R.id.after_order_no);
        orderStatus_name =(TextView) bottomView.findViewById(R.id.orderStatus_name);
        orderStatus_info =(TextView) bottomView.findViewById(R.id.orderStatus_info);
        createDate = (TextView)bottomView.findViewById(R.id.createDate);
        expressTime = (TextView)bottomView.findViewById(R.id.expressTime);
        shcardView =(CardView) bottomView.findViewById(R.id.shcardView);
        Appointment = (TextView)bottomView.findViewById(R.id.Appointment);
        fillnumber =(TextView) bottomView.findViewById(R.id.fillnumber);
        shname = (TextView)bottomView.findViewById(R.id.shname);
        shphone =(TextView) bottomView.findViewById(R.id.shphone);
        shenshiadd = (TextView)bottomView.findViewById(R.id.shenshiadd);
        jutiaddress = (TextView)bottomView.findViewById(R.id.jutiaddress);

    }

    private void qingqiu() {
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .aftershxq(shid)
                .compose(Transformer.<Aftershxqbean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Aftershxqbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(final Aftershxqbean data) {
                        loadingDialog.dismiss();
                        if(data.getData().getOrderStatus().equals("2")){
                            shcardView.setVisibility(View.VISIBLE);
                            shname.setText(data.getData().getName());
                            shphone.setText(data.getData().getPhone());
                            shenshiadd.setText(data.getData().getSysAreaName());
                            jutiaddress.setText(data.getData().getAddress());
                            Appointment.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent();
                                    intent.setClass(Aftersaledetails.this,AppointmentexActivity.class);
                                    intent.putExtra("yuyueid",data.getData().getId());
                                    intent.putExtra("dataid",shid);
                                    startActivity(intent);
                                }
                            });
                        }

                        if( data.getData().getReturnType().equals("0")){
                                 after_order_no.setText("退货退款");
                             }else if(data.getData().getReturnType().equals("1")){
                                 after_order_no.setText("换货");
                             }
                        orderStatus_name.setText(  data.getData().getOrderStatus_name());
                        orderStatus_info.setText(data.getData().getOrderStatus_info());
                        createDate.setText(data.getData().getCreateDate());
                        expressTime.setText(data.getData().getExpressTime() );
                        data1 = data.getData().getOrderList();
                         if(data1.size()!=0){
                             AfterxqAdapter afterxqAdapter=new AfterxqAdapter(Aftersaledetails.this,data1);
                             afterrecylist.addFooterView(bottomView);
                             afterrecylist.setAdapter(afterxqAdapter);
                         }else {
                             ToastUtils.showToast("暂无数据");
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

}
