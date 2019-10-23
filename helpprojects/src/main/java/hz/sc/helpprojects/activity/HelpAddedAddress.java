package hz.sc.helpprojects.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.utils.AddressPickerView;

import hz.sc.helpprojects.R;

public class HelpAddedAddress extends BaseActivity implements View.OnClickListener{

    private RelativeLayout rl_back;
    private TextView tv_title;
    private TextView tv_finished;
    private TextView textview_shensq;
    private LinearLayout lin_xuanzeadd;
    private Dialog dialog;
    private View inflate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_added_address);
        initView();
    }
    private void initView() {
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title =(TextView) findViewById(R.id.tv_title);
        tv_title.setText("新增地址");
        tv_finished = (TextView)findViewById(R.id.tv_finished);
        tv_finished.setText("保存");
        textview_shensq = (TextView)findViewById(R.id.textview_shensq);
        lin_xuanzeadd = (LinearLayout)findViewById(R.id.lin_xuanzeadd);
        lin_xuanzeadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lin_xuanzeadd:
                dialog = new Dialog(HelpAddedAddress.this, R.style.helpActionSheetDialogStyle);

                inflate = LayoutInflater.from(HelpAddedAddress.this).inflate(R.layout.pop_address_picker, null);

//                ImageView tv_cancle = inflate.findViewById(R.id.tv_cancle);
//                AddressPickerView addressView = inflate.findViewById(R.id.apvAddress);

//                tv_cancle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
                AddressPickerView addressView = (AddressPickerView)inflate.findViewById(R.id.apvAddress);
                addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
                    @Override
                    public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
                        textview_shensq.setText(address);
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(inflate);
                Window dialogWindow = dialog.getWindow();
                dialogWindow.setGravity(Gravity.BOTTOM);
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 0;//设置Dialog距离底部的距离
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
                break;
        }
    }
}
