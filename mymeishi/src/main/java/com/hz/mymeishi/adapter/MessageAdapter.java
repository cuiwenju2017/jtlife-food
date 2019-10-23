package com.hz.mymeishi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hz.mymeishi.R;
import com.hz.mymeishi.bean.MessageBean;
import com.hz.mymeishi.utils.HtmlText;

import java.util.List;

/**
 * 消息适配器
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<MessageBean.DataBean> messageBean;
    private Context context;
    private OnitemClick onitemClick;   //定义点击事件接口

    public MessageAdapter(Context context, List<MessageBean.DataBean> itemData) {
        mInflater = LayoutInflater.from(context);
        this.messageBean = itemData;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView iv_notifyType_icon;
        TextView tv_notifyType_name, tv_createDate, tv_content;
        private LinearLayout ll;
    }

    @Override
    public int getItemCount() {
        return messageBean.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = mInflater.inflate(R.layout.item_message, null, false);
        MessageAdapter.ViewHolder viewHolder = new MessageAdapter.ViewHolder(view);
        viewHolder.iv_notifyType_icon = view.findViewById(R.id.iv_notifyType_icon);
        viewHolder.tv_notifyType_name = view.findViewById(R.id.tv_notifyType_name);
        viewHolder.tv_createDate = view.findViewById(R.id.tv_createDate);
        viewHolder.tv_content = view.findViewById(R.id.tv_content);
        viewHolder.ll = view.findViewById(R.id.ll);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(context).load(messageBean.get(i).getNotifyType_icon()).into(viewHolder.iv_notifyType_icon);
        viewHolder.tv_notifyType_name.setText(messageBean.get(i).getNotifyType_name());
        viewHolder.tv_createDate.setText(messageBean.get(i).getCreateDate());
        //viewHolder.tv_content.setText(Html.fromHtml(messageBean.get(i).getContent()));//解析html数据
        viewHolder.tv_content.setText(HtmlText.delHTMLTag(messageBean.get(i).getContent()));

        if (onitemClick != null) {
            viewHolder.ll.setOnClickListener(new View.OnClickListener() {
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
