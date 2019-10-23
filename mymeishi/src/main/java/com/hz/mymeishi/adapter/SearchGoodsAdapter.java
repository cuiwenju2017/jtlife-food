package com.hz.mymeishi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hz.mymeishi.R;
import com.hz.mymeishi.model.Serchbeangoods;

import java.util.ArrayList;


public class SearchGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Serchbeangoods.DataBean.ListBean> list;
    private Context mContext;

    public void setList(ArrayList<Serchbeangoods.DataBean.ListBean>list){
        this.list = list;
        notifyDataSetChanged();
    }
    public SearchGoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.layout_searchadaitem, null, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        Serchbeangoods.DataBean.ListBean listBean=list.get(position);
        if (viewHolder instanceof ViewHolder0) {
            Glide.with(mContext).load(listBean.getSinglePhoto()).into(((ViewHolder0) viewHolder).iv_shop_img);
            ((ViewHolder0) viewHolder).tv_collect_name.setText(listBean.getTitle());
            String getpricesrc=Double.toString(listBean.getPriceSrc());
            ((ViewHolder0)viewHolder).tv_collect_price.setText(getpricesrc);
            //原价
            String discountprice=Double.toString(listBean.getDiscountPrice());
            ((ViewHolder0) viewHolder).tv_yuanjia.setText(discountprice);
            //数量
            String num=Integer.toString(listBean.getSellNum());
            ((ViewHolder0) viewHolder).tv_shuliang.setText(num);
            Log.d("aassd",num);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private ImageView iv_shop_img;
        private TextView tv_collect_name;
        private TextView tv_collect_price;
        private TextView tv_yuanjia;
        private TextView tv_shuliang;


        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {


            iv_shop_img = (ImageView)itemView.findViewById(R.id.searchiv_shop_img);
            tv_collect_name =(TextView) itemView.findViewById(R.id.searchtv_collect_name);
            tv_collect_price = (TextView)itemView.findViewById(R.id.searchstv_collect_price);
            tv_yuanjia = (TextView)itemView.findViewById(R.id.searchttv_yuanjia);
            tv_shuliang =(TextView) itemView.findViewById(R.id.searchshuliang);
        }

    }
}
