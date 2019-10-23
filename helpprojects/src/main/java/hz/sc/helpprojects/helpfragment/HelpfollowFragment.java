package hz.sc.helpprojects.helpfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hz.sc.helpprojects.R;


public class HelpfollowFragment extends BaseFragment {


    private RecyclerView helprecyclerView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_helpfollow;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initView(view);
    }

    private void initView(View view) {
        helprecyclerView =(RecyclerView) view.findViewById(R.id.helprecyclerView);
    }

    @Override
    public void fetchData() {

    }
}
