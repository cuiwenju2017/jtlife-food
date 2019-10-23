package com.hz.mymeishi.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.view.LoadingDialog;
import com.hz.mymeishi.R;
import com.base.commonlib.utils.ConfirmPopWindow;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.bean.HomeLeftBean;
import com.hz.mymeishi.bean.HomeRightBean;
import com.hz.mymeishi.model.Homeyoublibu;

import java.util.List;

public class MainSectionedAdapter extends SectionedBaseAdapter {

    private Context mContext;
    private List<HomeLeftBean.DataBean> leftStr;
    private List<HomeRightBean.DataBean.ListBean> rightStr;
    private boolean isShowOrNot = false;
    public static RecyclerView recyclerView;
    private LoadingDialog loadingDialog;
    private List<Homeyoublibu.ListBean.PgcGoodsListBean>mlist;

    public MainSectionedAdapter(Context context, List<HomeLeftBean.DataBean> listLeft, List<HomeRightBean.DataBean.ListBean> listRight) {
        this.mContext = context;
        this.rightStr = listRight;
        this.leftStr = listLeft;
    }

    @Override
    public Object getItem(int section, int position) {
        return rightStr.size();
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return leftStr.size();
    }

    @Override
    public int getCountForSection(int section) {
        return rightStr.size();
    }



    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.right_list_item2, null);
            /**
             * 配置RecyclerView适配器
             */
            recyclerView = layout.findViewById(R.id.recyclerView);
            Log.d("wwww","+++++++++++++");
            initDatas();
            //设置布局管理器
            StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//布局横向
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            layout = (LinearLayout) convertView;
        }
        ((TextView) layout.findViewById(R.id.tv_text)).setText(rightStr.get(position).getTitle());


        /**
         * 设置点击iv_pop弹窗
         */
        final ImageView iv_pop = layout.findViewById(R.id.iv_pop);
        iv_pop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmPopWindow(mContext).showAtBottom(iv_pop);
            }
        });

        /**
         * 点击展开全文设置
         */
        final LinearLayout finalLayout = layout;
        ((TextView) layout.findViewById(R.id.tv_qw)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowOrNot == false) {
                    ((TextView) finalLayout.findViewById(R.id.tv_text)).setMaxLines(3);
                    isShowOrNot = true;
                } else {
                    ((TextView) finalLayout.findViewById(R.id.tv_text)).setMaxLines(10);
                    isShowOrNot = false;
                }
            }
        });

        /**
         * banner轮播图
         */
       /* Banner banner = (Banner) layout.findViewById(R.id.banner);
        int[] imageResouceID = new int[]{R.drawable.pic, R.drawable.pic,
                R.drawable.pic, R.drawable.pic};//定义图片数组
        List<Integer> imageList = new ArrayList<>();
        for (int i = 0; i < imageResouceID.length; i++) {
            imageList.add(imageResouceID[i]);//把图片放入List中
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(mContext).load(path).into(imageView);
                }
            });
            banner.setDelayTime(3000);//设置轮播时间
            banner.setImages(imageList);//设置图片资源
            banner.start();//开始进行banner渲染
            //banner.stopAutoPlay();//停止自动轮播
            banner.startAutoPlay();//开始自动轮播
        }*/



        /**
         * item点击事件
         */

        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                /* Intent intent = new Intent(context, XQActivity.class);
                intent.putExtra("title", pgcGoodsListBeans.get(i).getTitle());
                context.startActivity(intent);*/
            }
        });
        return layout;
    }
    private GalleryAdapter galleryAdapter;
    private void initDatas() {
        loadingDialog = new LoadingDialog(mContext);
        String id="1";
        RxHttpUtils
                .createApi(GitHubService.class)
                .getHomeneibu(1,id)
                .compose(Transformer.<BaseData<Homeyoublibu>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Homeyoublibu>() {

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Homeyoublibu data) {
                        Log.d("ffs",data.getList().get(0).getPgcGoodsList().get(0).getId());


                    }
                });


    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(leftStr.get(section).getCategoryName());
        return layout;
    }

}
