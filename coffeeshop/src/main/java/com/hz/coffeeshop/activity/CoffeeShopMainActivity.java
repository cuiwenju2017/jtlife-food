package com.hz.coffeeshop.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.base.commonlib.utils.StatusBarUtil;
import com.hz.coffeeshop.Fragment.CoffecircleFragment;
import com.hz.coffeeshop.Fragment.CoffeeReleaseFragment;
import com.hz.coffeeshop.Fragment.CoffeeVideoFragment;
import com.hz.coffeeshop.Fragment.CoffeehomepageFragment;
import com.hz.coffeeshop.Fragment.Coffeemyfragment;
import com.hz.coffeeshop.R;
import com.hz.coffeeshop.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShopMainActivity extends BaseActivity {

    private RadioGroup rg;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_shop_main);


        initView();
        initDate();
    }
    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        vp = (ViewPager) findViewById(R.id.vp);

    }
    private void initDate() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CoffeehomepageFragment());
        fragments.add(new CoffecircleFragment());
        fragments.add(new CoffeeReleaseFragment());
        fragments.add(new CoffeeVideoFragment());
        fragments.add(new Coffeemyfragment());
        vp.setOffscreenPageLimit(0);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                vp.setCurrentItem(radioGroup.indexOfChild(radioGroup.findViewById(i)));
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //实现滑动页面下方按钮的联动
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

    }
}
