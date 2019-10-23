package commeishi.shanjing.mymeishi.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.adapter.AnimalAdapter;
import commeishi.shanjing.mymeishi.adapter.OrdercenterAdapter;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.bean.Wholebeandd;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WholeFragment extends BaseFragment  {

    private RecyclerView mListView;
    private SmartRefreshLayout mRefresh;
    private AnimalAdapter mAdapter = null;
    private Context mContext;
    private int ye = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
                case 1://接收消息进行数据库访问  获取数据
                        Log.d("ssss","++++++222222");

                    break;
            }
        }
    };
    private TextView txt_aSpeak;
    private LoadingDialog loadingDialog;
    private  List<Wholebeandd.ListBean>mlist;
    private OrdercenterAdapter ordercenterAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_whole;
    }
    public void shuaXin(int i){
        Log.d("-----","i = " + i);
        ye = i;
        fetchData();
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
            initView(view);
            indate();
            initDSS();

    }
    private void initView(View view) {

        loadingDialog = new LoadingDialog(getActivity());
        mListView = (RecyclerView)view.findViewById(R.id.goods_fragment_listview);
        mRefresh =(SmartRefreshLayout) view.findViewById(R.id.refresh);
    }
    private void initDSS() {
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
        mContext = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext) {
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
         ordercenterAdapter = new OrdercenterAdapter(mContext);
        mListView.setAdapter(ordercenterAdapter);

    }
    private void indate() {
        String type="0";
        RxHttpUtils
                .createApi(GitHubService.class)
                .orderqb(1,type)
                .compose(Transformer.<BaseData<Wholebeandd>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Wholebeandd>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Log.d("ddds2",errorMsg+"+++++++++++++++++++---------");
                    }
                    @Override
                    protected void onSuccess(Wholebeandd data) {
                        loadingDialog.dismiss();
                        mlist=data.getList();
                        ordercenterAdapter.setList((ArrayList<Wholebeandd.ListBean>)mlist);
                        ordercenterAdapter.updatadingd(new OrdercenterAdapter.UpdataMessage() {
                                @Override
                                public void updata(boolean message) {
                                    if(message){
                                      indate();
                                      Log.d("ddsad",message+"");
                                    }
                                }
                            });
                    }
                });


    }

    @Override
    public void fetchData() {
        handler.sendEmptyMessage(1);
        Log.d("ssss","fragment_whole---------*********/////////");
    }


}
