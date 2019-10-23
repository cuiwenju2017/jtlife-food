package commeishi.shanjing.mymeishi.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.view.LoadingDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.View.DbMyDialog;
import commeishi.shanjing.mymeishi.activity.Aftersaledetails;
import commeishi.shanjing.mymeishi.activity.OrderCenterOrderMessage;
import commeishi.shanjing.mymeishi.activity.Returnsubmission;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.GetReturnOrderTypeList;
import commeishi.shanjing.mymeishi.model.Reasonsbean;
import commeishi.shanjing.mymeishi.model.Shouhoubean;
import commeishi.shanjing.mymeishi.model.Tuihuobean;
import commeishi.shanjing.mymeishi.model.Xqwupwc;


import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RefunAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Shouhoubean.DataBean.ListBean> list;
    private List<Shouhoubean.DataBean.ListBean.OrderListBean> mlist;
    private Context mContext;
    private LoadingDialog loadingDialog;
    private DbMyDialog sh_dialogxq;
    private List<GetReturnOrderTypeList.DataBean>typelist;
    private List<Xqwupwc.DataBean.OrderListBean>mmlist;
    private List<Xqwupwc.DataBean.OrderListBean>checklist;
    private DbMyDialog gettypexz;
    private ListView getxuz_list;
    private DbMyDialog shouhyuanyin;
    private ListView shoudialoglistview;
    private View bottomView;
    private TextView shyuanyin;
    private TextView dialog_shxq_xz;
    private RelativeLayout relayout_shyy;
    private String rdioorderid;
    private Button re_listview_button;
    private String rdigoodsId;
    private String rdiproduceId;
    private int rdinum;
    private int rdirenums;
    private String rdiicon;
    private String rdigoodsTitle;
    private String rdiproduceName;
    private double rdipriceSrc;
    private double rdidiscountBuy;
    private double rdipriceBuy;
    private String rdiid;


    public void setList( List<Shouhoubean.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public RefunAdapter(Context mContext) {
        this.mContext = mContext;
    }
    private void stchulartmessage(String id) {
        Intent intent = new Intent(mContext, Aftersaledetails.class);
        intent.putExtra("shid", id);
        mContext.startActivity(intent);
    }
    private void startOrderMessageActivity(String orderId) {
        Intent intent = new Intent(mContext, OrderCenterOrderMessage.class);
        intent.putExtra("orderID", orderId);
        mContext.startActivity(intent);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                return new ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.item_shouhou, null, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
                    final Shouhoubean.DataBean.ListBean listBean=list.get(position);
        if (viewHolder instanceof ViewHolder0) {
                Log.d("qqqq",listBean.getId());
            Glide.with(mContext).load(listBean.getShopLogo()).into(((ViewHolder0) viewHolder).img_icon);
            ((ViewHolder0) viewHolder).txt_aName.setText(listBean.getShopName());

            if(listBean.getOrderStatus().equals("4")){
                ((ViewHolder0) viewHolder).shbj.setText("退货订单");
                ((ViewHolder0) viewHolder).shbj.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tupiantianjia(listBean.getOrderId());
                        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_shouhouxd, null);
                        sh_dialogxq = new DbMyDialog(mContext, 0, 0, inflate, R.style.DialogTheme);
                        bottomView = LayoutInflater.from(mContext).inflate(R.layout.layout_sssfffsh,null);
                        shoudialoglistview = (ListView)inflate.findViewById(R.id.shoudialoglistview);
                        re_listview_button = (Button)inflate.findViewById(R.id.re_listview_button);
                        dialog_shxq_xz =(TextView) bottomView.findViewById(R.id.dialog_shxq_xz);
                        shyuanyin = (TextView)bottomView.findViewById(R.id.shyuanyin);
                        relayout_shyy = (RelativeLayout)bottomView.findViewById(R.id.relayout_shyy);
                        dialog_shxq_xz.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                sh_dialogxq.dismiss();
                                View view1 = LayoutInflater.from(mContext).inflate(R.layout.getxuanzeitem, null);
                                getxuz_list = (ListView)view1.findViewById(R.id.getxuz_list);
                                gettypexz = new DbMyDialog(mContext, 0, 0, view1, R.style.DialogTheme);
                                gettypexz.setCancelable(true);
                                gettypexz.show();
                                xuanzeqi();
                            }


                        });
                        shyuanyin.setOnClickListener(new View.OnClickListener() {

                            private TextView qita;
                            private TextView queshji;
                            private TextView miaoshu;
                            private TextView wuliyou;
                            private TextView sunhuai;
                            private TextView zhiliang;
                            private TextView maicuo;
                            @Override
                            public void onClick(View view) {
                                View view1 = LayoutInflater.from(mContext).inflate(R.layout.layout_shyuanyin, null);
                                 shouhyuanyin = new DbMyDialog(mContext, 0, 0, view1, R.style.DialogTheme);
                                 maicuo =(TextView) view1.findViewById(R.id.maicuo);
                                 zhiliang =(TextView) view1.findViewById(R.id.zhiliang);
                                 sunhuai =(TextView) view1.findViewById(R.id.sunhuai);
                                 wuliyou =(TextView) view1.findViewById(R.id.wuliyou);
                                 miaoshu =(TextView) view1.findViewById(R.id.miaoshu);
                                 queshji =(TextView) view1.findViewById(R.id.queshji);
                                 qita =(TextView) view1.findViewById(R.id.qita);
                                 shouhyuanyin.setCancelable(true);
                                 shouhyuanyin.show();
                                 shyuanylibiao(maicuo,zhiliang,sunhuai,wuliyou,miaoshu,queshji,qita);

                            }


                        });
                        re_listview_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tuihuo(listBean.getId(),listBean.getOrderId(),listBean.getShopId(),listBean.getOrderNo(),rdioorderid,rdigoodsId,rdiproduceId,rdinum,rdirenums,rdiicon,rdigoodsTitle,rdiproduceName,rdipriceSrc,rdidiscountBuy,rdipriceBuy,rdiid);

                            }
                        });
                        sh_dialogxq.setCancelable(true);
                        sh_dialogxq.show();

                    }
                });
                ((ViewHolder0) viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startOrderMessageActivity(listBean.getOrderId());
                    }

                });
            }else if(listBean.getOrderStatus().equals("5")){
                ((ViewHolder0) viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stchulartmessage(listBean.getId());
                    }

                });
            }
            else if(listBean.getOrderStatus().equals("6")){
                ((ViewHolder0) viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stchulartmessage(listBean.getId());
                    }

                });
            }


        }

    }



    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }




    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private ImageView img_icon;
        private TextView txt_aName;
        private TextView shouhouzkj;
        private TextView shouhou_yuanjia;
        private TextView shbj;
        private CardView cardView;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            img_icon =(ImageView) itemView.findViewById(R.id.shouhou_img_icon);
            txt_aName = (TextView) itemView.findViewById(R.id.shouhou_txt_aName);
            shouhouzkj = (TextView)itemView.findViewById(R.id.shouhouzkj);
            shouhou_yuanjia = (TextView)itemView.findViewById(R.id.shouhou_yuanjia);
            shbj =(TextView) itemView.findViewById(R.id.shbj);
        }
    }
    private void tuihuo(String id, String orderId, String shopId, String orderNo, String rdioorderid, String rdigoodsId, String rdiproduceId, int rdinum, int rdirenums, String rdiicon, String rdigoodsTitle, String rdiproduceName, double rdipriceSrc, double rdidiscountBuy, double rdipriceBuy, String rdiid) {
        loadingDialog = new LoadingDialog(mContext);
        Gson gson = new Gson();
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("orderId",orderId);
        paramsMap.put("shopId",shopId);
        paramsMap.put("orderNo",orderNo);
        paramsMap.put("returnArr",checklist);
        String info = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
        RxHttpUtils.createApi(GitHubService.class)
                .tuihuo(body)
                .compose(Transformer.<BaseData<Tuihuobean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Tuihuobean>() {

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Tuihuobean data) {
                        Log.d("dddwwww",data.getIsOK()+"");
//                        if(data.getId().equals("1")){
                            Intent intent=new Intent();
                            intent.setClass(mContext,Returnsubmission.class);

                            mContext.startActivity(intent);
//                        }else {
//                            Intent intent=new Intent();
//                            intent.setClass(mContext,Returnsubmission.class);
//                            mContext.startActivity(intent);
//                        }

                    }
                });
    }
    private void xuanzeqi() {
        loadingDialog = new LoadingDialog(mContext);
        RxHttpUtils.createApi(GitHubService.class)
                .typeList()
                .compose(Transformer.<GetReturnOrderTypeList>switchSchedulers(loadingDialog))
                .subscribe(new Observer<GetReturnOrderTypeList>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(GetReturnOrderTypeList getReturnOrderTypeList) {
                        Log.d("qqaaa",getReturnOrderTypeList.getStatus());
                        typelist= getReturnOrderTypeList.getData();
                        GetxzAdapter getxzAdapter=new GetxzAdapter(mContext,typelist);
                        getxuz_list.setAdapter(getxzAdapter);
                        getxzAdapter.setItemClickListener(new itemViewClickListener() {
                            @Override
                            public void onItemClickListener(int Position, List<GetReturnOrderTypeList.DataBean> typelist) {
                                dialog_shxq_xz.setText(typelist.get(Position).getValue());
                                gettypexz.dismiss();
                                relayout_shyy.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("qqaaa",e+"");
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void shyuanylibiao(final TextView maicuo, final TextView zhiliang, final TextView sunhuai, final TextView wuliyou, final TextView miaoshu, final TextView queshji, final TextView qita) {
        loadingDialog = new LoadingDialog(mContext);
        RxHttpUtils.createApi(GitHubService.class)
                .reaslist()
                .compose(Transformer.<Reasonsbean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Reasonsbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(final Reasonsbean reasonsbean) {
                        maicuo.setText(reasonsbean.getData().get(0));
                        maicuo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shyuanyin.setText(reasonsbean.getData().get(0));
                                shouhyuanyin.dismiss();
                            }
                        });
                        zhiliang.setText(reasonsbean.getData().get(1));
                        zhiliang.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shyuanyin.setText(reasonsbean.getData().get(1));
                                shouhyuanyin.dismiss();
                            }
                        });
                        sunhuai.setText(reasonsbean.getData().get(2));
                        sunhuai.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shyuanyin.setText(reasonsbean.getData().get(2));
                                shouhyuanyin.dismiss();
                            }
                        });
                        wuliyou.setText(reasonsbean.getData().get(3));
                        wuliyou.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shyuanyin.setText(reasonsbean.getData().get(3));
                                shouhyuanyin.dismiss();
                            }
                        });
                        miaoshu.setText(reasonsbean.getData().get(4));
                        miaoshu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shyuanyin.setText(reasonsbean.getData().get(4));
                                shouhyuanyin.dismiss();
                            }
                        });
                        queshji.setText(reasonsbean.getData().get(5));
                        queshji.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shyuanyin.setText(reasonsbean.getData().get(5));
                                shouhyuanyin.dismiss();
                            }
                        });
                         qita.setText(reasonsbean.getData().get(6));
                        qita.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shyuanyin.setText(reasonsbean.getData().get(6));
                                shouhyuanyin.dismiss();

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
    private void tupiantianjia(String orderId) {
        RxHttpUtils
                .createApi(GitHubService.class)
                .Orderdetails(orderId)
                .compose(Transformer.<Xqwupwc>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Xqwupwc>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Xqwupwc xqwupwc) {

                        mmlist=xqwupwc.getData().getOrderList();
                        DialogAdaptervv  dialogAdaptervv=new DialogAdaptervv(mContext,mmlist);
                        shoudialoglistview.addFooterView(bottomView);
                        shoudialoglistview.setAdapter(dialogAdaptervv);
                        dialogAdaptervv.setbuoontItemClickListener(new buttonViewClickListener() {

                            @Override
                            public void onbuttonItemClickListener(int Position, List<Xqwupwc.DataBean.OrderListBean> list) {
                                rdioorderid = list.get(Position).getOrderId();
                                rdigoodsId = list.get(Position).getGoodsId();
                                rdiproduceId = list.get(Position).getProduceId();
                                rdinum = list.get(Position).getNum();
                                rdirenums = list.get(Position).getRenums();
                                rdiicon = list.get(Position).getIcon();
                                rdigoodsTitle = list.get(Position).getGoodsTitle();
                                rdiproduceName = list.get(Position).getProduceName();
                                rdipriceSrc = list.get(Position).getPriceSrc();
                                rdidiscountBuy = list.get(Position).getDiscountBuy();
                                rdipriceBuy = list.get(Position).getPriceBuy();
                                rdiid = list.get(Position).getId();
                                int renums = list.get(Position).getRenums();
                                Log.d("wweerr",list.get(Position).getNum()+"++++"+list.get(Position).getRenums());
                                checklist=list;

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
    public interface itemViewClickListener{
        void onItemClickListener(int Position, List<GetReturnOrderTypeList.DataBean>typelist);
    }
    public interface buttonViewClickListener{
        void onbuttonItemClickListener(int Position, List<Xqwupwc.DataBean.OrderListBean>list);
    }

}
