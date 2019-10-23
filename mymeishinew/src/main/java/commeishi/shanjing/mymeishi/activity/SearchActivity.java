package commeishi.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.base.commonlib.BaseActivity;


import java.util.ArrayList;

import commeishi.shanjing.mymeishi.R;
import commeishi.shanjing.mymeishi.View.Bean.SuggestionAddress;
import commeishi.shanjing.mymeishi.adapter.SuggestionAddressAdpter;

public class SearchActivity extends BaseActivity implements OnGetSuggestionResultListener {
    private SuggestionAddressAdpter suggestionAddressAdpter;
    private RelativeLayout rl_back;
    private TextView tv_title;
    private SuggestionSearch mysearch;
    private ArrayList<SuggestionAddress> suggestionAddressArrayList=new ArrayList<SuggestionAddress>();
    private ListView list_searchss;
    private EditText searet_suo;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_search);
        mysearch = SuggestionSearch.newInstance();//单例模式
        mysearch.setOnGetSuggestionResultListener(this);
        Intent intent=getIntent();
        city = intent.getStringExtra("city");
        initView();
    }

    private void initView() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        list_searchss = (ListView)findViewById(R.id.list_searchss);
        searet_suo =(EditText) findViewById(R.id.searet_suo);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("搜索");
        suggestionAddressAdpter = new SuggestionAddressAdpter(this);
        list_searchss.setAdapter(suggestionAddressAdpter);
        searet_suo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.equals("")||s==null) {
                    suggestionAddressAdpter.setList(null);
                    return;
                }
                String s1 = searet_suo.getText().toString();
                mysearch.requestSuggestion((new SuggestionSearchOption()).keyword(s.toString()).city(city));
            }
        });
        list_searchss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent();
                intent.setClass(SearchActivity.this,GetAddressActivity.class);
                intent.putExtra("streess",suggestionAddressArrayList.get(i).getStreetMessage()+suggestionAddressArrayList.get(i).getName());
                Log.d("ddwwqqaaa",suggestionAddressArrayList.get(i).getName()+"6666"+suggestionAddressArrayList.get(i).getStreetMessage());
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
          suggestionAddressArrayList.clear();
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            return;
        }

        for (SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {

            if(info.key!=null){
                SuggestionAddress poiResult=new SuggestionAddress(info.address,info.city+info.district,info.key);
              //记录搜索到的城市，名称和地区;
//            poiResult.setCity(info.city);
//            poiResult.setName(info.key);
//            poiResult.setAddress(info.city+info.district+info.address);
//            System.out.println(info.city+info.district+info.address);
                suggestionAddressArrayList.add(poiResult);
                Log.d("ddwwqsssss",info.city+info.district+info.key);
            }
        }
        suggestionAddressAdpter.setList(suggestionAddressArrayList);
    }
}
