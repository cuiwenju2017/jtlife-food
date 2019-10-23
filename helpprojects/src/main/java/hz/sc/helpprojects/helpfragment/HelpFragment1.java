package hz.sc.helpprojects.helpfragment;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.ArrayList;
import java.util.List;

import hz.sc.helpprojects.R;


public class HelpFragment1 extends BaseFragment {


    private TabLayout tabLayout;
    private ViewPager tab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private HelpRecomFragment helpRecomFragment;
    private HelpfollowFragment helpfollowFragment;
    private RadioGroup rg;
    private ViewPager helptab_vp;
    private RadioButton helpcity;
    private RadioButton helpguanzhu;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_help_fragment1;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
            initView(view);
            initDate();
    }



    private void initView(View view) {
        helpguanzhu =(RadioButton) view.findViewById(R.id.helpguanzhu);
        helpguanzhu.setChecked(true);
        rg =(RadioGroup) view.findViewById(R.id.helpmyhomegadio);
        helptab_vp = (ViewPager)view.findViewById(R.id.helptab_vp);
        helpcity =(RadioButton) view.findViewById(R.id.helpcity);
        helpcity.setText("杭州");
    }
    private void initDate() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HelpfollowFragment());
        fragments.add(new HelpRecomFragment());
        fragments.add(new HelpUrbanChoice());
        helptab_vp.setOffscreenPageLimit(0);
        helptab_vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
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
                helptab_vp.setCurrentItem(radioGroup.indexOfChild(radioGroup.findViewById(i)));
            }
        });
        helptab_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    @Override
    public void fetchData() {

    }

}
