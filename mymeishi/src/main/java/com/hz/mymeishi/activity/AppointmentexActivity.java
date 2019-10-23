 package com.hz.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;

import com.base.commonlib.interceptor.Transformer;

import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.hz.mymeishi.R;
import com.hz.mymeishi.View.CompatResourceUtils;
import com.hz.mymeishi.View.DateTimePicker;
import com.hz.mymeishi.View.DbMyDialog;
import com.hz.mymeishi.View.Orderlistchuzhi;
import com.hz.mymeishi.adapter.AppomentExAdapter;
import com.hz.mymeishi.adapter.WllistAdapter;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.model.Aftershxqbean;
import com.hz.mymeishi.model.AppoiExpressbean;
import com.hz.mymeishi.model.GetAddressbean;


import com.hz.mymeishi.model.Wlgslistbean;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

 public class AppointmentexActivity extends BaseActivity implements Orderlistchuzhi {
    private RelativeLayout rl_back;
    private TextView tv_title;
    private String yuyueid;
    private LoadingDialog loadingDialog;
     private List<Aftershxqbean.DataBean.OrderListBean> data1;
     private ListView appomentlist;
     private View bottomView;
     private TextView wlgonsilistxz;
     private ListView wlgshuoqulist;
     private DbMyDialog dialoggs;
     private  List<Wlgslistbean.DataBean>hslist;
     private TextView addredress;
     private DbMyDialog dialogaddress;
     private TextView morenaddress;
     private TextView endaddress;
     private TextView currentDate;
     private TextView currentTime;
     private DateTimePicker dateTimePicker1;
     private DateTimePicker dateTimePicker2;
     private Button mentext;
     private List<Aftershxqbean.DataBean.OrderListBean> chenlist;
     private String hashMapfgg;
     private int expressID;
     private String shname;
     private String shphone;
     private String shenshiadd;
     private String jutiaddress;
     private String ggghashMapfgg;
     private GetAddressbean.DataBean getdata;
     private String dataid;
     private TextView shouhuomorenaddress;
     private Aftershxqbean.DataBean aftershxqbeanData;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentex);
        Intent intent = getIntent();
         yuyueid = intent.getStringExtra("yuyueid");
         dataid = intent.getStringExtra("dataid");
        initView();
        initdate();
    }



    private void initView() {
        bottomView = LayoutInflater.from(AppointmentexActivity.this).inflate(R.layout.yuyueitemdd,null);
        appomentlist =(ListView) findViewById(R.id.appomentlist);
        mentext = (Button)findViewById(R.id.mentex_listview_button);
        mentext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sunbus();
            }
        });
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {  
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("预约订单");
        wlgonsilistxz =(TextView) bottomView.findViewById(R.id.wlgonsilistxz);
        addredress =(TextView) bottomView.findViewById(R.id.addredress);
        endaddress =(TextView) bottomView.findViewById(R.id.endaddress);
        currentDate = (TextView)bottomView.findViewById(R.id.tv_selected_date);
        currentTime = (TextView)bottomView.findViewById(R.id.tv_selected_time);
        initDatePicker();
        currentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateTimePicker1.show(date1);

            }
        });
        currentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateTimePicker2.show(date2);
            }
        });
        wlgonsilistxz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = LayoutInflater.from(AppointmentexActivity.this).inflate(R.layout.wlitemgongsihq, null);
                wlgshuoqulist = (ListView)view1.findViewById(R.id.wlgshuoqulist);
                dialoggs = new DbMyDialog(AppointmentexActivity.this, 0, 0, view1, R.style.DialogTheme);
                dialoggs.setCancelable(true);
                dialoggs.show();
                wlgshq();

            }
        });
        addredress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = LayoutInflater.from(AppointmentexActivity.this).inflate(R.layout.morenaddressitem, null);
                dialogaddress = new DbMyDialog(AppointmentexActivity.this, 0, 0, view1, R.style.DialogTheme);
                morenaddress = (TextView)view1.findViewById(R.id.morenaddress);
                dialogaddress.setCancelable(true);
                dialogaddress.show();
                getmorenaddress();
            }
        });
        endaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = LayoutInflater.from(AppointmentexActivity.this).inflate(R.layout.shouhuomorenitem, null);
                dialogaddress = new DbMyDialog(AppointmentexActivity.this, 0, 0, view1, R.style.DialogTheme);
                shouhuomorenaddress = (TextView)view1.findViewById(R.id.shouhuomorenaddress);
                dialogaddress.setCancelable(true);
                dialogaddress.show();
                enaddfangf();
            }
        });
    }




     private void initdate() {
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .aftershxq(yuyueid)
                .compose(Transformer.<Aftershxqbean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Aftershxqbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Aftershxqbean data) {
                        data1 = data.getData().getOrderList();
                        if(data1.size()!=0){
                            AppomentExAdapter appomentExAdapter=new AppomentExAdapter(AppointmentexActivity.this,data1,(Orderlistchuzhi)AppointmentexActivity.this);
                            appomentlist.addFooterView(bottomView);
                            appomentlist.setAdapter(appomentExAdapter);
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
     private void wlgshq() {
         loadingDialog = new LoadingDialog(this);
         RxHttpUtils.createApi(GitHubService.class)
                 .wlgshq()
                 .compose(Transformer.<Wlgslistbean>switchSchedulers(loadingDialog))
                 .subscribe(new Observer<Wlgslistbean>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                     }
                     @Override
                     public void onNext(Wlgslistbean wlgslistbean) {
                                hslist=wlgslistbean.getData();
                                WllistAdapter wllistAdapter=new WllistAdapter(AppointmentexActivity.this,hslist);
                                wlgshuoqulist.setAdapter(wllistAdapter);
                                Log.d("qqq2224444","++++++;");
                                wllistAdapter.setItemClickListener(new itemgsClickListener() {
                                    @Override
                                    public void ongsItemClickListener(int Position, List<Wlgslistbean.DataBean> typelist) {
                                        wlgonsilistxz.setText(typelist.get(Position).getEname());
                                        expressID = typelist.get(Position).getExpressID();
                                        Log.d("ddwwwq",wlgonsilistxz.getText().toString());
                                        dialoggs.dismiss();
                                    }
                                });

                     }
                     @Override
                     public void onError(Throwable e) {

                     }
                     @Override
                     public void onComplete() {

                     }
                 });

     }
     private void getmorenaddress() {
         loadingDialog = new LoadingDialog(this);
         RxHttpUtils.createApi(GitHubService.class)
                 .getaddress()
                 .compose(Transformer.<GetAddressbean>switchSchedulers(loadingDialog))
                 .subscribe(new Observer<GetAddressbean>() {

                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(final GetAddressbean getAddressbean) {
                         morenaddress.setText(getAddressbean.getData().getSysAreaName());
                         morenaddress.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 addredress.setText(getAddressbean.getData().getSysAreaName());
                                 getdata = getAddressbean.getData();
                             }
                         });
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });
     }
     SimpleDateFormat format1, format2;
     Date date1, date2;
     private void initDatePicker() {
         format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
         format2 = new SimpleDateFormat("yyyy-MM-dd  HH:mm", Locale.CHINA);
         Calendar calendar = Calendar.getInstance();
         Date startDate = calendar.getTime();
         date2 = date1 = calendar.getTime();
         currentDate.setText(format1.format(date1));
         currentTime.setText(format2.format(date1));

//        calendar.set(Calendar.YEAR, 2010);
         calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 8);
         calendar.set(Calendar.DAY_OF_MONTH, 1);
         calendar.set(Calendar.HOUR_OF_DAY, 0);
         calendar.set(Calendar.MINUTE, 0);
         Date endDate = calendar.getTime();

         DateTimePicker.Builder builder = new DateTimePicker.Builder(this)
                 .setTitle("选择年月日")
                 .setCancelTextColor(CompatResourceUtils.getColor(this, R.color.colorPrimary))
                 .setOkTextColor(CompatResourceUtils.getColor(this, R.color.colorPrimary))
                 .setTitleTextColor(0xFF999999)
                 .setSelectedTextColor(CompatResourceUtils.getColor(this, R.color.colorAccent))
                 .setKeepLastSelected(true)
                 .setShowYMDHMLabel(true)
                 .setShowType(DateTimePicker.ShowType.DAY);
         dateTimePicker1 = new DateTimePicker(this, new DateTimePicker.ResultHandler() {
             @Override
             public void handle(Date date) {
                 AppointmentexActivity.this.date1 = date;
                 currentDate.setText(format1.format(date));
             }
         }, startDate, endDate, builder);

         dateTimePicker2 = new DateTimePicker(this, new DateTimePicker.ResultHandler() {
             @Override
             public void handle(Date date) {
                 date2 = date;
                 currentTime.setText(format2.format(date));
             }
         }, startDate, endDate, new DateTimePicker.Builder(this).setLoopScroll(true));
     }

     @Override
     public void listid(List<Aftershxqbean.DataBean.OrderListBean> list, int position) {
         Log.d("ddsaa",list.get(position).getNum()+"");
         this.chenlist=list;
     }
     public interface itemgsClickListener{
         void ongsItemClickListener(int Position, List<Wlgslistbean.DataBean>typelist);
     }
     private void sunbus() {
         loadingDialog = new LoadingDialog(AppointmentexActivity.this);
         Gson gson = new Gson();
         HashMap<String, Object> paramsMap = new HashMap<>();
         paramsMap.put("orderList",chenlist);
         paramsMap.put("sendAddressList",getdata);
         paramsMap.put("reAddressList",aftershxqbeanData);
         paramsMap.put("expressID",expressID);
         String info = gson.toJson(paramsMap);
         Log.d("zaq",info);
         RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
         RxHttpUtils.createApi(GitHubService.class)
                 .yuyuekuadi(body)
                 .compose(Transformer.<AppoiExpressbean>switchSchedulers(loadingDialog))
                 .subscribe(new Observer<AppoiExpressbean>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                     }
                     @Override
                     public void onNext(AppoiExpressbean appoiExpressbean) {
                         Log.d("dwweeeqq",appoiExpressbean.getData().getEBusinessID());
                     }
                     @Override
                     public void onError(Throwable e) {
                            Log.d("dwweeeqq2",e+"");
                     }
                     @Override
                     public void onComplete() {
                     }
                 });
     }
     private void enaddfangf() {
         loadingDialog = new LoadingDialog(this);
         RxHttpUtils
                 .createApi(GitHubService.class)
                 .aftershxq(dataid)
                 .compose(Transformer.<Aftershxqbean>switchSchedulers(loadingDialog))
                 .subscribe(new Observer<Aftershxqbean>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                     }
                     @Override
                     public void onNext(final Aftershxqbean aftershxqbean) {
                         shouhuomorenaddress.setText(aftershxqbean.getData().getSysAreaName());
                         shouhuomorenaddress.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 endaddress.setText(aftershxqbean.getData().getSysAreaName());
                                 aftershxqbeanData = aftershxqbean.getData();
                             }
                         });
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
