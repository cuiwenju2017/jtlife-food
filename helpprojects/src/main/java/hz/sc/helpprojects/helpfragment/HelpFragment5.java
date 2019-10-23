package hz.sc.helpprojects.helpfragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import hz.sc.helpprojects.R;

import static android.content.Context.MODE_PRIVATE;


public class HelpFragment5 extends BaseFragment {

        private SharedPreferences sp;
        private TabLayout mygerentab;
        private ViewPager gerenmypager;
        private ImageView add;
        private List<Fragment> list=new ArrayList<>();
        private String Tabdata="TabJson";
        private List<ChannelBean>tabListAll=new ArrayList<>();
        private List<ChannelBean>tabList;
        private String jsontr;
        private List<ChannelBean> listAll;

        @Override
        protected int setLayoutId() {
            return R.layout.fragment_help_fragment5;
        }

        @Override
        protected void init(View view, Bundle savedInstanceState) {
                initView(view);
        }

        private void initView(View view) {
            mygerentab = (TabLayout)view.findViewById(R.id.mygerentab);
            gerenmypager = (ViewPager)view.findViewById(R.id.gerenmypager);
            add = (ImageView)view.findViewById(R.id.add);
            sp=getActivity().getSharedPreferences(Tabdata,MODE_PRIVATE);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadData();
                    jsontr=sp.getString(Tabdata,null);
                    if (jsontr==null){
                        ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(),tabList);
                    }else {
                        ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(),jsontr);
                    }
                }
            });
            loadData();
        }

        @Override
        public void fetchData() {

        }
    private void loadData() {
        add();
        String str=sp.getString(Tabdata,null);
        if (str==null){
            for (int i=0;i<myDataList.size();i++){
                list.add(new Fragment_content());
                tabList.add(myDataList.get(i));
            }
        }else {
            listAll = new Gson().fromJson(str,new TypeToken<List<ChannelBean>>(){
            }.getType());
            for (int i = 0; i< listAll.size(); i++){
                if (listAll.get(i).isSelect())
                list.add(new Fragment_content());
                tabList.add(new ChannelBean(listAll.get(i).getName(),true));

            }
        }
        MyAdapter adapter = new MyAdapter(getActivity().getSupportFragmentManager());
        gerenmypager.setAdapter(adapter);
        mygerentab.setupWithViewPager(gerenmypager);//和viewpager关联
        if (str==null){
            for (int i=0;i<tabList.size();i++){
                mygerentab.getTabAt(i).setText(tabList.get(i).getName());

            }
        }else{
            List<ChannelBean>listall=new Gson().fromJson(str,new TypeToken<List<ChannelBean>>(){}.getType());
            for (int i=0;i<listall.size();i++){
                if (listall.get(i).isSelect())
                    mygerentab.getTabAt(i).setText(listall.get(i).getName());
            }
        }

    }
    private List<ChannelBean> myDataList;
    //给tablist添加标题
    private void add() {
        tabList = new ArrayList<>();
        myDataList = new ArrayList<>();
        myDataList.add(new ChannelBean("推荐",true));
        myDataList.add(new ChannelBean("热点",true));
        myDataList.add(new ChannelBean("北京",true));
        myDataList.add(new ChannelBean("社会",true));
        myDataList.add(new ChannelBean("头条",true));
        myDataList.add(new ChannelBean("看点",true));
        myDataList.add(new ChannelBean("关注",false));
        myDataList.add(new ChannelBean("育儿",false));
        myDataList.add(new ChannelBean("体育",true));
        myDataList.add(new ChannelBean("健康",false));
        myDataList.add(new ChannelBean("育儿",false));
        myDataList.add(new ChannelBean("体育",false));
        myDataList.add(new ChannelBean("购物",false));
    }

    class  MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Fragment_content();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE){
            String jsontr=data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            Log.e("requestcode","requescode="+jsontr);
            //保存
            sp.edit().putString(Tabdata,jsontr).commit();
            myDataList.clear();
            tabList.clear();
            list.clear();

            loadData();
        }
    }
    }
