package hz.sc.helpprojects.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.utils.AddressPickerView;

import hz.sc.helpprojects.R;


public class Privacysettings extends BaseActivity implements View.OnClickListener{

    private LinearLayout gongkai_linlayout;
    private CheckBox checkbox_gongkai;
    private LinearLayout linlayoutsiyou;
    private CheckBox mysiyou_checkbox;
    private LinearLayout linlayput_bufen;
    private CheckBox bufen_checkbox;
    private LinearLayout layout_noplase;
    private CheckBox noplase_checkbox;
    private LinearLayout linlayout_yongjiu;
    private CheckBox checkbox_yjiu;
    private LinearLayout liblayout_esshours;
    private CheckBox checkbox_esshours;
    private LinearLayout layout_ssbhours;
    private CheckBox checkbox_ssbhours;
    private LinearLayout layout_qsehours;
    private CheckBox checkbox_qsehours;
    private LinearLayout lin_finsh_privacys;
    private TextView phone;
    private String maskNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacysettings);
        initView();
    }
    private void initView() {
        phone = (TextView)findViewById(R.id.phone);
        final String mobile = phone.getText().toString();
        maskNumber = mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());
        phone.setText(maskNumber);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile ));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                Log.d("Ddsaqqq",maskNumber+"");
            }
        });
        lin_finsh_privacys =(LinearLayout)findViewById(R.id.lin_finsh_privacys);
        lin_finsh_privacys.setOnClickListener(this);
        //公开
        gongkai_linlayout =(LinearLayout) findViewById(R.id.gongkai_linlayout);
        gongkai_linlayout.setOnClickListener(this );
        checkbox_gongkai =(CheckBox) findViewById(R.id.checkbox_gongkai);
        checkbox_gongkai.setOnClickListener(this);
        //仅自己
        linlayoutsiyou =(LinearLayout) findViewById(R.id.linlayoutsiyou);
        linlayoutsiyou.setOnClickListener(this);
        mysiyou_checkbox = (CheckBox)findViewById(R.id.mysiyou_checkbox);
        mysiyou_checkbox.setOnClickListener(this);
        //部分
        linlayput_bufen = (LinearLayout)findViewById(R.id.linlayput_bufen);
        linlayput_bufen.setOnClickListener(this);
        bufen_checkbox = (CheckBox)findViewById(R.id.bufen_checkbox);
        bufen_checkbox.setOnClickListener(this);
        //不给谁看
        layout_noplase = (LinearLayout)findViewById(R.id.layout_noplase);
        layout_noplase.setOnClickListener(this);
        noplase_checkbox =(CheckBox) findViewById(R.id.noplase_checkbox);
        noplase_checkbox.setOnClickListener(this);
        //永久
        linlayout_yongjiu =(LinearLayout) findViewById(R.id.linlayout_yongjiu);
        linlayout_yongjiu.setOnClickListener(this);
        checkbox_yjiu = (CheckBox)findViewById(R.id.checkbox_yjiu);
        checkbox_yjiu.setOnClickListener(this);
        //二十四小时
        liblayout_esshours = (LinearLayout)findViewById(R.id.liblayout_esshours);
        liblayout_esshours.setOnClickListener(this);
        checkbox_esshours = (CheckBox)findViewById(R.id.checkbox_esshours);
        checkbox_esshours.setOnClickListener(this);
        //四十八小时
        layout_ssbhours = (LinearLayout)findViewById(R.id.layout_ssbhours);
        layout_ssbhours.setOnClickListener(this);
        checkbox_ssbhours = (CheckBox)findViewById(R.id.checkbox_ssbhours);
        checkbox_ssbhours.setOnClickListener(this);
        //七十二小时
        layout_qsehours = (LinearLayout)findViewById(R.id.layout_qsehours);
        layout_qsehours.setOnClickListener(this);
        checkbox_qsehours = (CheckBox)findViewById(R.id.checkbox_qsehours);
        checkbox_qsehours.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.gongkai_linlayout) {
            checkbox_gongkai.setChecked(true);
            mysiyou_checkbox.setChecked(false);
            bufen_checkbox.setChecked(false);
            noplase_checkbox.setChecked(false);

        } else if (i == R.id.linlayoutsiyou) {
            checkbox_gongkai.setChecked(false);
            mysiyou_checkbox.setChecked(true);
            bufen_checkbox.setChecked(false);
            noplase_checkbox.setChecked(false);

        } else if (i == R.id.linlayput_bufen) {
            Intent intent = new Intent();
            intent.setClass(Privacysettings.this, Citypacler.class);
            startActivity(intent);

            checkbox_gongkai.setChecked(false);
            mysiyou_checkbox.setChecked(false);
            bufen_checkbox.setChecked(true);
            noplase_checkbox.setChecked(false);


        } else if (i == R.id.layout_noplase) {
            checkbox_gongkai.setChecked(false);
            mysiyou_checkbox.setChecked(false);
            bufen_checkbox.setChecked(false);
            noplase_checkbox.setChecked(true);

        } else if (i == R.id.checkbox_gongkai) {
            checkbox_gongkai.setChecked(true);
            mysiyou_checkbox.setChecked(false);
            bufen_checkbox.setChecked(false);
            noplase_checkbox.setChecked(false);

        } else if (i == R.id.mysiyou_checkbox) {
            checkbox_gongkai.setChecked(false);
            mysiyou_checkbox.setChecked(true);
            bufen_checkbox.setChecked(false);
            noplase_checkbox.setChecked(false);

        } else if (i == R.id.bufen_checkbox) {
            checkbox_gongkai.setChecked(false);
            mysiyou_checkbox.setChecked(false);
            bufen_checkbox.setChecked(true);
            noplase_checkbox.setChecked(false);

        } else if (i == R.id.noplase_checkbox) {
            checkbox_gongkai.setChecked(false);
            mysiyou_checkbox.setChecked(false);
            bufen_checkbox.setChecked(false);
            noplase_checkbox.setChecked(true);

        } else if (i == R.id.linlayout_yongjiu) {
            checkbox_yjiu.setChecked(true);
            checkbox_esshours.setChecked(false);
            checkbox_ssbhours.setChecked(false);
            checkbox_qsehours.setChecked(false);

        } else if (i == R.id.liblayout_esshours) {
            checkbox_yjiu.setChecked(false);
            checkbox_esshours.setChecked(true);
            checkbox_ssbhours.setChecked(false);
            checkbox_qsehours.setChecked(false);

        } else if (i == R.id.layout_ssbhours) {
            checkbox_yjiu.setChecked(false);
            checkbox_esshours.setChecked(false);
            checkbox_ssbhours.setChecked(true);
            checkbox_qsehours.setChecked(false);

        } else if (i == R.id.layout_qsehours) {
            checkbox_yjiu.setChecked(false);
            checkbox_esshours.setChecked(false);
            checkbox_ssbhours.setChecked(false);
            checkbox_qsehours.setChecked(true);

        } else if (i == R.id.checkbox_yjiu) {
            checkbox_yjiu.setChecked(true);
            checkbox_esshours.setChecked(false);
            checkbox_ssbhours.setChecked(false);
            checkbox_qsehours.setChecked(false);

        } else if (i == R.id.checkbox_esshours) {
            checkbox_yjiu.setChecked(false);
            checkbox_esshours.setChecked(true);
            checkbox_ssbhours.setChecked(false);
            checkbox_qsehours.setChecked(false);

        } else if (i == R.id.checkbox_ssbhours) {
            checkbox_yjiu.setChecked(false);
            checkbox_esshours.setChecked(false);
            checkbox_ssbhours.setChecked(true);
            checkbox_qsehours.setChecked(false);

        } else if (i == R.id.checkbox_qsehours) {
            checkbox_yjiu.setChecked(false);
            checkbox_esshours.setChecked(false);
            checkbox_ssbhours.setChecked(false);
            checkbox_qsehours.setChecked(true);

        } else if (i == R.id.lin_finsh_privacys) {
            finish();

        }
    }

}
