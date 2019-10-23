package com.hz.mymeishi.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import android.widget.LinearLayout;
import android.widget.TextView;


import com.base.commonlib.RxHttpUtils;

import com.base.commonlib.URLS;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;

import com.hz.mymeishi.R;

import com.hz.mymeishi.adapter.GalleryAdapter;
import com.hz.mymeishi.adapter.HomeRightadapter;
import com.hz.mymeishi.adapter.LeftListAdapter;

import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.bean.HomeLeftBean;
import com.hz.mymeishi.bean.HomeRightBean;

import com.hz.mymeishi.model.Shopcollbean;




import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.base.commonlib.utils.ConfirmPopWindow.iv_collect;

public class Fragment1 extends BaseFragment {
    private int oldIndex;
    private int newIndex;
    private boolean isFirstScroll;

    RecyclerView leftListview;
    RecyclerView pinnedListView;
    private boolean isScroll = true;
    private LeftListAdapter adapter;
    private HomeRightadapter sectionedAdapter;
    private boolean[] flagArray = {true, false, false, false, false, false, false, false, false,
            false, false, false, false, false};
    private LinearLayout ll_nav;
    private boolean isShowOrNot = false;
    private LinearLayout ll;
    private int mLastFirstPostion;
    private int mLastFirstTop;
    private int touchSlop;
    private LoadingDialog loadingDialog;
    private List<HomeRightBean.DataBean.ListBean>mlistright;
    private TextView title_mingc;


    @Override
    protected int setLayoutId() {
        return R.layout.fm_layout1;
    }

    //控件的部署
    @Override
    protected void init(View view, Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog(getActivity());

        leftListview = (RecyclerView) view.findViewById(R.id.left_listview);
        pinnedListView = (RecyclerView) view.findViewById(R.id.pinnedListView);
        title_mingc =(TextView) view.findViewById(R.id.title_mingc);
        ll_nav = view.findViewById(R.id.ll_nav);
        ll = view.findViewById(R.id.ll);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                // 这里要复写一下，因为默认宽高都是wrap_content
                // 这个不复写，你点击的背景色就只充满你的内容
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pinnedListView.setLayoutManager(linearLayoutManager);
        sectionedAdapter=new HomeRightadapter(getActivity());
        pinnedListView.setAdapter(sectionedAdapter);

       /* final MainSectionedAdapter sectionedAdapter = new MainSectionedAdapter(getActivity(), leftStr, rightStr);
        pinnedListView.setAdapter(sectionedAdapter);*/
        //设置分类可隐藏
        ll_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowOrNot == false) {
                    ll.setVisibility(View.GONE);
                    isShowOrNot = true;
                } else {
                    ll.setVisibility(View.VISIBLE);
                    isShowOrNot = false;
                }
            }
        });





    }

    private List<HomeLeftBean.DataBean> list = new ArrayList<>();
    private List<HomeRightBean.DataBean.ListBean> listRight = new ArrayList<>();
    private List<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> listRightnb = new ArrayList<>();

    private GalleryAdapter galleryAdapter;
    private List<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> pgcGoodsListBeans = new ArrayList<>();

    //网络请求
    @Override
    public void fetchData() {
        shopsoucang();

        Log.d("ssss", "---------*********/////////");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLS.Host)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<HomeLeftBean> call = gitHubService.getCall();
        loadingDialog.show();
        call.enqueue(new Callback<HomeLeftBean>() {
            @Override
            public void onResponse(Call<HomeLeftBean> call, Response<HomeLeftBean> response) {
                loadingDialog.dismiss();
                list = response.body().getData();
                adapter = new LeftListAdapter(getActivity());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
                    @Override
                    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                        // 这里要复写一下，因为默认宽高都是wrap_content
                        // 这个不复写，你点击的背景色就只充满你的内容
                        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                    }
                };
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                leftListview.setLayoutManager(linearLayoutManager);
                adapter.setList((ArrayList<HomeLeftBean.DataBean>)list);
                leftListview.setAdapter(adapter);
                adapter.setItemClickListener(new OnRecyclerViewClickListener() {

                    @Override
                    public void onItemClickListener(int Position, List<HomeLeftBean.DataBean> dataBeanList) {

                        Dataquary(Position,dataBeanList);
                        title_mingc.setText(dataBeanList.get(Position).getCategoryName());
                        title_mingc.setTextColor(Color.BLACK);
                    }

                    @Override
                    public void onItemLongClickListener(int Position, List<HomeLeftBean.DataBean> dataBeanList) {

                    }
                });
            }

            @Override
            public void onFailure(Call<HomeLeftBean> call, Throwable t) {
                ToastUtil.showShort(getActivity(), "请求失败");
            }
        });

        Call<HomeRightBean> rightBeanCall = gitHubService.getHomeRight(1);
        rightBeanCall.enqueue(new Callback<HomeRightBean>() {
            @Override
            public void onResponse(Call<HomeRightBean> call, Response<HomeRightBean> response) {
                loadingDialog.dismiss();
                mlistright = response.body().getData().getList();
//                for (int i = 0; i <listRight.size() ; i++) {
//                    Log.d("ddsad",response.body().getData().getList().get(i).getPgcGoodsList()+"");
//
//                }
                sectionedAdapter.setList((ArrayList<HomeRightBean.DataBean.ListBean>)mlistright);

            }

            @Override
            public void onFailure(Call<HomeRightBean> call, Throwable t) {
                ToastUtil.showShort(getActivity(), "请求失败");
            }
        });

    }


    private void Dataquary(int position, List<HomeLeftBean.DataBean> dataBeanList) {
        Log.d("ddsad",dataBeanList.get(position).getId());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLS.Host)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<HomeRightBean> rightBeanCall = gitHubService.getHomeRightddd(1,dataBeanList.get(position).getId());
        loadingDialog.show();
        rightBeanCall.enqueue(new Callback<HomeRightBean>() {
            @Override
            public void onResponse(Call<HomeRightBean> call, Response<HomeRightBean> response) {
                loadingDialog.dismiss();
                mlistright = response.body().getData().getList();
//                for (int i = 0; i <listRight.size() ; i++) {
//                    Log.d("ddsad",response.body().getData().getList().get(i).getPgcGoodsList()+"");
//
//                }
                sectionedAdapter.setList((ArrayList<HomeRightBean.DataBean.ListBean>)mlistright);

            }

            @Override
            public void onFailure(Call<HomeRightBean> call, Throwable t) {
                ToastUtil.showShort(getActivity(), "请求失败");
            }
        });
    }

    public interface OnRecyclerViewClickListener {
        void onItemClickListener(int Position, List<HomeLeftBean.DataBean> dataBeanList);
        void onItemLongClickListener(int Position, List<HomeLeftBean.DataBean> dataBeanList);
    }
    private void shopsoucang() {
        RxHttpUtils
                .createApi(GitHubService.class)
                .dianpu("1",2)
                .compose(Transformer.<BaseData<Shopcollbean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Shopcollbean>() {

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Shopcollbean data) {
                        if(data.getIsFav().equals("1")){
                            iv_collect.setBackgroundColor(Color.parseColor("#f44336"));
                        }

                    }
                });
    }


    public interface Listener {
        void send(String s);
    }
}
