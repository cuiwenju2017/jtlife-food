package com.hz.mymeishi.model;



import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GetAliPayResponse  extends     ResponseResult{

    public List<DataBean> data;

    public static class DataBean {
        /**
         * response2 : charset=utf-8&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%22%E6%B5%8B%E8%AF%95%E8%AE%A2%E5%8D%951%E5%88%86%E9%92%B1%22%2C%22body%22%3A%22%22%2C%22out_trade_no%22%3A%22201811615241951%22%7D&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F192.168.1.120%3A8080%2FSYWeb%2FalipayNotify%3F&app_id=2016052101425354&sign_type=RSA2&version=1.0&timestamp=2018-01-18+09%3A37%3A50&sign=EQF%2FnAYpFZtx%2F%2B3nn45RPctvwujIFmn68sV35YCex6rl%2B%2FLMiSd%2FgwN0%2BlF1EgmZg%2BVyByF8ptbDRNHzWByLXau61RzaP0elnz0AEJw3zRdZzi0ZSKchIRR3AgSlznPIILrTww5dwAjgbnhP85GH2Bu%2Bjo8OzNYe%2BQOgReU2e7tIGXOL5Z%2BxvctcRkyvvtxd28SaRBnZ45AZIIJgHY%2FH37seE9AmMOzZ5dQZfHEI8f5uc90PGGYhiD6y5ox%2B%2FH3XesNkj5Aq6ryVp%2BG0hyXx1Izbtdaz3rdllzD1M6yMJ5bRBQegfsg1cAvfuAaBVHILpkK4MAv0iBxVvGJM1COA5A%3D%3D
                */

        public String response2;
    }


}
