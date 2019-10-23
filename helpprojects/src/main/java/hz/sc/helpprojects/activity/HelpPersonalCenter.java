package hz.sc.helpprojects.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
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
import com.base.commonlib.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hz.sc.helpprojects.R;
import hz.sc.helpprojects.helpfragment.HelpPersonalVideo;
import hz.sc.helpprojects.helpfragment.HelpPersonalbangz;
import hz.sc.helpprojects.helpfragment.HelpPersonalfraqiuzhu;
import hz.sc.helpprojects.helpfragment.HelpPersonalpraise;
import hz.sc.helpprojects.helpfragment.HelpgrAllPersons;

public class HelpPersonalCenter extends BaseActivity {

    private TabLayout helpgerentabLayout;
    private ViewPager helpgerentab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private LinearLayout lindongtai;
    private CircleImageView helpcircBInji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_personal_center);
        StatusBarUtil.getStatusBarHeight(HelpPersonalCenter.this);
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        StatusBarUtil.setTranslucentStatus(HelpPersonalCenter.this);//透明状态栏
        StatusBarUtil.setStatusBarColor(HelpPersonalCenter.this, Color.parseColor("#598EF9"));//设置背景颜色
        initView();
    }

    private void initView() {
        helpgerentabLayout =(TabLayout) findViewById(R.id.helpgerentabLayout);
        helpgerentab_vp = (ViewPager)findViewById(R.id.helpgerentab_vp);
        lindongtai = (LinearLayout)findViewById(R.id.lindongtai);
        helpcircBInji =(CircleImageView) findViewById(R.id.helpcircBInji);
        helpcircBInji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(HelpPersonalCenter.this,EditingPersonalData.class);
                startActivity(intent);
            }
        });
        titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("动态"+"26");
        titleList.add("视频"+"2");
        titleList.add("求助"+"2");
        titleList.add("帮助"+"2");
        fragmentList = new ArrayList<>();
        HelpgrAllPersons helpgrAllPersons=new HelpgrAllPersons();
        fragmentList.add(helpgrAllPersons);
        HelpPersonalpraise helpPersonalpraise=new HelpPersonalpraise();
        fragmentList.add(helpPersonalpraise);
        HelpPersonalVideo helpPersonalVideo=new HelpPersonalVideo();
        fragmentList.add(helpPersonalVideo);
        HelpPersonalfraqiuzhu helpPersonalfraqiuzhu=new HelpPersonalfraqiuzhu();
        fragmentList.add(helpPersonalfraqiuzhu);
        HelpPersonalbangz helpPersonalbangz=new HelpPersonalbangz();
        fragmentList.add(helpPersonalbangz);
        helpgerentab_vp.setAdapter(new MyFragmentAdpter(this.getSupportFragmentManager()));
        helpgerentabLayout.setupWithViewPager(helpgerentab_vp);
        helpgerentabLayout.setTabMode(TabLayout.MODE_FIXED);
        helpgerentabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorwaite)));
        lindongtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helpgerentab_vp.setCurrentItem(1);
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
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);//页卡标题
        }
    }
}
