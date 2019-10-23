package commeishi.shanjing.mymeishi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.model.GetReturnOrderTypeList;

import java.util.List;

public class GetxzAdapter extends BaseAdapter {
    private Context context;
    private List<GetReturnOrderTypeList.DataBean>list;
    public GetxzAdapter(Context context, List<GetReturnOrderTypeList.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    private RefunAdapter.itemViewClickListener monItemClickListener;
    public void setItemClickListener(RefunAdapter.itemViewClickListener listener) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layou_itemxuanzeqi, viewGroup, false);
            holder.textview = (TextView) convertView.findViewById(R.id.textView_xuanzq);
            convertView.setTag(holder);
        }else {
            holder =(MyViewHolder) convertView.getTag();
        }
        final GetReturnOrderTypeList.DataBean dataBean=list.get(position);
        holder.textview.setText(dataBean.getValue());
        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monItemClickListener.onItemClickListener(position,list);
            }
        });
        return convertView;
    }
    public static class MyViewHolder {
            private TextView textview;
    }
}
