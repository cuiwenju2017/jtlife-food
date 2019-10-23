package com.hz.mymeishi.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.base.commonlib.BaseActivity;


import com.hz.mymeishi.R;
import com.hz.mymeishi.View.Bean.SuggestionAddress;
import com.hz.mymeishi.View.GeoUtils;
import com.hz.mymeishi.adapter.SuggestionAddressAdpter;

import java.util.ArrayList;
import java.util.List;


public class  MapActivity extends BaseActivity implements  GeoUtils.GetEnlanlng, BaiduMap.OnMapStatusChangeListener,SensorEventListener , OnGetSuggestionResultListener {

    private RelativeLayout rl_back;
    private TextView tv_title;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private ListView listView;
    private GeoUtils geoUtils;
    private float x, y;
    private ArrayList<SuggestionAddress> suggestionAddressArrayList;
    private SuggestionAddressAdpter suggestionAddressAdpter;
    private LocationClient mLocClient;
    private SensorManager mSensorManager;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    public MyLocationListenner myListener = new MyLocationListenner();
    public MydingweiLocationListenner dwmyListener = new MydingweiLocationListenner();
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private int mCurrentDirection = 0;
    private Double lastX = 0.0;
    private boolean isFirstLocHoseManLocation = true;
    private SuggestionSearch mysearch;
    private String sssCity;




    private static final int BAIDU_READ_PHONE_STATE =100;
    private ImageView im_biaozhi;
    private TextView searet_suo;

    public void showContacts(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"没有权限,请手动开启定位权限",Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        }else{
           startLocation();
        }
    }


    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    startLocation();
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取位置权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        inintLocation();
        initView();
        if (Build.VERSION.SDK_INT>=23){
            showContacts();
        }else{
            startLocation();//init为定位方法
        }

    }

    private void initView( ) {
        geoUtils = GeoUtils.getInstance();
        geoUtils.setGetEnlanlng(this);
        mMapView = (MapView) findViewById(R.id.mymap);
        listView =(ListView) findViewById(R.id.list_aroundPoi);
        searet_suo =(TextView) findViewById(R.id.searet_suo);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("地图");
        im_biaozhi= (ImageView)findViewById(R.id.im_biaozhi);
        im_biaozhi.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                x = im_biaozhi.getX();
                y = im_biaozhi.getY();
                im_biaozhi.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        suggestionAddressAdpter = new SuggestionAddressAdpter(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent();
                intent.setClass(MapActivity.this,GetAddressActivity.class);
                intent.putExtra("streess",suggestionAddressArrayList.get(i).getStreetMessage());
                Log.d("ddwwqqaaa",suggestionAddressArrayList.get(i).getName()+"6666"+suggestionAddressArrayList.get(i).getStreetMessage());
                startActivity(intent);
                finish();
            }
        });
        listView.setAdapter(suggestionAddressAdpter);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapStatusChangeListener(this);
        mysearch = SuggestionSearch.newInstance();//单例模式
        mysearch.setOnGetSuggestionResultListener(this);
//        searet_suo.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.length() <= 0) {
//                    return;
//                }
//                String s1 = searet_suo.getText().toString();
//                mysearch.requestSuggestion((new SuggestionSearchOption()).keyword(s.toString()).city(s1));
//            }
//        });
        searet_suo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MapActivity.this,SearchActivity.class);
                intent.putExtra("city",sssCity);
                startActivity(intent);
            }
        });
        startLocation();
    }
    private void startLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(0);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, null));
        MapStatus.Builder builder1 = new MapStatus.Builder();
        builder1.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));
    }
    @Override
    protected void onDestroy() {
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mLocClient.stop();
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();

    }
    @Override
    protected void onResume() {
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
        super.onResume();

    }

    //
    @Override
    protected void onPause() {
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        super.onPause();

        isFirstLocHoseManLocation = true;
//
    }




    @Override
    protected void onStop() {
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        // 退出时销毁定位
        mLocClient.stop();
        if (myListener != null) {
            mLocClient.unRegisterLocationListener(myListener);
        }
        super.onStop();
    }

    @Override
    public void getPoi(List<SuggestionAddress> list) {
        suggestionAddressArrayList = new ArrayList<>();
        suggestionAddressArrayList.addAll(list);
        suggestionAddressAdpter.setList((ArrayList<SuggestionAddress>) suggestionAddressArrayList);
    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        LatLng latLng = mBaiduMap.getProjection().fromScreenLocation(new android.graphics.Point((int) x, (int) y));

        System.out.println(latLng.longitude + "-----" + latLng.latitude);
        geoUtils.geoSearch(latLng);
    }



    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {



            System.out.println("定位中0---");
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())

                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                geoUtils.geoSearch(ll);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        double x = event.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
            return;
        }
        for (SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {
            if(info.key!=null){
                suggestionAddressArrayList.clear();
                SuggestionAddress poiResult=new SuggestionAddress(info.address,info.city,info.key);
                //记录搜索到的城市，名称和地区;
//            poiResult.setCity(info.city);
//            poiResult.setName(info.key);
//            poiResult.setAddress(info.city+info.district+info.address);
//            System.out.println(info.city+info.district+info.address);
                suggestionAddressArrayList.add(poiResult);
            }
        }
        suggestionAddressAdpter.setList(suggestionAddressArrayList);
        suggestionAddressAdpter.notifyDataSetChanged();
    }

    //定位自己的位置
    private void inintLocation() {

            mLocClient = new LocationClient(getApplicationContext());
            mLocClient.registerLocationListener(dwmyListener);
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setIsNeedAddress(true);
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(0);
            mLocClient.setLocOption(option);
            mLocClient.start();

    }
    public class MydingweiLocationListenner implements BDLocationListener {



        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation!=null){
                sssCity = bdLocation.getCity();
                Log.d("dsadwwq",sssCity);

            }
        }
    }
}
