package commeishi.shanjing.mymeishi.activity;

import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.fragment.Processingdetails;
import commeishi.shanjing.mymeishi.fragment.Refunddetails;
import commeishi.shanjing.mymeishi.fragment.Shouhouwanchen;

import java.util.ArrayList;
import java.util.List;

public class RefundActivity extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private TabLayout refuntabLayout;
    private ViewPager refuntab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private Refunddetails refunddetails;
    private Processingdetails processingdetails;
    private Shouhouwanchen shouhouwanchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        initView();
    }

    private void initView() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("售后");
        refuntabLayout = (TabLayout) findViewById(R.id.refuntabLayout);
        refuntab_vp = (ViewPager) findViewById(R.id.refuntab_vp);
        titleList = new ArrayList<>();
        titleList.add("售后申请");
        titleList.add("处理中");
        titleList.add("已完成");
        fragmentList = new ArrayList<>();
        refunddetails = new Refunddetails();
        processingdetails = new Processingdetails();
        shouhouwanchen = new Shouhouwanchen();
        fragmentList.add(refunddetails);
        fragmentList.add(processingdetails);
        fragmentList.add(shouhouwanchen);
        refuntab_vp.setAdapter(new MyFragmentAdpter(this.getSupportFragmentManager()));
        refuntabLayout.setupWithViewPager(refuntab_vp);
        refuntabLayout.setTabMode(TabLayout.MODE_FIXED);
        refuntabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorwaite)));
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
