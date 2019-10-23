package com.hz.coffeeshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.coffeeshop.R;
import com.hz.coffeeshop.view.BaseActivity;

public class Reservediancan extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservediancan);
        initView();
    }

    private void initView() {
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("预定");
    }
}
