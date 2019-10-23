package com.hz.coffeeshop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hz.coffeeshop.R;
import com.hz.coffeeshop.view.BaseActivity;
import com.hz.coffeeshop.view.NumPicker;

public class CoffeemenuActivity extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private ImageView righr_imageview;
    private LinearLayout lin_numrenshu;


    private TextView textview_numbre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffeemenu);
        initView();
    }

    private void initView() {
        textview_numbre = (TextView)findViewById(R.id.textview_numbre);

        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("菜单");
        righr_imageview =(ImageView) findViewById(R.id.coffee_righr_imageview);
        righr_imageview.setBackgroundResource(R.drawable.fenxiang_coffeeright);
        lin_numrenshu =(LinearLayout) findViewById(R.id.lin_numrenshu);
        final NumPicker np = new NumPicker(CoffeemenuActivity.this);
        np.setOnCancelListener(new NumPicker.OnCancelClickListener() {
            @Override
            public void onClick() {
                np.dismiss();
            }
        });
        np.setOnComfirmListener(new NumPicker.onComfirmClickListener() {
            @Override
            public void onClick(int num) {
                textview_numbre.setText(num+"人");
                np.dismiss();
            }
        });
        lin_numrenshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                np.show();
            }
        });
    }

}
