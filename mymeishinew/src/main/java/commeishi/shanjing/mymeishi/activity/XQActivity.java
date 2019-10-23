package commeishi.shanjing.mymeishi.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.base.commonlib.BaseActivity;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.View.PinnedHeaderListView;
import commeishi.shanjing.mymeishi.adapter.LeftListAdapter;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 详情页
 */

public class XQActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private Banner banner;
    private CircleImageView civ_head;
    private ImageView iv_phone;

    ListView leftListview;
    PinnedHeaderListView pinnedListView;
    private boolean isScroll = true;
    private LeftListAdapter adapter;
    private String[] leftStr = new String[]{"拼团", "折扣", "一元购", "限时抢购", "菜包", "汤包"};
    private boolean[] flagArray = {true, false, false, false, false, false};
    private String[][] rightStr = new String[][]{{"肉菜包", "韭菜包", "汤包"},
            {"奶油包", "红烧排骨", "农家小炒肉"},
            {"芝士", "丑小丫", "金枪鱼"},
            {"羊肉串", "烤鸡翅", "烤羊排"},
            {"长城干红", "燕京鲜啤", "青岛鲜啤"},
            {"拌粉丝", "大拌菜", "菠菜花生"}
    };

    private ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        ButterKnife.bind(this);
        initView();
        initData();
        // 首先获取到意图对象
        Intent intent = getIntent();
        // 获取到传递过来的姓名
        String title = intent.getStringExtra("title");
        tv_title.setText(title);
        //设置ScrollView回到顶部，否则会显示List顶部
        sv.smoothScrollTo(0, 0);
    }

    private void initData() {
        /**
         * banner轮播图
         */
        int[] imageResouceID = new int[]{R.drawable.pic, R.drawable.pic,
                R.drawable.pic, R.drawable.pic};//定义图片数组
        List<Integer> imageList = new ArrayList<>();
        for (int i = 0; i < imageResouceID.length; i++) {
            imageList.add(imageResouceID[i]);//把图片放入List中
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(XQActivity.this).load(path).into(imageView);
                }
            });
            banner.setImages(imageList);//设置图片资源
            banner.start();//开始进行banner渲染
            banner.stopAutoPlay();//停止自动轮播
            //banner.setDelayTime(3000);//设置轮播时间
            //banner.startAutoPlay();//开始自动轮播
        }

        /*final MainSectionedAdapter sectionedAdapter = new MainSectionedAdapter(XQActivity.this, leftStr, rightStr);
        pinnedListView.setAdapter(sectionedAdapter);*/
       /* adapter = new LeftListAdapter(XQActivity.this, leftStr, flagArray);
        leftListview.setAdapter(adapter);*/


        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;

                for (int i = 0; i < leftStr.length; i++) {
                    if (i == position) {
                        flagArray[i] = true;
                    } else {
                        flagArray[i] = false;
                    }
                }
                adapter.notifyDataSetChanged();
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    //rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                pinnedListView.setSelection(rightSection);
            }
        });

        /**
         * 左侧分类上下滑动监听
         */
        leftListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        pinnedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (pinnedListView.getLastVisiblePosition() == (pinnedListView.getCount() - 1)) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }
                        // 判断滚动到顶部
                        if (pinnedListView.getFirstVisiblePosition() == 0) {
                            leftListview.setSelection(0);
                        }
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            //滑到顶部底部控件显示的话if (firstVisibleItem == 0 && isFirstScroll == true)用这句
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

    }

    private void initView() {
        rl_back = findViewById(R.id.rl_back);
        tv_title = findViewById(R.id.tv_title);
        banner = findViewById(R.id.banner);
        civ_head = findViewById(R.id.civ_head);
        iv_phone = findViewById(R.id.iv_phone);
        leftListview = findViewById(R.id.left_listview);
        pinnedListView = findViewById(R.id.pinnedListView);
        sv = findViewById(R.id.sv);
        rl_back.setOnClickListener(this);
        civ_head.setOnClickListener(this);
        iv_phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();//拨打商家电话
        if (i == R.id.rl_back) {
            finish();
        } else if (i == R.id.civ_head) {
            Toast.makeText(this, "我是商家头像", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.iv_phone) {
            callPhone("18888888888");
        }
    }

    private void callPhone(String s) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + s));
        startActivity(intent);
    }
}
