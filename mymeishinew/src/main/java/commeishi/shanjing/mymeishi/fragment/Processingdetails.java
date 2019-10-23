package commeishi.shanjing.mymeishi.fragment;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.adapter.RefunAdapter;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.Shouhoubean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class Processingdetails extends BaseFragment {

    private RecyclerView processlistfragment;
    private LoadingDialog loadingDialog;
    private List<Shouhoubean.DataBean.ListBean> mlist;
    private RefunAdapter refunAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_processingdetails;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initView(view);
        initDSS();

    }

    private void initView(View view) {
        processlistfragment = (RecyclerView)view.findViewById(R.id.processlistfragment);
    }
    private void initDSS() {
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
        processlistfragment.setLayoutManager(linearLayoutManager);
        refunAdapter = new RefunAdapter(getActivity());
        processlistfragment.setAdapter(refunAdapter);
    }

    @Override
    public void fetchData() {
        loadingDialog = new LoadingDialog(getActivity());
        String orderstatus="22";
        RxHttpUtils
                .createApi(GitHubService.class)
                .shouhou(1,orderstatus)
                .compose(Transformer.<Shouhoubean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Shouhoubean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Shouhoubean data) {
                        mlist=data.getData().getList();
                        if(mlist.size()!=0){
                            refunAdapter.setList((ArrayList<Shouhoubean.DataBean.ListBean>)mlist);
                        }else {
                            ToastUtils.showToast("暂无数据");
                        }



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
