package com.hz.mymeishi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;
import com.bumptech.glide.Glide;
import com.hz.mymeishi.R;

import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.model.Collscbean;
import com.hz.mymeishi.model.Deletecolle;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Collscbean.ListBean> list;
    private Context mContext;
    private LoadingDialog loadingDialog;
    private Collscbean.ListBean listbean;
    public void setList(ArrayList<Collscbean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public CollectAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public ArrayList<Collscbean.ListBean> getList() {
        return list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:
                return new CollectAdapter.ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.item_my_collect, null, false));
        }
      return  null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final Collscbean.ListBean listBean=list.get(position);
        this .listbean=listBean;
        if (viewHolder instanceof CollectAdapter.ViewHolder0) {
            Glide.with(mContext).load(listBean.getSinglePhoto()).into(((ViewHolder0) viewHolder).iv_shop_img);
            ((ViewHolder0) viewHolder).tv_collect_name.setText(listBean.getTitle());
            String getpricesrc=Double.toString(listBean.getPriceSrc());
            ((ViewHolder0) viewHolder).tv_collect_price.setText(getpricesrc);
            //原价
            String discountprice=Double.toString(listBean.getDiscountPrice());
            ((ViewHolder0) viewHolder).tv_yuanjia.setText(discountprice);

            //数量
            String num=Integer.toString(listBean.getSellNum());
            ((ViewHolder0) viewHolder).tv_shuliang.setText(num);
            ((ViewHolder0) viewHolder).iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.size()>0){
                        onDel(list.get(position).getGoodsId());
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
    private void onDel(String goodsId) {
        loadingDialog = new LoadingDialog(mContext);
        RxHttpUtils
                .createApi(GitHubService.class)
                .deletesc(goodsId)
                .compose(Transformer.<Deletecolle>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Deletecolle>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Deletecolle deletecolle) {
                                    if(deletecolle.getStatus().equals("1")){
                                        ToastUtils.showToast("取消收藏成功");
                                    }else {
                                        ToastUtils.showToast("取消失败");
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

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    private class ViewHolder0 extends RecyclerView.ViewHolder {
        private ImageView iv_shop_img;
        private TextView tv_collect_name;
        private TextView tv_collect_price;
        private ImageView iv_delete;
        private TextView tv_yuanjia;
        private TextView tv_shuliang;
        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }
        private void initView(View itemView) {
            iv_shop_img = (ImageView)itemView.findViewById(R.id.iv_shop_img);
            tv_collect_name =(TextView) itemView.findViewById(R.id.tv_collect_name);
            tv_collect_price = (TextView)itemView.findViewById(R.id.tv_collect_price);
            iv_delete = (ImageView)itemView.findViewById(R.id.iv_delete);
            tv_yuanjia =(TextView) itemView.findViewById(R.id.tv_yuanjia);
            tv_shuliang =(TextView) itemView.findViewById(R.id.shuliang);
        }
    }
}
