package com.huitui.gxdt.fragment;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huitui.gxdt.R;
import com.huitui.gxdt.adapter.BasAdapter;
import com.huitui.gxdt.bean.AppInfo;
import com.huitui.gxdt.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class MyThreeFragment extends Fragment {
    @InjectView(R.id.frgamen_mythree_listview)
    MyListView frgamenMythreeListview;
    private BaseAdapter adapter;
    @Nullable
    private ArrayList appList = new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_three, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getAppList();
        adapter=new BasAdapter(appList,getContext());

       // frgamenMythreeListview.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }

    private void getAppList() {
        PackageManager pm = this.getActivity().getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {             // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) // 非系统应用
            {
                AppInfo info = new AppInfo();
                info.setApp_name(packageInfo.applicationInfo.loadLabel(pm).toString());
                info.setPackagename(packageInfo.packageName);
                info.setApp_icon(packageInfo.applicationInfo.loadIcon(pm));              // 获取该应用安装包的Intent，用于启动该应用
                try {
                    info.setApp_version(pm.getPackageInfo(packageInfo.packageName,0).versionName);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                appList.add(info);
            } else {                 // 系统应用　　　　　　　　
            }
        }
    }
        @Override
        public void onDestroyView () {
            super.onDestroyView();
            ButterKnife.reset(this);
        }
    }
