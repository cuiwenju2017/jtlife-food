package com.hz.coffeeshop.Fragment;



import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.base.commonlib.utils.StatusBarUtil;
import com.hz.coffeeshop.R;
import com.hz.coffeeshop.view.CoffeeStatusBarUtils;


public class CoffecircleFragment extends BaseFragment {


    private View viewdddsssaa;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coffecircle;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        //用来设置整体下移，状态栏沉浸
        viewdddsssaa =(View) view.findViewById(R.id.viewdddsssaa);
        CoffeeStatusBarUtils.setPaddingTop(getActivity(), viewdddsssaa);
    }

    @Override
    public void fetchData() {

    }
    public void setDarkStatusIcon(boolean bDark) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            View decorView = getActivity().getWindow().getDecorView();
           getActivity(). getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));//这里对应的是状态栏的颜色，就是style中colorPrimaryDark的颜色
            if(decorView != null){
                int vis = decorView.getSystemUiVisibility();
                if(bDark){
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else{
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }
}
