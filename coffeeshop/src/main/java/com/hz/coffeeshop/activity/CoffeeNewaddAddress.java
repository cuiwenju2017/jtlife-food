package com.hz.coffeeshop.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.ToastUtils;
import com.hz.coffeeshop.R;

public class CoffeeNewaddAddress extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private RadioButton xianshegn;
    private RadioButton coffeenvshi;
    private TextView gettonfxun;
    private EditText ed_phoneget;
    private static final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_newadd_address);
        initView();
    }

    private void initView() {
        ed_phoneget = (EditText)findViewById(R.id.ed_phoneget);

        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("新增地址");
        xianshegn = (RadioButton)findViewById(R.id.xianshegn);
        coffeenvshi =(RadioButton)findViewById(R.id.coffeenvshi);
        xianshegn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                xianshegn.setChecked(true);
                coffeenvshi.setChecked(false);

            }
        });
        coffeenvshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xianshegn.setChecked(false);
                coffeenvshi.setChecked(true);
            }
        });
        gettonfxun = (TextView)findViewById(R.id.gettonfxun);
        gettonfxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS)!=
                        PackageManager.PERMISSION_GRANTED){//没有权限需要动态获取
                    //动态请求权限
                       ActivityCompat.requestPermissions(CoffeeNewaddAddress.this,
                            new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CODE);

                }else {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                      startActivityForResult(intent, 0);
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            String phoneNumber = "";
            if(data != null) {
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (null != cursor && cursor.moveToFirst()){
                    phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //得到纯数字电话号码
                    if (phoneNumber.startsWith("+86")) {
                        phoneNumber = phoneNumber.replace("+86", "");
                    }
                    phoneNumber = phoneNumber.replace(" ", "");
                    phoneNumber = phoneNumber.replace("-", "");
                    ed_phoneget.setText(phoneNumber);
                    Log.d("ddsada",phoneNumber);
                    cursor.close();
                }
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){//判断是否给于权限
                Toast.makeText(this,"已开启",Toast.LENGTH_SHORT).show();   
            }else {
                Toast.makeText(this,"请开启权限",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
