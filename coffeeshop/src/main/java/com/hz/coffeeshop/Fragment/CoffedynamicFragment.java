package com.hz.coffeeshop.Fragment;


import android.content.res.ColorStateList;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hz.coffeeshop.R;
import com.hz.coffeeshop.activity.CoffeConcerned;

import java.util.ArrayList;
import java.util.List;


public class CoffedynamicFragment extends BaseFragment {


    private TabLayout tabLayout;
    private ViewPager tab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coffedynamic;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
            initView(view);
    }

    private void initView(View view) {
        tabLayout = (TabLayout)view.findViewById(R.id.dongtaitabLayout);
        tab_vp = (ViewPager)view.findViewById(R.id.dongtaitab_vp);
        titleList = new ArrayList<>();
        titleList.add("动态");
        titleList.add("作品");
        fragmentList = new ArrayList<>();
        DtinDynamicsFragment dtinDynamicsFragment=new DtinDynamicsFragment();
        fragmentList.add(dtinDynamicsFragment);
        WorksFragment worksFragment=new WorksFragment();
        fragmentList.add(worksFragment);
        tab_vp.setAdapter(new MyFragmentAdpter(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(tab_vp);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorwaite)));
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
