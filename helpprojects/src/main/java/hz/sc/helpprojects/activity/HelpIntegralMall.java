package hz.sc.helpprojects.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import hz.sc.helpprojects.R;
import hz.sc.helpprojects.helpfragment.HelpMyjfintegral;
import hz.sc.helpprojects.helpfragment.HelpRecomFragment;
import hz.sc.helpprojects.helpfragment.HelpUrbanChoice;
import hz.sc.helpprojects.helpfragment.HelpfollowFragment;
import hz.sc.helpprojects.helpfragment.HelpjlExchangerecord;
import hz.sc.helpprojects.helpfragment.HelpmyShcart;
import hz.sc.helpprojects.view.CustomRadioButton;

public class HelpIntegralMall extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private RadioGroup rg;
    private ViewPager helptab_vp;
    private CustomRadioButton custom_jfbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_ma);
        initView();
        inData();
    }



    private void initView() {
        custom_jfbutton =(CustomRadioButton) findViewById(R.id.custom_jfbutton);
        custom_jfbutton.setChecked(true);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("积分商城");
        rg = (RadioGroup)findViewById(R.id.myhelpjfsc);
        helptab_vp =(ViewPager) findViewById(R.id.helpviewpagerjfsc);

    }
    private void inData() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HelpMyjfintegral());
        fragments.add(new HelpjlExchangerecord());
        fragments.add(new HelpmyShcart());
        helptab_vp.setOffscreenPageLimit(0);
        helptab_vp.setAdapter(new FragmentPagerAdapter(HelpIntegralMall.this.getSupportFragmentManager()) {
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
}
