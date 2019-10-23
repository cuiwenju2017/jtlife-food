package com.hz.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.hz.mymeishi.R;

public class GetAddressActivity extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private TextView suozaidiqu;
    private String streess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);
        Intent intent=getIntent();
        streess = intent.getStringExtra("streess");
        initView();
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
        tv_title.setText("新增");
        suozaidiqu =(TextView) findViewById(R.id.suozaidiqu);
        suozaidiqu.setText(streess);
        suozaidiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(GetAddressActivity.this,MapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
