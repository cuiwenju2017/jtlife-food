package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.GetAddressbean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NewAddressActivity extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private ListView newaddlist;
    private LoadingDialog loadingDialog;
    private TextView receiveName;
    private TextView receiveTel;
    private TextView sysAreaName;
    private TextView addressDetail;
    private Button getnewaddress_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        initView();
        getmoraddress();
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
        tv_title.setText("地址管理");
        receiveName =(TextView) findViewById(R.id.receiveName);
        receiveTel =(TextView) findViewById(R.id.receiveTel);
        sysAreaName = (TextView)findViewById(R.id.sysAreaName);
        addressDetail =(TextView) findViewById(R.id.addressDetail);
        getnewaddress_but = (Button)findViewById(R.id.getnewaddress_but);
        getnewaddress_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(NewAddressActivity.this,GetAddressActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getmoraddress() {
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils.createApi(GitHubService.class)
                .getaddress()
                .compose(Transformer.<GetAddressbean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<GetAddressbean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final GetAddressbean getAddressbean) {
                        receiveName.setText(getAddressbean.getData().getReceiveName());
                        receiveTel.setText(getAddressbean.getData().getReceiveTel());
                        sysAreaName.setText(getAddressbean.getData().getSysAreaName());
                        addressDetail.setText(getAddressbean.getData().getAddressDetail());
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
