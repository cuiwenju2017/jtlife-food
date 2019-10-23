package com.hz.mymeishi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.hz.mymeishi.R;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.model.Goosmessges;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 详情
 */
@SuppressLint("ValidFragment")
public class ParticularsFragment extends BaseFragment {

    private LoadingDialog loadingDialog;
    private String goodsid;
    private SmartRefreshLayout mRefresh;
    private WebView wv;
    private static final String WEBVIEW_CONTENT_NIGHT = "<html><head></head><body style=\"text-indent:0em;\">%s</body></html>";

    @SuppressLint("ValidFragment")
    public ParticularsFragment(String goodsid) {
        this.goodsid = goodsid;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_particulars;
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
        mRefresh = view.findViewById(R.id.mRefresh);
        wv = view.findViewById(R.id.wv);
    }

    @Override
    public void fetchData() {
        RxHttpUtils
                .createApi(GitHubService.class)
                .goodsmessage(goodsid)
                .compose(Transformer.<Goosmessges>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Goosmessges>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Goosmessges goosmessges) {
                        String content = goosmessges.getData().getContent();
                        wv.loadDataWithBaseURL(null, String.format(WEBVIEW_CONTENT_NIGHT, content), "text/html", "utf-8", null);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
