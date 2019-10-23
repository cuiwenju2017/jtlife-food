package com.hz.mymeishi.adapter;

import android.content.Context;
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
import com.hz.mymeishi.R;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.bean.AddShopBean;
import com.hz.mymeishi.bean.HomeRightBean;

import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> pgcGoodsListBeans;
    private Context context;
    private LoadingDialog loadingDialog;

    public GalleryAdapter(Context context, List<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> itemData) {
        mInflater = LayoutInflater.from(context);
        this.pgcGoodsListBeans = itemData;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView iv, iv_add_to_shop;
        TextView tv, tv_price;
    }

    @Override
    public int getItemCount() {
        return pgcGoodsListBeans.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = mInflater.inflate(R.layout.item_rv,
                null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv = view.findViewById(R.id.tv);
        viewHolder.iv = view.findViewById(R.id.iv);
        viewHolder.iv_add_to_shop = view.findViewById(R.id.iv_add_to_shop);
        /**
         * 添加到购物车
         */
        viewHolder.iv_add_to_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("shop_id", "1059396544446468096");//店铺ID
                paramsMap.put("collocation_id", "0");//来源搭配ID
                paramsMap.put("resource_type", "0");//来源类型(0、单品 1、搭配)
                paramsMap.put("goods_id", pgcGoodsListBeans.get(i).getId());//商家ID
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
                                ToastUtil.showShort(context, errorMsg);
                            }

                            @Override
                            protected void onSuccess(AddShopBean data) {
                                if ("1".equals(data.getIsAdd())) {
                                    ToastUtil.showShort(context, "添加成功");
                                }
                            }
                        });
            }
        });
        viewHolder.tv_price = view.findViewById(R.id.tv_price);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glide.with(context).load(pgcGoodsListBeans.get(i).getSinglePhoto()).into(viewHolder.iv);
        viewHolder.tv.setText(pgcGoodsListBeans.get(i).getTitle());
        viewHolder.tv_price.setText("￥  " + pgcGoodsListBeans.get(i).getPriceSrc());
    }
}