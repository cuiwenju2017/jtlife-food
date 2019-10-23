package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtils;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;

import java.util.HashMap;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.Dologin;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = "/mymeishinew/main")
public class ShjmeishiLogin extends BaseActivity implements View.OnClickListener {
    private TextView login_denlu;
    private EditText login_edit_account;
    private EditText login_edit_pwd;
    private TextView tv_register;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shjmeishi_login);
        initView();
    }
    private void initView() {

        login_denlu = findViewById(R.id.login_denlu);
        tv_register = findViewById(R.id.tv_register);
        login_denlu.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        //账号
        login_edit_account = (EditText) findViewById(R.id.login_edit_account);
        //密码
        login_edit_pwd = (EditText) findViewById(R.id.login_edit_pwd);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.login_denlu) {
            final String phone = login_edit_account.getText().toString();
            String pwd = login_edit_pwd.getText().toString();
            if (login_edit_account.length() != 11) {
                ToastUtils.showMessage(this, "请输入正确的手机号码");
            } else if ("".equals(pwd)) {
                ToastUtils.showMessage(this, "密码不能为空");
            } else {
                Intent intent=new Intent();
                intent.setClass(ShjmeishiLogin.this,MeiShiHomeActivity.class);
                startActivity(intent);
                loadingDialog = new LoadingDialog(this);
                Gson gson = new Gson();
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("platform", "0");
                paramsMap.put("userCode", phone);
                paramsMap.put("passWord", pwd);
                String info = gson.toJson(paramsMap);
                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                RxHttpUtils.createApi(GitHubService.class)
                        .regisbutton(body)
                        .compose(Transformer.<Dologin>switchSchedulers(loadingDialog))
                        .subscribe(new Observer<Dologin>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Dologin dologin) {
                                Log.d("ddsawq", dologin.getStatus());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

        } else if (i == R.id.tv_register) {
            Intent intent = new Intent();
            intent.setClass(ShjmeishiLogin.this, RegisterActivity.class);
            startActivity(intent);

        }
    }
}
