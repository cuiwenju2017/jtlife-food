package com.hz.coffeeshop.Fragment;


import android.os.Bundle;
import android.view.View;
import com.hz.coffeeshop.R;
import com.hz.coffeeshop.view.CoffeeStatusBarUtils;


public class CoffeeVideoFragment extends BaseFragment {


    private View viewvideotitle;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coffee_video;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
            initView(view);
    }

    private void initView(View view) {
        viewvideotitle = (View)view.findViewById(R.id.viewvideotitle);
        CoffeeStatusBarUtils.setPaddingTop(getActivity(), viewvideotitle);


    }
    @Override
    public void fetchData() {

    }
}
