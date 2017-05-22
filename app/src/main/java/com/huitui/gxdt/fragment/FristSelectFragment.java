package com.huitui.gxdt.fragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.huitui.gxdt.R;
import com.huitui.gxdt.activity.MainActivity;
import com.huitui.gxdt.activity.PictureActivity;
import com.huitui.gxdt.adapter.HomeAdapter;
import com.huitui.gxdt.bean.PictureBean;
import com.huitui.gxdt.yuliu.PictureDao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenzhang on 2016/11/21.
 */
public class FristSelectFragment extends Fragment {
    @InjectView(R.id.fragmen_frist_rcyview)
    RecyclerView fragmenFristRcyview;
    @InjectView(R.id.frgamen_frist_ptr)
    PtrClassicFrameLayout ptr;
    private List<PictureBean> titlelist;
    private PictureDao pictureDao;
    private MainActivity activity;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity= (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frist_select, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        addtitle();

        pictureDao = new PictureDao(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());//布局管理器
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmenFristRcyview.setLayoutManager(layoutManager);
        final HomeAdapter adapter = new HomeAdapter(getActivity(), titlelist);
        fragmenFristRcyview.setAdapter(adapter);
        fragmenFristRcyview.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new HomeAdapter.MyItemClickListener() {//点击事件
            @Override
            public void setOnItemClickListener(View itemview, View view, int postion) {//单项点击
                startActivity(new Intent(getActivity(), PictureActivity.class));
            }
            @Override
            public void OnLike(int position) {//喜欢点击
                pictureDao.add(titlelist.get(position));
                Toast.makeText(getContext(), "已添加到我的表情包", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void OnBtDelete(int position) {//删除点击
                titlelist.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "已从我的表情包删除", Toast.LENGTH_SHORT).show();
            }
        });
        ptr.setResistance(1.5f);
        //是否是下拉刷新
        //true:下拉到一定位置刷新
        //false:释放刷新
        ptr.setPullToRefresh(false);
        //头部高度的几倍再去刷新
        ptr.setRatioOfHeaderHeightToRefresh(1.5f);
        //刷新的时候，是否保持头部
        ptr.setKeepHeaderWhenRefresh(true);
        //恢复到头部的位置的时长
        ptr.setDurationToClose(200);
        //头部整体关闭的时常
        ptr.setDurationToCloseHeader(1000);
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {//放入加载数据的方法
                        ptr.refreshComplete();
                    }
                }, 2000);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void addtitle() {//添加数据源
        titlelist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            titlelist.add(new PictureBean(9527 + i, "我是好人" + i, null, null, false));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
