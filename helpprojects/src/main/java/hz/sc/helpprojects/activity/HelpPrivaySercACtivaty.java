package hz.sc.helpprojects.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import hz.sc.helpprojects.R;
import hz.sc.helpprojects.helpfragment.HelpPrivamyRecom;
import hz.sc.helpprojects.helpfragment.HelpPrivamyfriend;

public class HelpPrivaySercACtivaty extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager tab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private LinearLayout lin_finsh_privacys;
    private TextView checkbox_lianxiren;
    private HelpPrivamyfriend helpPrivamyfriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_privay_serc_activaty);
        initView();
    }

    private void initView() {
        checkbox_lianxiren = (TextView)findViewById(R.id.checkbox_lianxiren);
        tabLayout = (TabLayout) findViewById(R.id.help_peivavtabLayout);
        tab_vp = (ViewPager)findViewById(R.id.help_peivavtab_vp);
        titleList = new ArrayList<>();
        titleList.add("我的好友");
        titleList.add("我关注的");
        fragmentList = new ArrayList<>();
        helpPrivamyfriend = new HelpPrivamyfriend(checkbox_lianxiren);
        fragmentList.add(helpPrivamyfriend);
        HelpPrivamyRecom helpPrivamyRecom=new HelpPrivamyRecom();
        fragmentList.add(helpPrivamyRecom);
        tab_vp.setAdapter(new MyFragmentAdpter(this.getSupportFragmentManager()));
        tabLayout.setupWithViewPager(tab_vp);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorwaite)));
        lin_finsh_privacys = (LinearLayout)findViewById(R.id.lin_finsh_privacys);
        lin_finsh_privacys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
        public CharSequence getPageTitle(int position)  {
            return titleList.get(position);//页卡标题
        }
    }




}
