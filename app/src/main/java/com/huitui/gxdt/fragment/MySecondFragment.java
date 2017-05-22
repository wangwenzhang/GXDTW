package com.huitui.gxdt.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.huitui.gxdt.R;
import com.huitui.gxdt.activity.JokeActivity;
import com.huitui.gxdt.activity.MainActivity;
import com.huitui.gxdt.adapter.SecondAdapter;
import com.huitui.gxdt.bean.JokeBean;
import com.huitui.gxdt.yuliu.JokeDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class MySecondFragment extends Fragment {
    @InjectView(R.id.fragmen_my_second_rcv)
    RecyclerView fragmenMySecondRcv;
    private SecondAdapter secondAdapter;
    private List<JokeBean> jokeBeanList;
    private JokeDao jokeDao;
    private MainActivity mactivity1;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity1= (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_second, container, false);
        ButterKnife.inject(this, view);
        jokeDao=new JokeDao(getActivity());
        jokeBeanList=new ArrayList<>();
        jokeBeanList.addAll(jokeDao.getDatabse());
        secondAdapter=new SecondAdapter(getActivity(),jokeBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());//布局管理
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmenMySecondRcv.setLayoutManager(layoutManager);
        fragmenMySecondRcv.setAdapter(secondAdapter);
        update();
        mactivity1.setJokeButtonclickLinstener(new MainActivity.OnJokeButtonClickLinstener() {
            @Override
            public void onchange() {
                jokeBeanList.clear();
                jokeBeanList.addAll(jokeDao.getDatabse());
                secondAdapter.notifyDataSetChanged();
            }
        });
        mactivity1.setButtonClickedListener(new MainActivity.OnButtonClickedListener() {
            @Override
            public void onclicked() {
                jokeBeanList.clear();
                jokeBeanList.addAll(jokeDao.getDatabse());
                secondAdapter.notifyDataSetChanged();
            }
        });
        secondAdapter.setOnItemClickListener(new SecondAdapter.MyItemClickListener() {
            @Override
            public void setOnItemClickListener(View itemview, View view, int postion) {
                startActivity(new Intent(getActivity(), JokeActivity.class));
            }

            @Override
            public void OnLike(int position) {

            }

            @Override
            public void OnBtDelete(int position) {
                JokeBean jokeBean=jokeBeanList.get(position);
                jokeDao.removeData(jokeBean);
                jokeBeanList.remove(position);
                secondAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),"已删除",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }
    private void update(){
        jokeBeanList.clear();
        jokeBeanList.addAll(jokeDao.getDatabse());
        secondAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
