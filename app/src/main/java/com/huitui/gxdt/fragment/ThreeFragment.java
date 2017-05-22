package com.huitui.gxdt.fragment;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huitui.gxdt.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class ThreeFragment extends Fragment {
    @InjectView(R.id.fragment_three_jingwei)
    TextView fragmentThreeJingwei;
    private LocationManager locationManager;
    private String locationProvider;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if(providers.contains(LocationManager.GPS_PROVIDER)){
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else{
            Toast.makeText(getActivity(), "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return ;
        }
        //获取Location
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if(location!=null){
            //不为空,显示地理位置经纬度
            showLocation(location);
            if(locationManager!=null){
                //移除监听器
                locationManager.removeUpdates(locationListener);
            }
        }/*else {
            Toast.makeText(getActivity(), "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }*/
        locationManager.requestLocationUpdates(locationProvider, 3000, 100, locationListener);
        super.onViewCreated(view, savedInstanceState);
    }
    private void showLocation(Location location){
        String locationStr = "维度：" + location.getLatitude() +"\n"
                + "经度：" + location.getLongitude();
        fragmentThreeJingwei.setText(locationStr);
    }
    LocationListener locationListener =  new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            showLocation(location);

        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(locationManager!=null){
            //移除监听器
            locationManager.removeUpdates(locationListener);
        }
        ButterKnife.reset(this);
    }
}
