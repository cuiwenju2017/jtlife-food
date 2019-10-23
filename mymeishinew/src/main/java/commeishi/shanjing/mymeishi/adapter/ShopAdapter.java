package commeishi.shanjing.mymeishi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.bean.ShoppingCarDataBean;

import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private List<ShoppingCarDataBean.DataBean.CartListBean> list;

    private Context context;
    private ViewHolder myHolder;

    public ShopAdapter(List<ShoppingCarDataBean.DataBean.CartListBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            myHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_sslistview_shop, null);
             myHolder.textView= (TextView)convertView.findViewById(R.id.tv_name);
             myHolder.textView_num= (TextView)convertView.findViewById(R.id.sslisttv_num);
             myHolder.price_shop_ii = (TextView)convertView.findViewById(R.id.price_shop_ii);
             myHolder.iv_fm =(ImageView)(ImageView) convertView.findViewById(R.id.iv_fm);
            convertView.setTag(myHolder);
        } else {
            myHolder = (ViewHolder) convertView.getTag();
        }
        myHolder.textView.setText(list.get(position).getTitle());
        String getnum=Integer.toString(list.get(position).getNum());
        myHolder.textView_num.setText("x"+getnum);
        String pricesrc=Double.toString(list.get(position).getPriceSrc());
        myHolder.price_shop_ii.setText("￥"+pricesrc);
        Glide.with(context).load(list.get(position).getIcon()).into(myHolder.iv_fm);
        return convertView;
    }

    class ViewHolder {
            private TextView textView;
            private TextView textView_num;
            private TextView price_shop_ii;
            private ImageView iv_fm;
    }
}
