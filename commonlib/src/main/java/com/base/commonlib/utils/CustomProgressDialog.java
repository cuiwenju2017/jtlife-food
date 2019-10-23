package com.base.commonlib.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.base.commonlib.R;

public class CustomProgressDialog extends Dialog {

    private Context context = null;
    private static CustomProgressDialog customProgressDialog = null;

    public CustomProgressDialog(Context context){
        super(context);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }
    //创建dialog
    public static CustomProgressDialog createDialog(Context context){
        customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);//应用自定义style
        customProgressDialog.setContentView(R.layout.progress_dialog);//加载自定义布局
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;//居中
        //  customProgressDialog.setCancelable(false);//设置能否取消，默认是True，也就是点击其他地方就会取消这个dialog的显示

        return customProgressDialog;
    }

}
