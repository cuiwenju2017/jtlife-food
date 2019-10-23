package hz.sc.helpprojects.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.utils.AddressPickerView;

import hz.sc.helpprojects.R;

public class Citypacler extends BaseActivity {

    private TextView cityccc;
    private Dialog dialog;
    private View inflate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypacler);

        cityccc =(TextView) findViewById(R.id.cityccc);
        cityccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Citypacler.this, R.style.helpActionSheetDialogStyle);
//填充对话框的布局
                inflate = LayoutInflater.from(Citypacler.this).inflate(R.layout.pop_address_picker, null);
//获取控件
//                ImageView tv_cancle = inflate.findViewById(R.id.tv_cancle);
//                AddressPickerView addressView = inflate.findViewById(R.id.apvAddress);
////获取监听
//                tv_cancle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
                View viewById = inflate.findViewById(R.id.tvSure);
                AddressPickerView addressView = (AddressPickerView)inflate.findViewById(R.id.apvAddress);


                addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
                    @Override
                    public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
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
            }
        });
    }
}
  