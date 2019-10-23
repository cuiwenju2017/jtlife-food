package com.hz.coffeeshop.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.hz.coffeeshop.R;
import com.hz.coffeeshop.adapter.TitleFragmentAdapter;
import com.hz.coffeeshop.bean.MyChannel;
import com.hz.coffeeshop.util.GsonUtil;
import com.hz.coffeeshop.view.BaseActivity;
import com.trs.channellib.channel.channel.helper.ChannelDataHelepr;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 周边
 */
public class CoffeperipheryActivity extends BaseActivity implements ChannelDataHelepr.ChannelDataRefreshListenter {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private ImageView righr_imageview;

    View switch_view;
    ViewPager viewPager;
    TitleFragmentAdapter adapter;
    List<MyChannel> myChannels;
    ChannelDataHelepr<MyChannel> dataHelepr;
    private XTabLayout tab;
    private int needShowPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffeperiphery);
        initview();
    }

    private void initview() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("周边 ");
        righr_imageview = (ImageView) findViewById(R.id.coffee_righr_imageview);
        righr_imageview.setBackgroundResource(R.drawable.meishiicon_search);
        righr_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(CoffeperipheryActivity.this, PeripheralSearch.class);
                startActivity(intent);
            }
        });

        tab = findViewById(R.id.tab);
        switch_view = findViewById(R.id.iv_subscibe);
        viewPager = findViewById(R.id.viewpager);
        dataHelepr = new ChannelDataHelepr(this, this, findViewById(R.id.tab_layout));
        dataHelepr.setSwitchView(switch_view);
        myChannels = new ArrayList<>();
        adapter = new TitleFragmentAdapter(getSupportFragmentManager(), myChannels);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        loadData();

    }

    @Override
    public void updateData() {
        loadData();
    }

    @Override
    public void onChannelSeleted(boolean update, final int posisiton) {
        if (!update) {
            viewPager.setCurrentItem(posisiton);
        } else {
            needShowPosition = posisiton;
        }

    }


    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String data = getFromRaw();
                List<MyChannel> alldata = GsonUtil.jsonToBeanList(data, MyChannel.class);
                final List<MyChannel> showChannels = dataHelepr.getShowChannels(alldata);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myChannels.clear();
                        myChannels.addAll(showChannels);
                        adapter.notifyDataSetChanged();
                        if (needShowPosition != -1) {
                            viewPager.setCurrentItem(needShowPosition);
                            needShowPosition = -1;
                        }
                    }
                });

            }
        }).start();
    }

    private String getFromRaw() {
        String result = "";
        try {
            InputStream input = getResources().openRawResource(R.raw.news_list);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.close();
            input.close();

            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
