package com.hz.mymeishi.adapter;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.hz.mymeishi.R;
import com.hz.mymeishi.model.Xqwupwc;

import java.util.List;

public class OrderdetailsAdapter extends BaseAdapter {
    private Context context;
    private List<Xqwupwc.DataBean.OrderListBean> list;
    private List<Xqwupwc.DataBean> mlist;

    public OrderdetailsAdapter(Context context, List<Xqwupwc.DataBean.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder holder;
        if(convertView == null){
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_whole_orderdetails, viewGroup, false);
            holder.xqicon = (ImageView)convertView.findViewById(R.id.iv_fm);
            holder.xqtitle = (TextView) convertView.findViewById(R.id.title);
            holder.xqtv_num =(TextView) convertView.findViewById(R.id.tv_num);
            holder.xqgoumaiyuanjia = (TextView)convertView.findViewById(R.id.goumaiyuanjia);
            holder.xqdiscount_buy = (TextView)convertView.findViewById(R.id.discount_buy);
            holder.xqprice_buy= (TextView)convertView.findViewById(R.id.price_buy);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Xqwupwc.DataBean.OrderListBean listBean=list.get(position);

        Glide.with(context).load(listBean.getIcon()).into(holder.xqicon);
        holder.xqtitle.setText(listBean.getGoodsTitle());
        String num=Integer.toString(listBean.getNum());
        holder.xqtv_num.setText(num);
        String price=Double.toString(listBean.getPriceSrc());
        holder.xqgoumaiyuanjia.setText(price);
        String discount=Double.toString(listBean.getDiscountBuy());
        holder.xqdiscount_buy.setText(discount);
        String pricebuy=Double.toString(listBean.getPriceBuy());
        holder.xqprice_buy.setText(pricebuy);
//        if(mlist.get(position).getOrderStatus().equals("4")){
//
//            holder.wanc_lin_sp.setVisibility(View.VISIBLE);
//        }
//        Log.d("qqqqqq",mlist.get(position).getOrderStatus());

        return convertView;
    }
    public static class MyViewHolder {
            private ImageView xqicon;
            private TextView xqtitle;
            private TextView xqtv_num;
            private TextView xqgoumaiyuanjia;
            private TextView xqdiscount_buy;
            private TextView xqprice_buy;

    }
}
