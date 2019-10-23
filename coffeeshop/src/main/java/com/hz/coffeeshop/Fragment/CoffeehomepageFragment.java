package com.hz.coffeeshop.Fragment;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.base.commonlib.utils.StatusBarUtil;
import com.hz.coffeeshop.R;
import com.hz.coffeeshop.activity.CoffeConcerned;
import com.hz.coffeeshop.activity.CoffeeNewaddAddress;
import com.hz.coffeeshop.activity.CoffeemenuActivity;
import com.hz.coffeeshop.activity.CoffeperipheryActivity;
import com.hz.coffeeshop.activity.TakeawaysettlementAc;
import com.hz.coffeeshop.view.CoffeeStatusBarUtils;

import java.util.ArrayList;
import java.util.List;

public class CoffeehomepageFragment extends BaseFragment {


    private TabLayout tabLayout;
    private ViewPager tab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private LinearLayout menu_coffee_lin;
    private TextView textview_periphery;
    private LinearLayout makeanappointment;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coffeehomepage;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {

        CoffeeStatusBarUtils.with(getActivity())
                .init();
        initView(view);
    }

    private void initView(View view) {
        textview_periphery = (TextView)view.findViewById(R.id.textview_periphery);
        tabLayout =(TabLayout) view.findViewById(R.id.coffeetabLayout);
        tab_vp= (ViewPager)view.findViewById(R.id.coffeetab_vp);
        titleList = new ArrayList<>();
        titleList.add("今日特价");
        titleList.add("优惠套餐");
        titleList.add("菜品详情");
        fragmentList = new ArrayList<>();
        CoffetejiaFragment coffetejiaFragment=new CoffetejiaFragment();
        fragmentList.add(coffetejiaFragment);
        CoffeeDiscountpackage coffeeDiscountpackage=new CoffeeDiscountpackage();
        fragmentList.add(coffeeDiscountpackage);
        CoffeeDetailsofdishes coffeeDetailsofdishes=new CoffeeDetailsofdishes();
        fragmentList.add(coffeeDetailsofdishes);
        tab_vp.setAdapter(new MyFragmentAdpter(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(tab_vp);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorwaite)));
        menu_coffee_lin = (LinearLayout)view.findViewById(R.id.menu_coffee_lin);
        menu_coffee_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),CoffeemenuActivity.class);
                getActivity().startActivity(intent);
            }
        });
        textview_periphery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentperiphery=new Intent();
                intentperiphery.setClass(getActivity(),CoffeConcerned.class);
                getActivity().startActivity(intentperiphery);
            }
        });
        makeanappointment =(LinearLayout) view.findViewById(R.id.makeanappointment);
    }

    @Override
    public void fetchData() {

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
