package com.hz.mymeishi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hz.mymeishi.R;
import com.hz.mymeishi.activity.AppointmentexActivity;
import com.hz.mymeishi.model.Wlgslistbean;

import java.util.List;

public class WllistAdapter extends BaseAdapter {
    private Context context;
    private List<Wlgslistbean.DataBean> list;
    public WllistAdapter(Context context, List<Wlgslistbean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    private AppointmentexActivity.itemgsClickListener monItemClickListener;
    public void setItemClickListener(AppointmentexActivity.itemgsClickListener listener) {
        monItemClickListener = listener;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder holder;
        if(convertView == null){
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.logisticsitem, viewGroup, false);
            holder.textview = (TextView) convertView.findViewById(R.id.itemtextviewis);
            convertView.setTag(holder);
        }else {
            holder =(MyViewHolder) convertView.getTag();
        }
        final Wlgslistbean.DataBean dataBean=list.get(position);
        holder.textview.setText(dataBean.getEname());

        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ee11", view+"");

                monItemClickListener.ongsItemClickListener(position,list);
            }
        });
        return convertView;
    }
    public static class MyViewHolder {
        private TextView textview;

    }
}
