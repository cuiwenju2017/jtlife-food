package hz.sc.helpprojects.helpfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hz.sc.helpprojects.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpgrAllPersons extends Fragment {


    public HelpgrAllPersons() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_helpgr_all_persons, container, false);
    }

}
