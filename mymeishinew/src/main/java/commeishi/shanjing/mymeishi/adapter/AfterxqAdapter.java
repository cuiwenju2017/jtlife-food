package commeishi.shanjing.mymeishi.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.model.Aftershxqbean;
import java.util.List;

public class AfterxqAdapter extends BaseAdapter {


    private Context context;
    private List<Aftershxqbean.DataBean.OrderListBean> list;

    public AfterxqAdapter(Context context, List<Aftershxqbean.DataBean.OrderListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.itemafterxq, viewGroup, false);
            holder.after_iv_fm = (ImageView)convertView.findViewById(R.id.after_iv_fm);
            holder.aftertitle = (TextView)convertView.findViewById(R.id.aftertitle);
            holder.aftertv_price = (TextView)convertView.findViewById(R.id.aftertv_price);
            holder.faternum =(TextView) convertView.findViewById(R.id.faternum);
            holder.afterjian =(TextView) convertView.findViewById(R.id.afterjian);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Aftershxqbean.DataBean.OrderListBean listBean=list.get(position);
        Glide.with(context).load(listBean.getIcon()).into(holder.after_iv_fm);
        holder.aftertitle.setText(listBean.getGoodsTitle());
        String pricebuy=Double.toString(listBean.getPriceBuy());
        holder.aftertv_price.setText("￥"+pricebuy);
        holder.faternum.setText("×"+listBean.getNumBuy());
        holder.afterjian.setText(listBean.getNum()+"件");

        return convertView;
    }
    public static class MyViewHolder {

        private ImageView after_iv_fm;
        private TextView aftertitle;
        private TextView aftertv_price;
        private TextView faternum;
        private TextView afterjian;
    }
}
