package commeishi.shanjing.mymeishi.activity;

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

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.adapter.MessageNotificationAdapter;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.bean.MessageNotificationBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 消息通知
 */
public class MessageNotificationActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private LoadingDialog loadingDialog;
    private SmartRefreshLayout srl;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_notification);
        initView();
        initData();
    }

    private List<MessageNotificationBean.DataBean.ListBean> list = new ArrayList<>();
    private MessageNotificationAdapter messageNotificationAdapter;

    private void initData() {
        tv_title.setText("消息通知");
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .getMessageNotification(1, 1)
                .compose(Transformer.<MessageNotificationBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<MessageNotificationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(final MessageNotificationBean messageBean) {
                        loadingDialog.dismiss();
                        list = messageBean.getData().getList();

                        //设置布局管理器
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                        rv.setLayoutManager(linearLayoutManager);
                        messageNotificationAdapter = new MessageNotificationAdapter(MessageNotificationActivity.this, list);
                        rv.setAdapter(messageNotificationAdapter);
                        //item点击事件
                        messageNotificationAdapter.setOnitemClickLintener(new MessageNotificationAdapter.OnitemClick() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(MessageNotificationActivity.this,OrderCenterOrderMessage.class);
                                intent.putExtra("orderID",messageBean.getData().getList().get(position).getSourceId());
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(MessageNotificationActivity.this, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {
        rl_back = findViewById(R.id.rl_back);
        tv_title = findViewById(R.id.tv_title);
        srl = findViewById(R.id.srl);
        rv = findViewById(R.id.rv);
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
