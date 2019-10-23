package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.api.GitHubService;
import commeishi.shanjing.mymeishi.model.Tjyhpinglun;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MyevaluateActivity extends BaseActivity {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private RatingBar ratingBar;
    private String myevGoodsId;
    private String myevorderid;
    private String myevproduceid;
    private String myevicon;
    private Button my_evelasubmeed;
    private LoadingDialog loadingDialog;
    private int gradenum=1;
    private ImageView myevalimgview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        myevGoodsId = intent.getStringExtra("myevGoodsId");
        myevorderid = intent.getStringExtra("myevorderid");
        myevproduceid = intent.getStringExtra("myevproduceid");
        myevicon = intent.getStringExtra("myevicon");
        setContentView(R.layout.activity_myevaluate);
        initview();

    }

    private void initview() {
        myevalimgview =(ImageView) findViewById(R.id.myevalimgview);
        Glide.with(this).load(myevicon).into(myevalimgview);
        my_evelasubmeed =(Button) findViewById(R.id.my_evelasubmeed);
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("评价");
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //获取星星数量
                int num = ratingBar.getNumStars();
                //获取步长
                float step = ratingBar.getStepSize();
                //获取当前评分,与参数rating一致
                // float currentRating = ratingBar.getRating();
                Log.d("debug","num="+num+",step="+step+",rating="+rating);
//                textView.setText(""+rating);
                float a=rating;
                gradenum = (int)a;
                Log.d("dssaacc", gradenum +"");

            }
        });

        my_evelasubmeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog = new LoadingDialog(MyevaluateActivity.this);
                Gson gson = new Gson();
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("order_id",myevorderid);
                paramsMap.put("goods_id",myevGoodsId);
                paramsMap.put("produce_id",myevproduceid);
                paramsMap.put("grade",gradenum+"");
                paramsMap.put("comment","11111");
                String info = gson.toJson(paramsMap);
                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                RxHttpUtils.createApi(GitHubService.class)
                        .AddJudge(body)
                        .compose(Transformer.<Tjyhpinglun>switchSchedulers(loadingDialog))
                        .subscribe(new Observer<Tjyhpinglun>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }
                            @Override
                            public void onNext(Tjyhpinglun tjyhpinglun) {
                                Log.d("ssxxs",tjyhpinglun.getStatus());

                            }
                            @Override
                            public void onError(Throwable e) {
                                Log.d("dwweeeqq2",e+"");
                            }
                            @Override
                            public void onComplete() {
                            }
                        });
            }
        });

    }
}
