package com.hz.coffeeshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hz.coffeeshop.R;
import com.hz.coffeeshop.view.BaseActivity;

public class CoffecMyPhotoActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private LinearLayout ll_other_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffec_my_photo);
        initView();
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        ll_other_photo = findViewById(R.id.ll_other_photo);
        ll_back.setOnClickListener(this);
        ll_other_photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {
            finish();
        } else if (i == R.id.ll_other_photo) {//其他相册

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            try {
                startActivityForResult(Intent.createChooser(intent, "选择文件上传"), 345);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "请安装一个文件管理器.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
