package com.hz.mymeishi.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.hz.mymeishi.R;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.model.Dologin;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
@Route(path = "/mymeishi/main")
public class Loginmymeishi extends BaseActivity implements View.OnClickListener{
    private TextView login_denlu;
    private EditText login_edit_account;
    private EditText login_edit_pwd;
    private TextView tv_register;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmymeishi);
        SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", true);
        if (!isLogin) {
            Intent intent = new Intent(Loginmymeishi.this, MeiShiHomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            initView();
        }
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
                                String status = dologin.getStatus();
                                if(status.equals("1")){
                                    SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                                    editor.putBoolean("isLogin", false);//登录成功之后保存登录状态下次不用重新登录
                                    editor.commit();
                                    //跳转
                                    Intent intent=new Intent();
                                    intent.setClass(Loginmymeishi.this,MeiShiHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    com.base.commonlib.ToastUtils.showToast("账号或密码失败");
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

        } else if (i == R.id.tv_register) {
            Intent intent = new Intent();
            intent.setClass(Loginmymeishi.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
