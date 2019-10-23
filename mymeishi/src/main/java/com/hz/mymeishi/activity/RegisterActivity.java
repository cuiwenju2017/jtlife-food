package com.hz.mymeishi.activity;




import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtils;

import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.hz.mymeishi.R;
import com.hz.mymeishi.View.CountDownTimerUtils;
import com.hz.mymeishi.api.GitHubService;
import com.hz.mymeishi.model.Dologin;
import com.hz.mymeishi.model.Registbean;


import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 注册
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_time;
    private EditText et_phone, et_phone_code, et_pwd, et_pwd_again;
    private Button btn_register;
    private LoadingDialog loadingDialog;
    private RelativeLayout rl_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }


    private void initView() {
        tv_time = (TextView) findViewById(R.id.tv_time);
        et_phone =(EditText) findViewById(R.id.et_phone);
        btn_register = (Button) findViewById(R.id.btn_register);
        et_phone_code =(EditText) findViewById(R.id.et_phone_code);
        et_pwd_again = (EditText) findViewById(R.id.et_pwd_again);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_time.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("注册");
    }

    @Override
    public void onClick(View v) {
        loadingDialog = new LoadingDialog(this);
        String etp = et_pwd.getText().toString();
        String etpa = et_pwd_again.getText().toString();
        String phone = et_phone.getText().toString();
        String code = et_phone_code.getText().toString();
        int i = v.getId();
        if (i == R.id.tv_time) {//获取验证码
            if ("".equals(phone) || phone.length() != 11) {
                ToastUtils.showMessage(this, "请输入正确的手机号码");
            } else {
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tv_time, 60000, 1000);
                mCountDownTimerUtils.start();
                RxHttpUtils
                        .createApi(GitHubService.class)
                        .regiszc(phone,0)
                        .compose(Transformer.<Registbean>switchSchedulers(loadingDialog))
                        .subscribe(new Observer<Registbean>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Registbean registbean) {
                                Log.d("ddsadd",registbean.toString());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        } else if (i == R.id.btn_register) {//注册
            if (et_phone.length() != 11) {
                ToastUtils.showMessage(this, "请输入正确的手机号码");
            } else if (et_phone_code.length() != 6) {
                ToastUtils.showMessage(this, "请输入正确的短信验证码");
            } else if ("".equals(etp) || "".equals(etpa)) {
                ToastUtils.showMessage(this, "密码或确认密码不能为空");
            } else if (!etp.equals(etpa)) {
                ToastUtils.showMessage(this, "两次输入的密码不一样");
            } else {//发送数据进行注册
                Gson gson = new Gson();
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("platform", "0");
                paramsMap.put("userCode", phone);
                paramsMap.put("smsCode", code);
                paramsMap.put("passWord", etp);
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
                                Log.d("ddssaaxxx",dologin.getData().getAccess_Token());
                                SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                                editor.putBoolean("isLogin", false);//登录成功之后保存登录状态下次不用重新登录
                                editor.putString("access_Token", dologin.getData().getAccess_Token());
                                editor.commit();
                                //跳转
                                Intent intent=new Intent();
                                intent.setClass(RegisterActivity.this,MeiShiHomeActivity.class);
                                startActivity(intent);
                                finish();
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
    }
}
