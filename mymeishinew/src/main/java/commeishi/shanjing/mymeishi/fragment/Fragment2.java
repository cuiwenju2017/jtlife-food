package commeishi.shanjing.mymeishi.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;



import java.util.ArrayList;
import java.util.List;

import commeishi.shanjing.mymeishi.R;

@SuppressLint("ValidFragment")
public class Fragment2 extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager tab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private int ye = 1;
    private WholeFragment wholeFragment;
    private PendingFragment pendingFragment;
    private  int shye;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
                case 1://接收消息进行数据库访问  获取数据


                    break;
            }
        }
    };









    @Override
    protected int setLayoutId() {
        return R.layout.fm_layout2;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initView(view);

    }

    private void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tab_vp = (ViewPager) view.findViewById(R.id.tab_vp);
        titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("待付款");
        titleList.add("已结算");
        fragmentList = new ArrayList<>();
        wholeFragment = new WholeFragment();
        fragmentList.add(wholeFragment);//全部
        pendingFragment = new PendingFragment();
        fragmentList.add(pendingFragment);//待付款
        fragmentList.add(new SetFragment());//已结算
        tab_vp.setAdapter(new MyFragmentAdpter(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(tab_vp);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorwaite)));

    }

    public void shuaXin(int i) {
        Log.d("qq11111",i+"");
        try {
            ye = i;
            wholeFragment.shuaXin(ye);
            pendingFragment.shuaXin(ye);

        } catch (Exception eww) {
        }
    }


    @Override
    public void fetchData() {
        Log.d("ssss", "222---------*********/////////");
        ye = 1;
        shuaXin(ye);
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("ddwwqq",isVisibleToUser+"");
        if(isVisibleToUser==true) {
            fetchData();
            return;
        }
    }
}
