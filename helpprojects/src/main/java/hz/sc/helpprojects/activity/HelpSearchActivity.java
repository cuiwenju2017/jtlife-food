package hz.sc.helpprojects.activity;

import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.base.commonlib.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import hz.sc.helpprojects.R;
import hz.sc.helpprojects.helpfragment.HelpRecomFragment;
import hz.sc.helpprojects.helpfragment.HelpSearchGoodfriend;
import hz.sc.helpprojects.helpfragment.HelpSearchRecom;
import hz.sc.helpprojects.helpfragment.HelpfollowFragment;

public class HelpSearchActivity extends BaseActivity {

    private TabLayout helpsearchtabLayout;
    private ViewPager helpsearchtab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private LinearLayout Serchifshin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_search);
        initView();
    }

    private void initView() {
        Serchifshin =(LinearLayout) findViewById(R.id.Serchifshin);
        Serchifshin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        helpsearchtabLayout =(TabLayout) findViewById(R.id.helpsearchtabLayout);
        helpsearchtab_vp = (ViewPager)findViewById(R.id.helpsearchtab_vp);
        titleList = new ArrayList<>();
        titleList.add("推荐");
        titleList.add("好友");

        fragmentList = new ArrayList<>();
        HelpSearchRecom helpSearchRecom=new HelpSearchRecom();
        fragmentList.add(helpSearchRecom);
        HelpSearchGoodfriend searchGoodfriend=new HelpSearchGoodfriend();
        fragmentList.add(searchGoodfriend);
        helpsearchtab_vp.setAdapter(new MyFragmentAdpter(this.getSupportFragmentManager()));
        helpsearchtabLayout.setupWithViewPager(helpsearchtab_vp);
        helpsearchtabLayout.setTabMode(TabLayout.MODE_FIXED);
        helpsearchtabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorwaite)));
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
