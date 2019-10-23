package hz.sc.helpprojects.helpfragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hz.sc.helpprojects.R;
import hz.sc.helpprojects.adapter.MyfindgetAdapter;


@SuppressLint("ValidFragment")
public class HelpPrivamyfriend extends BaseFragment {
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private Button button1;
//    private List<CheckBox> checkBoxList = new ArrayList<CheckBox>();
    private StringBuffer append;
    private  TextView checkbox_lianxiren;
    private List<String> list = Arrays.asList("张晓山", "马杰杰", "李大璐", "李杰杰", "罗金凤",
            "Lucy Green", "张珲春", "金喜恶", "潘胜军", "刘强西", "张泽天");
    private RecyclerView recyclerView;

    public HelpPrivamyfriend(TextView checkbox_lianxiren) {
        this.checkbox_lianxiren=checkbox_lianxiren;
    }

    @Override
    protected int setLayoutId() {
            return R.layout.fragment_help_privamyfriend;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        recyclerView =(RecyclerView) view.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        recyclerView.setLayoutManager(linearLayoutManager);
        MyfindgetAdapter myfindgetAdapter=new MyfindgetAdapter(getActivity(),list);
        recyclerView.setAdapter(myfindgetAdapter);
        myfindgetAdapter.setItemClickListener(new itemViewClickListener() {
            @Override
            public void onItemClickListener(List<CheckBox> checkBoxList) {
                StringBuffer sb = new StringBuffer();
                for (CheckBox checkbox : checkBoxList) {
                    if (checkbox.isChecked()){
                            append = sb.append(checkbox.getText().toString() + ",");
                             checkbox_lianxiren.setText(append+"");
                    }
                }
            }
        });
//        checkBox1 = (CheckBox)view.findViewById(R.id.checkbox1);
//        checkBox2 = (CheckBox) view.findViewById(R.id.checkbox2);
//        checkBox3 = (CheckBox) view.findViewById(R.id.checkbox3);
//        checkBox4 = (CheckBox) view.findViewById(R.id.checkbox4);
//
//        button1 = (Button)view.findViewById(R.id.button1);
//        // 将所有的checkbox放到一个集合中
//        checkBoxList.add(checkBox1);
//        checkBoxList.add(checkBox2);
//        checkBoxList.add(checkBox3);
//        checkBoxList.add(checkBox4);
//        button1.setOnClickListener(new View.OnClickListener() {
//
//
//                @Override
//                public void onClick(View view) {
//                    StringBuffer sb = new StringBuffer();
//                    //遍历集合中的checkBox,判断是否选择，获取选中的文本
//                    for (CheckBox checkbox : checkBoxList) {
//                        if (checkbox.isChecked()){
//                            append = sb.append(checkbox.getText().toString() + ",");
//                             checkbox_lianxiren.setText(append);
//                        }
//                    }
//                    if (sb!=null && "".equals(sb.toString())){
//                        Toast.makeText(getActivity(), "请至少选择一个", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//            });
    }

    @Override
    public void fetchData() {

    }
    public interface itemViewClickListener{
        void onItemClickListener(List<CheckBox> checkBoxList);
    }
}
