package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.adapter.AdapterSppji;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.Sppjlistbean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SppjlistActivity extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private ListView listviewsppijia;
    private List<Sppjlistbean.DataBean.ListBean>date1;
    private String goddsspid;
    private LoadingDialog loadingDialog;
    private TextView sppjzongjia;
    private String spzj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();

        goddsspid = intent.getStringExtra("goddsspid");
        spzj = intent.getStringExtra("spzj");
        setContentView(R.layout.activity_sppjlist);
        initView();
        initDate();

    }



    private void initView() {
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title =(TextView) findViewById(R.id.tv_title);
        tv_title.setText("全部评价");
        listviewsppijia = (ListView)findViewById(R.id.listviewsppijia);
        sppjzongjia = (TextView)findViewById(R.id.sppjzongjia);
        sppjzongjia.setText("全部"+spzj);
    }
    private void initDate() {
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .sppjlist(1,goddsspid)
                .compose(Transformer.<Sppjlistbean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Sppjlistbean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Sppjlistbean sppjlistbean) {
                        date1=sppjlistbean.getData().getList();
                        AdapterSppji adapterSppji=new AdapterSppji(SppjlistActivity.this,date1);
                        listviewsppijia.setAdapter(adapterSppji);
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
