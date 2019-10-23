package hz.sc.helpprojects.helpfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import hz.sc.helpprojects.R;

/**
 * Created by YU on 2017/7/22.
 */
@SuppressLint("ValidFragment")
public class Fragment_content extends Fragment {

    private TextView textview;
    private String name;

//    public Fragment_content(String name) {
//        this.name=name;
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item,null);
        textview = (TextView)view.findViewById(R.id.textview);
        textview.setText(name);
//        if(name.equals("推荐")){
//            Toast.makeText(getActivity(),"ssdd",Toast.LENGTH_SHORT).show();
//        }
        return  view;
    }
}
