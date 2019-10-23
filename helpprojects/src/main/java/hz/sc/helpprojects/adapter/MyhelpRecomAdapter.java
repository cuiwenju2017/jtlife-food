package hz.sc.helpprojects.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import hz.sc.helpprojects.R;

public class MyhelpRecomAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //三个final分别代表三个不同的布局
    public static final int ITEMONE = 1;
    public static final int ITEMTWO = 2;
    public static final int ITEMTHREE = 3;

    //上下文
    private Context context;

    /**
     * 构造方法
     *
     * @param context
     */
    public MyhelpRecomAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //这时候就要根据这个i来判断加哪一个布局了

        View inflate = null;
        RecyclerView.ViewHolder viewHolder = null;

        //根据i返回不同布局
        switch (i) {
            case ITEMONE:
                inflate = LayoutInflater.from(context).inflate(R.layout.item_one, viewGroup, false);
                viewHolder = new OneItemHolder(inflate);
                break;
            case ITEMTWO:
                inflate = LayoutInflater.from(context).inflate(R.layout.item_two, viewGroup, false);
                viewHolder = new TwoItemHolder(inflate);
                break;
            case ITEMTHREE:
                inflate = LayoutInflater.from(context).inflate(R.layout.item_three, viewGroup, false);
                viewHolder = new ThreeItemHolder(inflate);
                break;

        }

        //返回布局
        return viewHolder;
    }

    /**
     * 绑定控件，这里可以写一些事件方法等
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        //如果当前的 viewHolder 属于 OneItemHolder 则执行
        if (viewHolder instanceof OneItemHolder) {

            //写绑定或这写事件可以如下


        } else if (viewHolder instanceof TwoItemHolder) {
            //此处省略。。。。
        } else if (viewHolder instanceof ThreeItemHolder) {
            //此处省略。。。。
        }
    }

    /**
     * 返回条目总数量，假设16个条目
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 3;
    }

    /**
     * 返回条目类型(这里就做个简单的判断区分)
     *
     * @param position 代表第几个条目
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (position ==1) {
            return ITEMTWO;
        } else if (position ==2) {
            return ITEMTHREE;
        } else  {
            return ITEMONE;
        }

    }

    /**
     * 第一个布局的Holder，要继承自RecyclerView.ViewHolder，这里你可以绑定控件
     */
    class OneItemHolder extends RecyclerView.ViewHolder {

        //举例
        TextView one_text;

        public OneItemHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    /**
     * 第二个布局的Holder，要继承自RecyclerView.ViewHolder，这里你可以绑定控件
     */
    class TwoItemHolder extends RecyclerView.ViewHolder {

        public TwoItemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 第三个布局的Holder，要继承自RecyclerView.ViewHolder，这里你可以绑定控件
     */
    class ThreeItemHolder extends RecyclerView.ViewHolder {


        public ThreeItemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
