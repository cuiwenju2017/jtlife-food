package com.hz.mymeishi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hz.mymeishi.R;
import com.hz.mymeishi.model.Goosmessges;


import java.util.List;

public class SpjugelistAdapter extends BaseAdapter {
    private Context context;
    private List<Goosmessges.DataBean.JudgeListBean>list;
    public SpjugelistAdapter(Context context, List<Goosmessges.DataBean.JudgeListBean> list) {
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
        if(convertView==null){
            holder= new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.sppjlistitem, viewGroup, false);
            holder.contextpj = (TextView)convertView.findViewById(R.id.contextpj);
            holder.createDate = (TextView)convertView.findViewById(R.id.createDate);
            holder.ratingBarlist =(RatingBar) convertView.findViewById(R.id.ratingBarlist);

            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Goosmessges.DataBean.JudgeListBean judgeListBean=list.get(position);
        holder.contextpj.setText(judgeListBean.getContext());
        holder.createDate.setText(judgeListBean.getCreateDate());
        int prduce=Integer.parseInt(judgeListBean.getProduceLevel());
        holder.ratingBarlist.setRating(prduce);
        return convertView;
    }
    public static class MyViewHolder {
        private TextView contextpj;
        private TextView createDate;
        private RatingBar ratingBarlist;
    }
}
