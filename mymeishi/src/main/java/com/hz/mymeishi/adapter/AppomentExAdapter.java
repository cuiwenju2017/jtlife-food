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
import com.hz.mymeishi.View.Orderlistchuzhi;
import com.hz.mymeishi.model.Aftershxqbean;

import java.util.List;

public class AppomentExAdapter extends BaseAdapter {

    private Context context;
    private List<Aftershxqbean.DataBean.OrderListBean> list;
    private Orderlistchuzhi orderlistchuzhi;

    public AppomentExAdapter(Context context, List<Aftershxqbean.DataBean.OrderListBean> list,Orderlistchuzhi orderlistchuzhi) {
        this.context = context;
        this.list = list;
        this.orderlistchuzhi=orderlistchuzhi;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.yuyuradaitem, viewGroup, false);
            holder.after_iv_fm = (ImageView)convertView.findViewById(R.id.after_iv_fm);
            holder.aftertitle = (TextView)convertView.findViewById(R.id.aftertitle);
            holder.aftertv_price = (TextView)convertView.findViewById(R.id.aftertv_price);


            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Aftershxqbean.DataBean.OrderListBean listBean=list.get(position);
        Glide.with(context).load(listBean.getIcon()).into(holder.after_iv_fm);
        holder.aftertitle.setText(listBean.getGoodsTitle());
        String pricebuy=Double.toString(listBean.getPriceBuy());
        holder.aftertv_price.setText("￥"+pricebuy);
        click( list,position);

        return convertView;
    }



    public static class MyViewHolder {

        private ImageView after_iv_fm;
        private TextView aftertitle;
        private TextView aftertv_price;
        private TextView faternum;

    }
    private void click(List<Aftershxqbean.DataBean.OrderListBean> list, int position) {
        orderlistchuzhi.listid(list,position);
    }
}
