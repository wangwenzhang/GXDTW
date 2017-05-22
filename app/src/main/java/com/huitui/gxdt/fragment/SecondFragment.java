package com.huitui.gxdt.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.huitui.gxdt.R;
import com.huitui.gxdt.adapter.SecondAdapter1;
import com.huitui.gxdt.bean.JokeBean1;
import com.huitui.gxdt.util.OkhttpUtils;
import com.huitui.gxdt.util.UrlString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class SecondFragment extends Fragment {
    @InjectView(R.id.fragmen_second_recyclerview)
    RecyclerView fragmenSecondRecyclerview;
   /* private List<JokeBean> jokeBeanList;
    private SecondAdapter secondAdapter;*/
    //private JokeDao jokeDao;
    private List<JokeBean1.ListBean> jokebeanList1;
    private SecondAdapter1 secondAdapter1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //addjokebean();
        //jokeDao=new JokeDao(getActivity());
        lodingdata();
        secondAdapter1=new SecondAdapter1(getActivity(),jokebeanList1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());//布局管理器
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmenSecondRecyclerview.setLayoutManager(layoutManager);
        fragmenSecondRecyclerview.setAdapter(secondAdapter1);
       /* secondAdapter=new SecondAdapter(getActivity(),jokeBeanList);
        fragmenSecondRecyclerview.setAdapter(secondAdapter);
        secondAdapter.setOnItemClickListener(new SecondAdapter.MyItemClickListener() {
            @Override
            public void setOnItemClickListener(View itemview, View view, int postion) {

                startActivity(new Intent(getActivity(), JokeActivity.class));
            }

            @Override
            public void OnLike(int position) {
                jokeDao.add(jokeBeanList.get(position));
                Toast.makeText(getActivity(),"已添加到我喜欢的笑话里",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnBtDelete(int position) {

            }
        });*/
        super.onViewCreated(view, savedInstanceState);
    }
   /* private void addjokebean(){
        jokeBeanList=new ArrayList<>();
        for (int i=0;i<10;i++){
            jokeBeanList.add(new JokeBean(9527+i,"中国这一成就让全世界震惊"+i,null,null,null,false));
        }
    }*/
    private void lodingdata(){
        jokebeanList1=new ArrayList<>();
        try {
            OkhttpUtils.get(UrlString.Jokedta, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result=response.body().string();
                    final JokeBean1 jokeBean1=new Gson().fromJson(result,JokeBean1.class);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jokebeanList1.addAll(jokeBean1.getList());
                            secondAdapter1.notifyDataSetChanged();
                        }
                    });

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
