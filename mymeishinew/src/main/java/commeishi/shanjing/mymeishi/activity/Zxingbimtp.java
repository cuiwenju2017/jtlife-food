package commeishi.shanjing.mymeishi.activity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.base.commonlib.BaseActivity;


import com.base.commonlib.utils.ZxingUtils;

import commeishi.shanjing.mymeishi.R;

public class Zxingbimtp extends BaseActivity {

    private ImageView imageview;
    private String tuxiangim;
    byte[] res;
    private String geturl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxingbimtp);
        geturl = getIntent().getStringExtra("bitmap");
        initView();



    }

    private void initView() {
        imageview = (ImageView)findViewById(R.id.imageview);
//        imageview.setImageBitmap(getPicFromBytes(res,null));

        Bitmap bitmap = ZxingUtils.createQRImage(this, imageview, geturl);
        if (bitmap != null && !bitmap.isRecycled()) {
            imageview.setImageBitmap(bitmap);
        }

    }
    //下面的这个方法是将byte数组转化为Bitmap对象的一个方法
    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {

        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,  opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;

    }
}
