package com.hz.mymeishi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commonlib.utils.ConfirmPopWindow;
import com.hz.mymeishi.R;
import com.hz.mymeishi.bean.HomeRightBean;


import java.util.ArrayList;
import java.util.List;

public class HomeRightadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<HomeRightBean.DataBean.ListBean> rightStr;
    private  HomeRightBean.DataBean.ListBean listbean;
    private boolean isShowOrNot = false;


    public void setList(ArrayList<HomeRightBean.DataBean.ListBean> list) {
        this.rightStr = list;
        notifyDataSetChanged();
    }
    public HomeRightadapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                return new HomeRightadapter.ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.right_list_item2, null, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HomeRightBean.DataBean.ListBean listBean=rightStr.get(position);
        this .listbean=listBean;
        if (viewHolder instanceof ViewHolder0) {
            ((ViewHolder0) viewHolder).tv_text.setText(listBean.getTitle());
            Log.d("dddad",listBean.getTitle());
            StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//布局横向
            ((ViewHolder0) viewHolder).recyclerView.setLayoutManager(linearLayoutManager);
            ((ViewHolder0) viewHolder).recyclerView.setLayoutManager(linearLayoutManager);
            List<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> pgcGoodsList = listBean.getPgcGoodsList();
            RighphotoAdapter righphotoAdapter=new RighphotoAdapter(mContext,listBean.getPublisherId());
            righphotoAdapter.setList((ArrayList<HomeRightBean.DataBean.ListBean.PgcGoodsListBean>)pgcGoodsList);
            ((ViewHolder0) viewHolder).recyclerView.setAdapter(righphotoAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return rightStr == null ? 0 : rightStr.size();
    }


    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private TextView tv_text;
        private RecyclerView recyclerView;
        private TextView tv_qw;
        private ImageView imag_iv_pop;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);

            initView(itemView);
        }

        private void initView(View itemView) {
            tv_text = (TextView)itemView.findViewById(R.id.tv_text);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerView);
            tv_qw =(TextView) itemView.findViewById(R.id.tv_qw);
            tv_qw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isShowOrNot == false) {
                        tv_text .setMaxLines(3);
                        isShowOrNot = true;
                    } else {
                        tv_text.setMaxLines(10);
                        isShowOrNot = false;
                    }
                }
            });
            imag_iv_pop =(ImageView) itemView.findViewById(R.id.iv_pop);
            imag_iv_pop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    ConfirmPopWindow confirmPopWindow= new ConfirmPopWindow(mContext);
                    confirmPopWindow.iv_collect.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    confirmPopWindow.showAtBottom(imag_iv_pop);

                }
            });
        }
    }




}
