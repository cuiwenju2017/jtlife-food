package com.hz.mymeishi.activity;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;
import com.hz.mymeishi.R;

import com.hz.mymeishi.View.PopScreen;
import com.hz.mymeishi.adapter.SearchGoodsAdapter;
import com.hz.mymeishi.api.GitHubService;

import com.hz.mymeishi.model.Serchbeangoods;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GoddsSerschActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout rl_back;
    private TextView tv_title;
    private EditText et_search;
    private LoadingDialog loadingDialog;
    private LinearLayout zonghepaixu;
    private TextView text_zhziti;
    private ImageView image_shangla;
    //黑色背景布局
    private View darkView;
    private Animation animIn;
    private Animation animOut;
    private TextView text_zonghe;
    private TextView text_pingjiayx;
    private TextView text_congdidaog;
    private TextView text_jiageconggao;
    private LinearLayout lin_zonghe_layout;
    private LinearLayout lin_pingjia_layout;
    private LinearLayout lin_congdi_layout;
    private LinearLayout lin_conggao_layout;
    private List<Serchbeangoods.DataBean.ListBean>mlist;
    private SearchGoodsAdapter searchGoodsAdapter;
    private RecyclerView serschRecycrview;
    private DrawerLayout dlShow;
    private TextView serch_shaixuan;
    private PopScreen screen;
    private TextView serschen_shaixuan;
    private LinearLayout lin_goods_sercch;
    private Animation pingyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_godds_sersch);
        initView();


    }



    private void initView() {


        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("商品搜索");
        et_search =(EditText) findViewById(R.id.et_search);
        zonghepaixu =(LinearLayout) findViewById(R.id.zonghepaixu);
        text_zhziti =(TextView) findViewById(R.id.text_zhziti);

        zonghepaixu.setOnClickListener(this);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if(s.equals("")||s==null){
                        ToastUtils.showToast("暂无搜到任何商品");
                        return;
                    }
                initDate(s);

            }
        });
        //黑色背景的初始化
        animIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);
//        darkView = findViewById(R.id.main_darkview);
//        darkView.setVisibility(View.GONE);
        zonghepaixu.setSelected(false);
        serschRecycrview = (RecyclerView)findViewById(R.id.serschRecycrview);
        serschen_shaixuan = (TextView)findViewById(R.id.serschen_shaixuan);
        lin_goods_sercch = (LinearLayout)findViewById(R.id.lin_goods_sercch);
        screen = new PopScreen(GoddsSerschActivity.this,lin_goods_sercch);

        serschen_shaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.showAtLocation(GoddsSerschActivity.this.findViewById(R.id.lin_goods_sercch), Gravity.RIGHT, 0, 0);
//

            }
        });

    }

    private void initDate(Editable s) {

        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .shouyeserch(1,s.toString(),"0")
                .compose(Transformer.<Serchbeangoods>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Serchbeangoods>() {


                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Serchbeangoods serchbeangoods) {
                        mlist=serchbeangoods.getData().getList();
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        serschRecycrview.setLayoutManager(linearLayoutManager);
                        searchGoodsAdapter = new SearchGoodsAdapter(GoddsSerschActivity.this);
                        searchGoodsAdapter.setList((ArrayList<Serchbeangoods.DataBean.ListBean>)mlist);
                        serschRecycrview.setAdapter(searchGoodsAdapter);
                    }
                    @Override
                    public void onError(Throwable throwable) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        //背景颜色变暗
        serschRecycrview.startAnimation(animIn);
        int i = view.getId();
        if (i == R.id.zonghepaixu) {
            zonghepaixu.setSelected(true);
            final PopupWindow popupWindow = new PopupWindow();
            View v = LayoutInflater.from(GoddsSerschActivity.this).inflate(R.layout.popwindow_zonghe, null);
            //综合排序
            text_zonghe = (TextView) v.findViewById(R.id.text_zonghe);
            lin_zonghe_layout = (LinearLayout) v.findViewById(R.id.lin_zonghe_layout);
            lin_zonghe_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text_zonghe.setTextColor(getResources().getColor(R.color.fa_color));
//                        text_pingjiayx.setTextColor(getResources().getColor(R.color.black));
//                        text_congdidaog.setTextColor(getResources().getColor(R.color.black));
//                        text_jiageconggao.setTextColor(getResources().getColor(R.color.black));

                    text_zhziti.setText("综合排序");
                    int type = 1;
                    fordate(type);
                    popupWindow.dismiss();

                }

            });
            //评价优先
            text_pingjiayx = (TextView) v.findViewById(R.id.text_pingjiayx);
            lin_pingjia_layout = (LinearLayout) v.findViewById(R.id.lin_pingjia_layout);
            lin_pingjia_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        text_zonghe.setTextColor(getResources().getColor(R.color.black));
                    text_pingjiayx.setTextColor(getResources().getColor(R.color.fa_color));
//                        text_congdidaog.setTextColor(getResources().getColor(R.color.black));
//                        text_jiageconggao.setTextColor(getResources().getColor(R.color.black));
                    text_zhziti.setText("评价优先");
                    int type = 2;
                    fordate(type);
                    popupWindow.dismiss();
                }
            });
            //价格从低到高
            text_congdidaog = (TextView) v.findViewById(R.id.text_congdidaog);
            lin_congdi_layout = (LinearLayout) v.findViewById(R.id.lin_congdi_layout);
            lin_congdi_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        text_zonghe.setTextColor(getResources().getColor(R.color.black));
//                        text_pingjiayx.setTextColor(getResources().getColor(R.color.black));
                    text_congdidaog.setTextColor(getResources().getColor(R.color.fa_color));
//                        text_jiageconggao.setTextColor(getResources().getColor(R.color.black));
                    text_zhziti.setText("价格从低到高");
                    int type = 3;
                    fordate(type);
                    popupWindow.dismiss();
                }
            });
            //价格从高到低
            text_jiageconggao = (TextView) v.findViewById(R.id.text_jiageconggao);
            lin_conggao_layout = (LinearLayout) v.findViewById(R.id.lin_conggao_layout);
            lin_conggao_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        text_zonghe.setTextColor(getResources().getColor(R.color.black));
//                        text_pingjiayx.setTextColor(getResources().getColor(R.color.black));
//                        text_congdidaog.setTextColor(getResources().getColor(R.color.black));
                    text_jiageconggao.setTextColor(getResources().getColor(R.color.fa_color));
                    text_zhziti.setText("价格从高到低");
                    int type = 4;
                    fordate(type);
                    popupWindow.dismiss();
                }
            });
            popupWindow.setContentView(v);
            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.style.popuwindowStyle);
            popupWindow.showAsDropDown(zonghepaixu, 0, 0);
            popupWindow.setOnDismissListener(mDismissListener);
            if (text_zhziti.getText().toString().equals("综合排序")) {
                text_zonghe.setTextColor(getResources().getColor(R.color.fa_color));
            } else if (text_zhziti.getText().toString().equals("评价优先")) {
                text_pingjiayx.setTextColor(getResources().getColor(R.color.fa_color));
            } else if (text_zhziti.getText().toString().equals("价格从低到高")) {
                text_congdidaog.setTextColor(getResources().getColor(R.color.fa_color));
            } else if (text_zhziti.getText().toString().equals("价格从高到低")) {
                text_jiageconggao.setTextColor(getResources().getColor(R.color.fa_color));
            }

        }

    }
    private PopupWindow.OnDismissListener mDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            //背景颜色变回正常
            zonghepaixu.setSelected(false);
            serschRecycrview.startAnimation(animOut);

        }
    };

    private void fordate(int type) {
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .shouyeserch(1,type+"","0")
                .compose(Transformer.<Serchbeangoods>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Serchbeangoods>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Serchbeangoods serchbeangoods) {
                        mlist=serchbeangoods.getData().getList();
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        serschRecycrview.setLayoutManager(linearLayoutManager);
                        searchGoodsAdapter = new SearchGoodsAdapter(GoddsSerschActivity.this);
                        searchGoodsAdapter.setList((ArrayList<Serchbeangoods.DataBean.ListBean>)mlist);
                        serschRecycrview.setAdapter(searchGoodsAdapter);

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
