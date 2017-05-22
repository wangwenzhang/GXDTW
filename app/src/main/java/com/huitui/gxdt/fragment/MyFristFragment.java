package com.huitui.gxdt.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huitui.gxdt.R;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class MyFristFragment extends Fragment {
    //private FrameLayout frameLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_frist,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //frameLayout= (FrameLayout) view.findViewById(R.id.fragment_my_frist_fragmelayout);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_my_frist_fragmelayout,new MyFristFragmentBao());
        ft.commit();
        super.onViewCreated(view, savedInstanceState);
    }
}
