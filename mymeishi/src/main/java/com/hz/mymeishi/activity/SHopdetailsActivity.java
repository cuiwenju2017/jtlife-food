package com.hz.mymeishi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hz.mymeishi.R;

public class SHopdetailsActivity extends AppCompatActivity {


    private TextView left;
    private TextView title;
    private RelativeLayout title_bar;
    private ImageView icon;
    private TextView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetails);

    }


}
