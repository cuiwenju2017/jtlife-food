package hz.sc.helpprojects.helpfragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hz.sc.helpprojects.R;
import hz.sc.helpprojects.adapter.MyhelpRecomAdapter;

public class HelpRecomFragment extends BaseFragment {

    private RecyclerView helprecomcyclerView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_help_recom;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
            initView(view);
            intoRecyclerView();

    }

    private void initView(View view) {
        helprecomcyclerView =(RecyclerView) view.findViewById(R.id.helprecomcyclerView);
    }

    @Override
    public void fetchData() {

    }
    private void intoRecyclerView() {
        //Recycle布局方式
        helprecomcyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //使用适配器
        helprecomcyclerView.setAdapter(new MyhelpRecomAdapter(getActivity()));
    }
}
