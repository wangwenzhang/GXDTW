package com.huitui.gxdt.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huitui.gxdt.R;
import com.huitui.gxdt.adapter.PopListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangwenzhang on 2016/11/8.
 */
public class MainFragemt extends Fragment {
    @InjectView(R.id.fragment_main_reclyview)
    RecyclerView fragmentMainReclyview;
    private List<String> list;
    private PopListAdapter popListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        indata();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //fragmentMainReclyview.setLayoutManager(layoutManager);
        //HomeAdapter homeAdapter=new HomeAdapter(getActivity(),list);
        //fragmentMainReclyview.setAdapter(homeAdapter);
        super.onViewCreated(view, savedInstanceState);
    }
    private void indata(){
        list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(i+"");
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
