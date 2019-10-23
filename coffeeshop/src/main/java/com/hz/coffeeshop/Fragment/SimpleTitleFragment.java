package com.hz.coffeeshop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hz.coffeeshop.R;
import com.hz.coffeeshop.adapter.RimAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yuelin on 2016/5/30.
 */
public class SimpleTitleFragment extends Fragment {

    public static final String KEY_TITLE = "key_title";
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString(KEY_TITLE, "");
    }

    private List<String> list = Arrays.asList("现磨咖啡", "李记面食", "北京火锅");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_title, container, false);

        /*TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(title);*/

        RecyclerView rv = view.findViewById(R.id.rv);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv.setLayoutManager(linearLayoutManager);
        //设置适配器
        RimAdapter rimAdapter = new RimAdapter(getActivity(), list);
        rv.setAdapter(rimAdapter);
        return view;
    }

    public String getTitle() {
        return title;
    }
}
