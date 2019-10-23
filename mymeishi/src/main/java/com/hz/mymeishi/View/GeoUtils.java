package com.hz.mymeishi.View;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.hz.mymeishi.View.Bean.SuggestionAddress;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */

public class GeoUtils implements OnGetGeoCoderResultListener {
    private GeoCoder mSearch;
    private GetEnlanlng getEnlanlng;
    private ArrayList<SuggestionAddress> list;
    private static GeoUtils geoUtils = new GeoUtils();


    public void setGetEnlanlng(GetEnlanlng getEnlanlng) {
        this.getEnlanlng = getEnlanlng;
    }

    public static GeoUtils getInstance() {
        if (geoUtils == null) {
            geoUtils = new GeoUtils();
        }
        return geoUtils;
    }

    public GeoUtils() {
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
    }

    public void geoSearch(LatLng latLng) {
        list = new ArrayList<>();
        if (mSearch != null) {
            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        }
    }

    public void distroyGeo() {
        if (mSearch != null) {
            mSearch.destroy();
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult != null && reverseGeoCodeResult.error == SearchResult.ERRORNO.NO_ERROR){
            if (reverseGeoCodeResult.getPoiList() != null && reverseGeoCodeResult.getPoiList().size() > 0){
                        for (PoiInfo p : reverseGeoCodeResult.getPoiList()){
                            SuggestionAddress suggestionAddress = new SuggestionAddress(p.name, p.address,p.location.latitude,p.location.longitude);
                            System.out.println(suggestionAddress.toString());
                            list.add(suggestionAddress);
                        }
                        if (getEnlanlng != null){
                            getEnlanlng.getPoi(list);
                        }
            }

        }

    }

    public static interface GetEnlanlng {
        public void getPoi(List<SuggestionAddress> list);

    }
}
