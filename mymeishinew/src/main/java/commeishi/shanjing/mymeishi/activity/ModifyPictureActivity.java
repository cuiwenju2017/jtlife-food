package commeishi.shanjing.mymeishi.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.view.LoadingDialog;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import commeishi.shanjing.mymeishi.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ModifyPictureActivity extends BaseActivity {

    private TextView tv_modify_head_image;
    private static final int REQUEST_CODE_PHOTO = 0x1;
    private static final int REQUEST_CODE_CAMERA = 0x2;
    private CircleImageView user_head_image;
    Bitmap mBitmap;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_picture);
        initView();
        initDate();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(this);
        user_head_image = (CircleImageView)findViewById(R.id.user_head_image);
        tv_modify_head_image =(TextView) findViewById(R.id.tv_modify_head_image);
        tv_modify_head_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void selectImage() {
        final String[] items = {"拍照", "相册"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择图片");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // 这里item是根据选择的方式，
                if (item == 0) {
                    Intent intent_pat = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent_pat.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                            .fromFile(new File(Environment
                                    .getExternalStorageDirectory(), "head.jpg")));
                    startActivityForResult(intent_pat, REQUEST_CODE_CAMERA);
                } else if (item == 1) {
                    Intent intent_photo = new Intent(Intent.ACTION_PICK, null);
                    intent_photo
                            .setDataAndType(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    "image/*");
                    startActivityForResult(intent_photo, REQUEST_CODE_PHOTO);
                }
            }
        });
        builder.show();
    }
    private void initDate() {
        path = Environment.getExternalStorageDirectory().getPath();
        //showToast(path);
        mBitmap = BitmapFactory.decodeFile(path + "/head.jpg");// 从sdcard中获取本地图片,通过BitmapFactory解码,转成bitmap
        if (mBitmap != null) {
            @SuppressWarnings("deprecation")
            RoundedBitmapDrawable circularBitmapDrawable =
                    RoundedBitmapDrawableFactory.create(getResources(), mBitmap);
            circularBitmapDrawable.setCornerRadius(35);
            user_head_image.setImageDrawable(circularBitmapDrawable);

        } else {
            /** 从服务器取,同时保存在本地 ,后续的工作 */
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 103:
                onCaptureImageResult(data);
                break;
            case 104:
                onSelectFromGalleryResult(data);
                break;
            case REQUEST_CODE_PHOTO:
                if (resultCode == RESULT_OK) {
                    shearPhoto(data.getData());// 剪切图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle mBundle = data.getExtras();
                    mBitmap = mBundle.getParcelable("data");
                    if (mBitmap != null) {
                        /** 上传到服务器 待--- */
                        setPictureToSD(mBitmap);// 保存本地
                        user_head_image.setImageBitmap(mBitmap);// 显示

                        //此处拿到命名为head.jpg的file上传上去
                        File file = new File(Environment.getExternalStorageDirectory(), "head.jpg");
                        Log.e("456789", file.toString());
                        upload(file);
                    }
                }
                break;
        }
    }

    private void upload(File file) {


//        RxHttpUtils.createApi(GitHubService.class)
//                .touxiang()
//                .compose(Transformer.<BaseData<Touxiangbean>>switchSchedulers(loadingDialog))
//                .subscribe(new DataObserver<Touxiangbean>() {
//
//                    @Override
//                    protected void onError(String errorMsg) {
//
//                    }
//
//                    @Override
//                    protected void onSuccess(Touxiangbean data) {
//
//
//                    }
//                });

    }

    private void setPictureToSD(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {// 检测SD卡的可用性
            return;
        }
        FileOutputStream fos = null;

        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "/head.jpg";// 图片名字

        try {
            fos = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 压缩后写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭输出流
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void onCaptureImageResult(Intent data) {
        Bundle extras = data.getExtras();
        if (extras == null) {
            return;
        }
        Bitmap thumbnail = data.getExtras().getParcelable("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
//        File capfile = new File(Environment.getExternalStorageDirectory(),
//                "head" + ".jpg");
//        Log.e("cccccc",capfile.toString());
//        upload(capfile);
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        user_head_image.setImageBitmap(thumbnail);
    }
    private void onSelectFromGalleryResult(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            user_head_image.setImageBitmap(photo);
        }
    }
    private void shearPhoto(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
    private String path = "/sdcard/headPhoto";

}
