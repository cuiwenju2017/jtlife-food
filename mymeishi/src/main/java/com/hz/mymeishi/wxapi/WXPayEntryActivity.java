package com.hz.mymeishi.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;


import com.hz.mymeishi.R;
import com.hz.mymeishi.activity.MeiShiHomeActivity;
import com.hz.mymeishi.api.App;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
	private TextView wxjieguo
			;



	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
		initview();
    	api = WXAPIFactory.createWXAPI(this, App.wechatAppid);
        api.handleIntent(getIntent(), this);
    }

	private void initview() {
		wxjieguo = (TextView)findViewById(R.id.wxjieguo);
	}
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {

		if (resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
			if (resp.errCode==0){
				wxjieguo.setText("支付成功，请去订单页面查看订单信息");
			}else{
				wxjieguo.setText("支付失败，请去订单页面查看订单信息");
			}

		}

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent=new Intent();
			intent.setClass(WXPayEntryActivity.this,MeiShiHomeActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}