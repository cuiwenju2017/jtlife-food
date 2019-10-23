package com.base.commonlib.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ToImage {
    /**
     * 将图片文件提交获取url
     *
     * @param type 图片标识，身份证id，头像head，营业执照license，商店照片shop
     *
     */

    public static int getimageurl(String type, File file){
        String url= "http://192.168.1.16:8081/zxWeb/photo/add";
        int backurl=0;
        RequestBody fileBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("type",type)
                .build();
        Request request= new Request.Builder()
                .url(url)
                .post(fileBody)
                .build();
        OkHttpClient.Builder httpBuilder= new OkHttpClient.Builder();
        OkHttpClient okHttpClient=httpBuilder
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(150,TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String a= response.body().string();
//                try {
//                    JSONObject jsonObject= new JSONObject(a);
//                    String date=jsonObject.getString("data").toString();
//                    JSONObject jsonObject1= new JSONObject(date);
//                    backurl=jsonObject1.getString("imgUrl")+",";
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }
        });


        return backurl;
    }
}
