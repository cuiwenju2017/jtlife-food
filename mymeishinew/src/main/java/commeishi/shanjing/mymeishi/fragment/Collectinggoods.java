package commeishi.shanjing.mymeishi.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.view.LoadingDialog;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.adapter.OrdercenterAdapter;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.bean.Wholebeandd;

import java.util.ArrayList;
import java.util.List;


public class Collectinggoods extends BaseFragment {

    private RecyclerView mListView;
    private LoadingDialog loadingDialog;
    private List<Wholebeandd.ListBean> mlist;
    private OrdercenterAdapter ordercenterAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_collectinggoods;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initView(view);
        initDSS();
    }

    private void initView(View view) {
        mListView = (RecyclerView) view.findViewById(R.id.dsCillecrecyc);
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
        mListView.setLayoutManager(linearLayoutManager);
        ordercenterAdapter = new OrdercenterAdapter(getActivity());
        mListView.setAdapter(ordercenterAdapter);
    }

    @Override
    public void fetchData() {
        loadingDialog = new LoadingDialog(getActivity());
        String type="3";
        RxHttpUtils
                .createApi(GitHubService.class)
                .orderqb(1,type)
                .compose(Transformer.<BaseData<Wholebeandd>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Wholebeandd>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }
                    @Override
                    protected void onSuccess(Wholebeandd data) {

                        loadingDialog.dismiss();
                        mlist=data.getList();
                        ordercenterAdapter.setList((ArrayList<Wholebeandd.ListBean>)mlist);

                    }
                });

    }
}
