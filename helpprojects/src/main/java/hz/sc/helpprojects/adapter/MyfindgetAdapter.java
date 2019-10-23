package hz.sc.helpprojects.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hz.sc.helpprojects.R;
import hz.sc.helpprojects.helpfragment.HelpPrivamyfriend;


public class MyfindgetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<String>list;
    private List<CheckBox> checkBoxList = new ArrayList<CheckBox>();
    public MyfindgetAdapter(Context mContext, List<String>list) {
        this.mContext = mContext;
        this.list=list;
    }
    private HelpPrivamyfriend.itemViewClickListener monItemClickListener;
    public void setItemClickListener(HelpPrivamyfriend.itemViewClickListener listener) {
        monItemClickListener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.mindfind, null, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof ViewHolder0) {
            ((ViewHolder0) viewHolder).checkbox1.setText(list.get(position));

            checkBoxList.add( ((ViewHolder0) viewHolder).checkbox1);
            ((ViewHolder0) viewHolder).checkbox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monItemClickListener.onItemClickListener(checkBoxList);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private TextView myfindttext;
        private CheckBox checkbox1;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }
        private void initView(View itemView) {
            myfindttext = (TextView)itemView.findViewById(R.id.myfindttext);
            checkbox1 = (CheckBox)itemView.findViewById(R.id.checkbox1);

        }
    }
}
