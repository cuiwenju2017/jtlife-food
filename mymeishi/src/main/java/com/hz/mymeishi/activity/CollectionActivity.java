package com.hz.mymeishi.activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.view.LoadingDialog;
import com.hz.mymeishi.R;
import com.hz.mymeishi.adapter.CollectAdapter;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.model.Collscbean;

import java.util.ArrayList;
import java.util.List;
public class CollectionActivity extends BaseActivity {

    private RecyclerView collecrecyclview;
    private LoadingDialog loadingDialog;
    private List<Collscbean.ListBean> mlist;
    private CollectAdapter collectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        inDate();
        initzhans();

    }




    private void initView() {
        loadingDialog = new LoadingDialog(this);
        collecrecyclview = (RecyclerView)findViewById(R.id.collecrecyclview);


    }
    private void initzhans() {

        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        collecrecyclview.setLayoutManager(linearLayoutManager);
        collectAdapter = new CollectAdapter(this);
        collecrecyclview.setAdapter(collectAdapter);
    }

    private void inDate() {

        String type="0";
        RxHttpUtils
                .createApi(GitHubService.class)
                .calletsc(1,type)
                .compose(Transformer.<BaseData<Collscbean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Collscbean>() {

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Collscbean data) {
                        mlist=data.getList();
                        collectAdapter.setList((ArrayList<Collscbean.ListBean>)mlist);

                    }
                });

    }
}
