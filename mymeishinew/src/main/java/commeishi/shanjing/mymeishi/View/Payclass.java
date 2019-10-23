package commeishi.shanjing.mymeishi.View;

import android.content.Context;

import commeishi.shanjing.mymeishi.api.App;
import commeishi.shanjing.mymeishi.model.Buydiatelybean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by Administrator on 2018/1/19.
 */

public class Payclass {
    /**
     * 调取微信支付界面
     *
     * @param data
     */
    public static void wechatpay(Buydiatelybean data, Context context) {

        IWXAPI api = WXAPIFactory.createWXAPI(context, App.wechatAppid);
        api.registerApp(App.wechatAppid);

        PayReq payReq = new PayReq();

        payReq.appId = App.wechatAppid;
        payReq.partnerId = data.getData().getPaydata().getPartnerid();
        payReq.prepayId = data.getData().getPaydata().getPrepayid();
        payReq.packageValue = "Sign=WXPay";
        payReq.nonceStr = data.getData().getPaydata().getNoncestr();
        payReq.timeStamp = data.getData().getPaydata().getTimestamp();
        payReq.sign = data.getData().getPaydata().getSign();
//        payReq.sign = "e5c541a2d159fb677104dcdc9ba7b4bb";
        api.sendReq(payReq);

    }




}
