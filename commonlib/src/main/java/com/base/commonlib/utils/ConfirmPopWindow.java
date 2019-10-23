package com.base.commonlib.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.R;

public class ConfirmPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    public View iv_like, iv_share;
    private Dialog dialog;
    private View inflate;
    public static  ImageView iv_collect;

    public ConfirmPopWindow(Context context) {
        super(context);
        this.context = context;
        initalize();

    }



    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_dialog, null);
        iv_like = view.findViewById(R.id.iv_like);//喜欢
        iv_collect = (ImageView) view.findViewById(R.id.iv_collect);//收藏
        iv_share = view.findViewById(R.id.iv_share);//分享
        iv_like.setOnClickListener(this);
        iv_collect.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.3));
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 1.0f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, -240, -70);
        //showAtLocation(view,Gravity.RIGHT, 10, 300);//有偏差
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();//取消弹窗
//微信分享
//QQ分享
//微博分享
//朋友圈分享
//复制链接
        if (i == R.id.iv_like) {
            Toast.makeText(context, "喜欢", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.iv_collect) {
            Toast.makeText(context, "收藏", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.iv_share) {
            dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
            //获取控件
            TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
            LinearLayout ll_wx = inflate.findViewById(R.id.ll_wx);
            LinearLayout ll_qq = inflate.findViewById(R.id.ll_qq);
            LinearLayout ll_wb = inflate.findViewById(R.id.ll_wb);
            LinearLayout ll_pyq = inflate.findViewById(R.id.ll_pyq);
            LinearLayout ll_copy_link = inflate.findViewById(R.id.ll_copy_link);
            //获取监听
            tv_cancel.setOnClickListener(this);
            ll_wx.setOnClickListener(this);
            ll_qq.setOnClickListener(this);
            ll_wb.setOnClickListener(this);
            ll_pyq.setOnClickListener(this);
            ll_copy_link.setOnClickListener(this);
            //将布局设置给Dialog
            dialog.setContentView(inflate);
            //获取当前Activity所在的窗体
            Window dialogWindow = dialog.getWindow();
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.y = 0;//设置Dialog距离底部的距离
            //宽度填满
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
            dialog.show();//显示对话框
        } else if (i == R.id.tv_cancel) {
            dialog.dismiss();
        } else if (i == R.id.ll_wx) {
            Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_qq) {
            Toast.makeText(context, "QQ", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_wb) {
            Toast.makeText(context, "微博", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_pyq) {
            Toast.makeText(context, "朋友圈", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_copy_link) {
            Toast.makeText(context, "复制链接", Toast.LENGTH_SHORT).show();
        }
    }


}