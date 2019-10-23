package commeishi.shanjing.mymeishi.adapter;

import android.content.Context;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.bean.MessageNotificationBean;

import java.util.List;

/**
 * 消息通知适配器
 */
public class MessageNotificationAdapter extends RecyclerView.Adapter<MessageNotificationAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<MessageNotificationBean.DataBean.ListBean> messageNotificationBean;
    private Context context;
    private OnitemClick onitemClick;   //定义点击事件接口

    public MessageNotificationAdapter(Context context, List<MessageNotificationBean.DataBean.ListBean> itemData) {
        mInflater = LayoutInflater.from(context);
        this.messageNotificationBean = itemData;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        MaterialCardView mcv;
        TextView tv_title, tv_createDate, tv_content;
    }

    @Override
    public int getItemCount() {
        return messageNotificationBean.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public MessageNotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = mInflater.inflate(R.layout.item_message_notification, null, false);
        MessageNotificationAdapter.ViewHolder viewHolder = new MessageNotificationAdapter.ViewHolder(view);
        viewHolder.tv_title = view.findViewById(R.id.tv_title);
        viewHolder.tv_createDate = view.findViewById(R.id.tv_createDate);
        viewHolder.tv_content = view.findViewById(R.id.tv_content);
        viewHolder.mcv = view.findViewById(R.id.mcv);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(MessageNotificationAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText(messageNotificationBean.get(i).getTitle());
        viewHolder.tv_createDate.setText(messageNotificationBean.get(i).getCreateDate());
        viewHolder.tv_content.setText(Html.fromHtml(messageNotificationBean.get(i).getContent()));

        if (onitemClick != null) {
            viewHolder.mcv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //在TextView的地方进行监听点击事件，并且实现接口
                    onitemClick.onItemClick(i);
                }
            });
        }

    }

    //定义设置点击事件监听的方法
    public void setOnitemClickLintener(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick(int position);
    }
}
