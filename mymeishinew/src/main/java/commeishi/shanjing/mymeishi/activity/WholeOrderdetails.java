package commeishi.shanjing.mymeishi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;

import commeishi.shanjing.mymeishi.R;


public class WholeOrderdetails extends BaseActivity {

    private TextView tv_title;
    private RelativeLayout rl_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_orderdetails);
        initView();
    }

    private void initView() {
        tv_title =(TextView) findViewById(R.id.tv_title);
        tv_title.setText("查看订单");
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
