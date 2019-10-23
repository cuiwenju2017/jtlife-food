package com.hz.meishi.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.URLS;
import com.base.commonlib.utils.ToastUtils;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hz.meishi.R;
import com.hz.meishi.bean.BaseBean;
import com.hz.meishi.bean.RegisterBean;
import com.hz.meishi.utils.AniDialog;


import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button login_denlu;
    private EditText login_edit_account;
    private EditText login_edit_pwd;
    private TextView tv_register;
    private AniDialog aniDialog;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingDialog = new LoadingDialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", true);
        if (!isLogin) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
            getlogin();
        } else if (i == R.id.tv_register) {
//            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private void getlogin() {
        final String phone = login_edit_account.getText().toString();
        String pwd = login_edit_pwd.getText().toString();
        if (login_edit_account.length() != 11) {
            ToastUtils.showMessage(this, "请输入正确的手机号码");
        } else if ("".equals(pwd)) {
            ToastUtils.showMessage(this, "密码不能为空");
        } else {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(URLS.Host)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build();
  //          GitHubService gitHubService = retrofit.create(GitHubService.class);
//            retrofit2.Call<> call = gitHubService.getCall();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            final Gson gson = new Gson();
            final HashMap<String, String> requestBody = new HashMap<>();
            requestBody.put("platform", "0");
            requestBody.put("userCode", phone);
            requestBody.put("passWord", pwd);
            final Request request = new Request.Builder()
                    .url(URLS.Hostqq + "/Member/DoLogin")
                    .post(RequestBody.create(mediaType, gson.toJson(requestBody)))
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient();
            aniDialog.show();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("aaa", "onFailure: 失败" + e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    aniDialog.dismiss();
                    java.lang.reflect.Type type = new TypeToken<BaseBean<RegisterBean>>() {
                    }.getType();
                    BaseBean<RegisterBean> registerBean = gson.fromJson(response.body().toString(), type);

                    Log.d("dddsad",registerBean.getStatus());

                    if ("1".equals(registerBean.getStatus())) {
                        //获取sharedPreferences对象
                        SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                        //获取editor对象
                        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                        editor.putBoolean("isLogin", false);//登录成功之后保存登录状态下次不用重新登录
                        editor.putString("access_Token", registerBean.getData().getAccess_Token());
                        editor.putString("userCode", registerBean.getData().getUserCode());
                        editor.commit();//提交修改
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //回调函数是在子线程中进行，而Toast是在主线程中进行，所以要在子线程中显示toast要在toast
                        //前加Looper.prepare();后加Looper.loop();
                        Looper.prepare();
                        ToastUtils.showMessage(LoginActivity.this, "" + registerBean.getErrorMsg());
                        Looper.loop();

                    }

                }
            });
        }


    }
}
