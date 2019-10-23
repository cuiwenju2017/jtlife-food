package com.hz.mymeishi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.hz.mymeishi.R;
import com.hz.mymeishi.model.Sppjlistbean;

import java.util.List;

public class AdapterSppji extends BaseAdapter {
    private Context context;
    private List<Sppjlistbean.DataBean.ListBean> list;
    private ImageView gravatar_image;


    public AdapterSppji(Context context, List<Sppjlistbean.DataBean.ListBean> list) {
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
        if(convertView == null) {
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.sppjlistitem, viewGroup, false);
            holder.contextpj = (TextView)convertView.findViewById(R.id.contextpj);
            holder.createDate = (TextView)convertView.findViewById(R.id.createDate);
            holder.ratingBarlist =(RatingBar) convertView.findViewById(R.id.ratingBarlist);
            holder.gravatar_image =(ImageView) convertView.findViewById(R.id.gravatar_image);
             holder.usercode = (TextView)convertView.findViewById(R.id.usercode);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Sppjlistbean.DataBean.ListBean listBean=list.get(position);
        holder.contextpj.setText(listBean.getContext());
        holder.createDate.setText(listBean.getCreateDate());
        Glide.with(context).load(listBean.getGravatar()).into(holder.gravatar_image);
        int prduce=Integer.parseInt(listBean.getProduceLevel());
        holder.ratingBarlist.setRating(prduce);
        holder.usercode.setText(listBean.getUsercode());
        return convertView;
    }
    public static class MyViewHolder {
        private TextView contextpj;
        private TextView createDate;
        private RatingBar ratingBarlist;
        private ImageView gravatar_image;
        private  TextView usercode;

    }
}
