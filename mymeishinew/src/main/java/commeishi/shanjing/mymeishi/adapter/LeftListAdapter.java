package commeishi.shanjing.mymeishi.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.bean.HomeLeftBean;
import commeishi.shanjing.mymeishi.fragment.Fragment1;


import java.util.ArrayList;


public class LeftListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<HomeLeftBean.DataBean> list;
    private Context mContext;
    public void setList(ArrayList<HomeLeftBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public LeftListAdapter(Context mContext) {
        this.mContext = mContext;
    }
    private Fragment1.OnRecyclerViewClickListener monItemClickListener;
    public void setItemClickListener(Fragment1.OnRecyclerViewClickListener listener) {
        monItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View view = LayoutInflater.from(mContext).inflate(R.layout.left_list_item,null);
                ViewHolder0 viewHolder0 =new ViewHolder0(view);
                return viewHolder0;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HomeLeftBean.DataBean listbean=list.get(position);
        if (viewHolder instanceof ViewHolder0) {
            ((ViewHolder0) viewHolder).left_list_item.setText(listbean.getCategoryName());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private TextView left_list_item;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monItemClickListener.onItemClickListener(getAdapterPosition(),list);
                }
            });
        }

        private void initView(View itemView) {
            left_list_item =(TextView) itemView.findViewById(R.id.left_list_item);
        }
    }
}
