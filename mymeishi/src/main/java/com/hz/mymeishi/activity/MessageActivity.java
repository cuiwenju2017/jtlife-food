package com.hz.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.hz.mymeishi.R;
import com.hz.mymeishi.adapter.MessageAdapter;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 消息页面
 */
public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private LoadingDialog loadingDialog;
    private RecyclerView rv;
    private MessageAdapter messageAdapter;
    private SmartRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
        initData();
    }

    private List<MessageBean.DataBean> list = new ArrayList<>();

    private void initData() {
        tv_title.setText("消息");
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .getMessage()
                .compose(Transformer.<MessageBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<MessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(MessageBean messageBean) {
                        loadingDialog.dismiss();
                        list = messageBean.getData();
                        //设置布局管理器
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                        rv.setLayoutManager(linearLayoutManager);
                        messageAdapter = new MessageAdapter(MessageActivity.this, list);
                        rv.setAdapter(messageAdapter);
                        //item点击事件
                        messageAdapter.setOnitemClickLintener(new MessageAdapter.OnitemClick() {
                            @Override
                            public void onItemClick(int position) {
                                if (position == 0) {//消息通知
                                    startActivity(new Intent(MessageActivity.this, MessageNotificationActivity.class));
                                }
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(MessageActivity.this, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {
        rl_back = findViewById(R.id.rl_back);
        tv_title = findViewById(R.id.tv_title);
        rv = findViewById(R.id.rv);
        srl = findViewById(R.id.srl);
        rl_back.setOnClickListener(this);
        srl.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
                if (srl != null) {
                    srl.finishRefresh();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_back) {
            finish();
        }
    }

}
