package com.hz.mymeishi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.hz.mymeishi.R;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.bean.CommentNumBean;

/**
 * 评价
 */
@SuppressLint("ValidFragment")
public class CommentFragment extends BaseFragment {

    private LoadingDialog loadingDialog;
    private String goodsid;
    private TextView tv_comment_num, tv_comment_tip;
    private SmartRefreshLayout mRefresh;

    @SuppressLint("ValidFragment")
    public CommentFragment(String goodsid) {
        this.goodsid = goodsid;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initView(view);
        initData();
    }

    private void initData() {
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                fetchData();
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
            }
        });
    }

    private void initView(View view) {
        tv_comment_num = view.findViewById(R.id.tv_comment_num);
        tv_comment_tip = view.findViewById(R.id.tv_comment_tip);
        mRefresh = view.findViewById(R.id.refresh);
    }

    @Override
    public void fetchData() {
        loadingDialog = new LoadingDialog(getActivity());
        RxHttpUtils
                .createApi(GitHubService.class)
                .getCommentNum(goodsid)
                .compose(Transformer.<BaseData<CommentNumBean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<CommentNumBean>() {

                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.showShort(getActivity(), errorMsg);
                    }

                    @Override
                    protected void onSuccess(CommentNumBean data) {
                        tv_comment_num.setText("评价(" + data.getJudgeNum() + ")");

                        Log.i("aaa", "onSuccess: " + data.getJudgeNum());

                        if (data.getJudgeNum() == 0) {
                            tv_comment_tip.setVisibility(View.VISIBLE);
                        } else {
                            tv_comment_tip.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
