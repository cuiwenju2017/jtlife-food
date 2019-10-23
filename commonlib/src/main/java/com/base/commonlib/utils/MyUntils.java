package com.base.commonlib.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUntils {
    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码

    /**
     * 判断是否有某项权限
     *
     * @param string_permission 权限
     * @param request_code      请求码
     * @return
     */
    public static boolean checkReadPermission(Activity activity, String string_permission, int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(activity, string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(activity, new String[]{string_permission}, request_code);
        }
        return flag;
    }

    /**
     * 拨打电话（直接拨打）
     *
     * @param telPhone 电话
     */
    public static void call(Activity activity, String telPhone) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (checkReadPermission(activity, Manifest.permission.CALL_PHONE, REQUEST_CALL_PERMISSION)) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telPhone));
//            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+telPhone));
                activity.startActivity(intent);
            }
        }
    }

    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }


}
