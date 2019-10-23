package commeishi.shanjing.mymeishi.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.commonlib.BaseActivity;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.fragment.Fragment1;
import commeishi.shanjing.mymeishi.fragment.Fragment2;
import commeishi.shanjing.mymeishi.fragment.Fragment3;
import commeishi.shanjing.mymeishi.fragment.Fragment4;
import commeishi.shanjing.mymeishi.fragment.Fragment5;

import commeishi.shanjing.mymeishi.utils.StatusBarUtil;
import com.base.commonlib.zxing.android.CaptureActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MeiShiHomeActivity extends BaseActivity implements View.OnClickListener {

    public RadioGroup rg;
    private ViewPager vp;
    public ImageView Shzijia, iv_message;
    private SharedPreferences.Editor editor2;
    private ImageView iv_scan;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;
    byte[] result;
    private ImageView iv_search;


    private void assignViews() {
        rg = (RadioGroup) findViewById(R.id.rg);
        vp = (ViewPager) findViewById(R.id.vp);
        Shzijia = (ImageView) findViewById(R.id.Shzijia);
        iv_scan = (ImageView) findViewById(R.id.iv_scan);
        iv_message = findViewById(R.id.iv_message);//消息
        iv_message.setOnClickListener(this);
        iv_search =(ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_shi_home);

        //用来设置整体下移，状态栏沉浸
        StatusBarUtil.getStatusBarHeight(MeiShiHomeActivity.this);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
//        StatusBarUtil.setTranslucentStatus(MainActivity.this);//透明状态栏
        StatusBarUtil.setStatusBarColor(MeiShiHomeActivity.this, Color.parseColor("#ffffff"));//设置背景颜色
        assignViews();
        initData();

    }



    private void initData() {

        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MeiShiHomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MeiShiHomeActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
            }
        });

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        vp.setOffscreenPageLimit(0);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
                vp.setCurrentItem(radioGroup.indexOfChild(radioGroup.findViewById(i)));
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();

                ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                result = output.toByteArray();
//                Intent intent = new Intent();
//                intent.setClass(MeiShiHomeActivity.this, Zxingbimtp.class);
//                intent.putExtra("bitmap", content);
//                startActivity(intent);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(MeiShiHomeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_message) {//跳转消息页面
            startActivity(new Intent(this, MessageActivity.class));
        }else if(i==R.id.iv_search){
            Intent intent =new Intent();
            intent.setClass(MeiShiHomeActivity.this,GoddsSerschActivity.class);
            startActivity(intent);
        }
    }

}
