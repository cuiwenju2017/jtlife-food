package commeishi.shanjing.mymeishi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.activity.GoodsDetaillsActivity;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.bean.AddShopBean;
import commeishi.shanjing.mymeishi.bean.HomeRightBean;


import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RighphotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private String shopid;
    private LoadingDialog loadingDialog;

    ArrayList<HomeRightBean.DataBean.ListBean> listbeanss;
    private ArrayList<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> list;
    public void setList(ArrayList<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public RighphotoAdapter(Context mContext, String publisherId) { 
        this.mContext = mContext;
        this.shopid=publisherId;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                return new ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.item_rv, null, false));

        }
        return null;
    }
    private void startOrderMessageActivity(String id, double discountPrice) {
        Intent intent = new Intent(mContext, GoodsDetaillsActivity.class);
//        Intent intent = new Intent(mContext, SHopdetailsActivity.class);
        intent.putExtra("goodsID", id);
        intent.putExtra("goodshopid",shopid);
        intent.putExtra("discountPrice",discountPrice+"");
        mContext.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final HomeRightBean.DataBean.ListBean.PgcGoodsListBean listBean=list.get(position);
        if (viewHolder instanceof ViewHolder0) {
            Glide.with(mContext).load(listBean.getSinglePhoto()).into(((ViewHolder0) viewHolder).image_iv);
            ((ViewHolder0) viewHolder).tv_goodsname.setText(listBean.getTitle());
            String price=Double.toString(listBean.getPriceSrc());
            ((ViewHolder0) viewHolder).tv_price.setText(price);

            String youhuijia=Double.toString(listBean.getDiscountPrice());
            ((ViewHolder0) viewHolder).tv_priceyouhui.setText(youhuijia);

            ((ViewHolder0) viewHolder).tv_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
            ((ViewHolder0) viewHolder).tv_price.getPaint().setAntiAlias(true);



            ((ViewHolder0) viewHolder).image_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startOrderMessageActivity(listBean.getId(),listBean.getDiscountPrice());
                }
            });
            ((ViewHolder0) viewHolder).iv_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getshop(listBean.getId(),shopid);
                }
            });
        }

    }

    private void getshop(String id, String shopid) {
        loadingDialog = new LoadingDialog(mContext);
        Gson gson = new Gson();
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("shop_id",shopid);//店铺ID
        paramsMap.put("collocation_id", "0");//来源搭配ID
        paramsMap.put("resource_type", "0");//来源类型(0、单品 1、搭配)
        paramsMap.put("goods_id",id);//商频ID
        paramsMap.put("produce_id","0" );//产品ID
        paramsMap.put("num", "1");//数量
        String info = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
        RxHttpUtils.createApi(GitHubService.class)
                .getDd(body)
                .compose(Transformer.<BaseData<AddShopBean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<AddShopBean>() {

                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.showShort(mContext, errorMsg);
                    }

                    @Override
                    protected void onSuccess(AddShopBean data) {

                        if ("1".equals(data.getIsAdd())) {
                            ToastUtil.showShort(mContext, "添加成功");




                        }
                    }


                });
    }



    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private ImageView image_iv;
        private TextView tv_goodsname;
        private TextView tv_price;
        private TextView tv_priceyouhui;
        private ImageView iv_shop;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            image_iv = (ImageView)itemView.findViewById(R.id.iv);
            tv_goodsname = (TextView)itemView.findViewById(R.id.tv);
            tv_price = (TextView)itemView.findViewById(R.id.tv_price);
            tv_priceyouhui = (TextView)itemView.findViewById(R.id.tv_priceyouhui);
            iv_shop = (ImageView)itemView.findViewById(R.id.iv_shop);


//            image_iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startOrderMessageActivity(list.get(getAdapterPosition()).getId());
////                    Log.d("dwwww",listbean.get)
//                }
//
//
//            });

        }
    }
}
