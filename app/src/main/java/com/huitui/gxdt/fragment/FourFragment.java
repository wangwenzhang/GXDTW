package com.huitui.gxdt.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huitui.gxdt.R;
import com.huitui.gxdt.adapter.MyfourPagerAdapder;
import com.huitui.gxdt.contents.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class FourFragment extends Fragment {
    @InjectView(R.id.fragmen_four_tb)
    TabLayout fragmenFourTb;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.textView)
    TextView textView;
    private ViewPager vp;
    private List<String> titlelist;
    private List<Fragment> fragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        settitle();
        addfragment();
        fragmenFourTb.setTabMode(TabLayout.MODE_FIXED);
        fragmenFourTb.addTab(fragmenFourTb.newTab().setText(titlelist.get(0)));//数据源添加进去
        fragmenFourTb.addTab(fragmenFourTb.newTab().setText(titlelist.get(1)));
        fragmenFourTb.addTab(fragmenFourTb.newTab().setText(titlelist.get(2)));
        vp = (ViewPager) view.findViewById(R.id.fragmen_four_vp);
        vp.setAdapter(new MyfourPagerAdapder(getChildFragmentManager(), fragmentList, titlelist));
        vp.setPageTransformer(true, new ZoomOutPageTransformer());//添加切换动画
        fragmenFourTb.setupWithViewPager(vp);//viewpager  和tablayout结合
        TelephonyManager TelephonyMgr = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        textView2.setText(szImei);
        //textView.setText(android.os.Build.MODEL);
        textView.setText(android.os.Build.MANUFACTURER+"-"+android.os.Build.MODEL);
        super.onViewCreated(view, savedInstanceState);
    }

    private void settitle() {//tablayout的数据源
        titlelist = new ArrayList<>();
        titlelist.add("我的表情");
        titlelist.add("我的笑话");
        titlelist.add("我的视频");
    }

    private void addfragment() {//viewpager的数据源
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyFristFragment());
        fragmentList.add(new MySecondFragment());
        fragmentList.add(new MyThreeFragment());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
