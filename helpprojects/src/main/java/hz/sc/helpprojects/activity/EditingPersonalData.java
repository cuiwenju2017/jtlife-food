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

import hz.sc.helpprojects.R;

public class EditingPersonalData extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private TextView tv_finished;
    private LinearLayout lin_xiugaixingbie;
    private TextView xingbienan;
    private TextView xingbienv;
    private TextView xingbietianxie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_personal_data);
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
        tv_title.setText("编辑资料");
        tv_finished = (TextView)findViewById(R.id.tv_finished);
        tv_finished.setText("保存");
        lin_xiugaixingbie =(LinearLayout) findViewById(R.id.lin_xiugaixingbie);
        lin_xiugaixingbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        xingbietianxie = (TextView)findViewById(R.id.xingbietianxie);
    }
    private void show() {
         final Dialog  dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.xingbiedialog_layout, null);
        //初始化控件
        xingbienan = (TextView)inflate.findViewById(R.id.xingbienan);
        xingbienv = (TextView)inflate.findViewById(R.id.xingbienv);
        xingbienan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xingbietianxie.setText(xingbienan.getText().toString());
                dialog.dismiss();
            }
        });
        xingbienv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                xingbietianxie.setText(xingbienv.getText().toString());
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
}
