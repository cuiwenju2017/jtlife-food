package com.hz.coffeeshop.activity;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.base.commonlib.utils.StatusBarUtil;
import com.hz.coffeeshop.Fragment.CoffedynamicFragment;
import com.hz.coffeeshop.Fragment.GuanzhucircleFragment;
import com.hz.coffeeshop.Fragment.GuanzhufollowFragment;
import com.hz.coffeeshop.R;
import com.hz.coffeeshop.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CoffeConcerned extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager tab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffe_concerned);
        StatusBarUtil.setTranslucentStatus(CoffeConcerned.this);
        initView();
    }

    private void initView() {
        tabLayout = (TabLayout)findViewById(R.id.yiguanzhutabLayout);
        tab_vp = (ViewPager)findViewById(R.id.yiguanzhutab_vp);
        titleList = new ArrayList<>();
        titleList.add("动态");
        titleList.add("关注");
        titleList.add("圈");
        fragmentList = new ArrayList<>();
        CoffedynamicFragment coffedynamicFragment=new CoffedynamicFragment();
        fragmentList.add(coffedynamicFragment);
        GuanzhufollowFragment guanzhufollowFragment=new GuanzhufollowFragment();
        fragmentList.add(guanzhufollowFragment);
        GuanzhucircleFragment guanzhucircleFragment=new GuanzhucircleFragment();
        fragmentList.add(guanzhucircleFragment);
        tab_vp.setAdapter(new MyFragmentAdpter(this.getSupportFragmentManager()));
        tabLayout.setupWithViewPager(tab_vp);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorwaite)));

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
