package com.hz.mymeishi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.hz.mymeishi.R;
import com.hz.mymeishi.View.Bean.SuggestionAddress;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/8/30.
 */

public class SuggestionAddressAdpter extends BaseAdapter {
    private ArrayList<SuggestionAddress> list;
    private Context mCntext;
    public SuggestionAddressAdpter(Context mCntext) {
        this.mCntext = mCntext;
    }

    public void setList(ArrayList<SuggestionAddress> list) {

        this.list = list;
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mCntext).inflate(R.layout.address_item,null);
            holder.tv_suggestion = (TextView) convertView.findViewById(R.id.tv_suggestionAddress);
            holder.tv_street = (TextView) convertView.findViewById(R.id.tv_streetMessage);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.tv_suggestion.setText(list.get(position).getName());
        if(!list.get(position).getStreetMessage().equals("")){
            holder.tv_street.setText(list.get(position).getStreetMessage());
        }else {
            holder.tv_street.setText("暂无位置");
        }

        Log.d("ddqqqwssssaaa",list.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        TextView tv_suggestion,tv_street;
    }
}
