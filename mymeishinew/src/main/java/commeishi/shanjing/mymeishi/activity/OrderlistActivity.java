package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.fragment.Collectinggoods;
import commeishi.shanjing.mymeishi.fragment.CompletedFragment;
import commeishi.shanjing.mymeishi.fragment.PaymentFragment;
import commeishi.shanjing.mymeishi.fragment.ShipFragment;
import commeishi.shanjing.mymeishi.fragment.WholeFragment;

import java.util.ArrayList;
import java.util.List;
public class OrderlistActivity extends BaseActivity {

    private TabLayout listtabLayout;
    private ViewPager listtab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private TextView tv_title;
    private RelativeLayout rl_back;
    private PaymentFragment paymentFragment;
    private ShipFragment shipFragment;
    private Collectinggoods collectinggoods;
    private CompletedFragment completedFragment;
    private WholeFragment wholeFragment;
    private String biashi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        Intent intent = getIntent();
        biashi = intent.getStringExtra("dfw");//标记
        Log.d("ddsad",biashi);
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
        tv_title.setText("订单列表");
        listtabLayout = (TabLayout)findViewById(R.id.listtabLayout);
        listtab_vp = (ViewPager)findViewById(R.id.listtab_vp);
        titleList = new ArrayList<>();

        titleList.add("全部");
        titleList.add("待付款");
        titleList.add("待发货");
        titleList.add("待收货");
        titleList.add("已完成");
        fragmentList = new ArrayList<>();
        wholeFragment = new WholeFragment();
        paymentFragment = new PaymentFragment();
        shipFragment = new ShipFragment();
        collectinggoods = new Collectinggoods();
        completedFragment = new CompletedFragment();
        fragmentList.add(wholeFragment);
        fragmentList.add(paymentFragment);
        fragmentList.add(shipFragment);
        fragmentList.add(collectinggoods);
        fragmentList.add(completedFragment);

        listtab_vp.setAdapter(new MyFragmentAdpter(OrderlistActivity.this.getSupportFragmentManager()));
        listtabLayout.setupWithViewPager(listtab_vp);
        listtabLayout.setTabMode(TabLayout.MODE_FIXED);
        listtabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorwaite)));
        if(biashi.equals("1")){
            listtab_vp.setCurrentItem(1);
        }else if(biashi.equals("3")){
            listtab_vp.setCurrentItem(3);
        }else if(biashi.equals("4")){
            listtab_vp.setCurrentItem(4);
        }
    }

    public class MyFragmentAdpter extends FragmentPagerAdapter {

        public MyFragmentAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);//页卡标题
        }
    }
}
