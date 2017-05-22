package com.huitui.gxdt.fragment;


import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huitui.gxdt.R;
import com.huitui.gxdt.activity.MainActivity;
import com.huitui.gxdt.activity.SearchActivity;
import com.huitui.gxdt.adapter.MyPagerAdapder;
import com.huitui.gxdt.bean.PictureBean;
import com.huitui.gxdt.contents.DepthPageTransformer;
import com.huitui.gxdt.contents.SizeHelper;
import com.huitui.gxdt.yuliu.PictureDao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class FristFragment extends Fragment {
    @InjectView(R.id.fragmen_frist_ll)
    LinearLayout fragmenFristLl;
    @InjectView(R.id.fragmen_frist_hsv)
    HorizontalScrollView fragmenFristHsv;
    @InjectView(R.id.fragment_frist_main)
    ViewPager fragmentFristvp;
    @InjectView(R.id.fragmen_frist_search)
    ImageView fragmenFristSearch;
    /* @InjectView(R.id.fragmen_frist_rcyview)
     RecyclerView fragmenFristRcyview;*/
    private String[] title = new String[]{"精选", "分类1", "分类2", "分类3", "分类4"};
    private List<PictureBean> titlelist;
    private RadioGroup myRadioGroup;
    private float mCurrentCheckedRadioLeft;
    private PictureDao pictureDao;
    private List<Fragment> fragmentList;
    private int previousIndex;
    private MainActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frist, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        addview();
       /* addtitle();
        pictureDao=new PictureDao(getActivity());
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
                Toast.makeText(getContext(),"已添加到我的表情包",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void OnBtDelete(int position) {//删除点击
            titlelist.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(),"已从我的表情包删除",Toast.LENGTH_SHORT).show();
            }
        });*/
        addFragment();
        MyPagerAdapder adapder = new MyPagerAdapder(getChildFragmentManager(), fragmentList);
        fragmentFristvp.setAdapter(adapder);
        fragmentFristvp.setOffscreenPageLimit(4);
        //previousIndex=0;
        //showFragment(0,previousIndex);
        fragmentFristvp.setPageTransformer(true, new DepthPageTransformer());
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // List<PictureBean> list= DataBaseUtils.getDatabse();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                int index = group.indexOfChild(rb);
                mCurrentCheckedRadioLeft = rb.getLeft();//更新当前按钮距离左边的距离
                int width = (int) SizeHelper.dp2px(getActivity(), 140);
                fragmenFristHsv.smoothScrollTo((int) mCurrentCheckedRadioLeft - width, 0);
               /* showFragment(index,previousIndex);
                previousIndex=index;*/
                fragmentFristvp.setCurrentItem(index);
            }
        });
        fragmentFristvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) myRadioGroup.getChildAt(position);//ViewPager的下标对应的Button
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设定默认被选中的选项卡为第一项
        if (title != null) {
            myRadioGroup.check(myRadioGroup.getChildAt(0).getId());
        }
        super.onViewCreated(view, savedInstanceState);
    }

    private void addview() {//动态添加 RadioButton
        myRadioGroup = new RadioGroup(getActivity());
        myRadioGroup.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        myRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        fragmenFristLl.addView(myRadioGroup);
        for (int i = 0; i < title.length; i++) {
            RadioButton radio = new RadioButton(getActivity());
            radio.setButtonDrawable(android.R.color.transparent);
            radio.setBackgroundResource(R.drawable.radiobtn_selector);
            ColorStateList csl = getResources().getColorStateList(R.color.radiobtn_text_color);
            radio.setTextColor(csl);
            LinearLayout.LayoutParams l = new LinearLayout.LayoutParams((int) SizeHelper.dp2px(getActivity(), 80), ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            radio.setLayoutParams(l);
            radio.setTextSize(15);
            radio.setGravity(Gravity.CENTER);
            radio.setText(title[i]);
            myRadioGroup.addView(radio);
        }
    }

    /* private void addtitle() {//添加数据源
         titlelist = new ArrayList<>();
         for (int i = 0; i < 10; i++) {
             titlelist.add(new PictureBean(9527 + i, "我是好人" + i, null, null,false));
         }
     }*/
    private void addFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FristSelectFragment());
        fragmentList.add(new FristSelectFragment());
        fragmentList.add(new FristSelectFragment());
        fragmentList.add(new FristSelectFragment());
        fragmentList.add(new FristSelectFragment());
       /* fragmentList.add(new MyThreeFragment());
        fragmentList.add(new MySecondFragment());
        fragmentList.add(new FristSelectFragment());
        fragmentList.add(new FristSelectFragment());
        fragmentList.add(new FristSelectFragment());*/
    }

    /* private void showFragment(int showIndex, int hideIndex) {
         Fragment showFragment = fragmentList.get(showIndex);
         Fragment hideFragment = fragmentList.get(hideIndex);
         FragmentTransaction ft = getChildFragmentManager().beginTransaction();
         if (!showFragment.isAdded()) {
             ft.replace(R.id.fragment_frist_main, showFragment);
         }
         if (showIndex == hideIndex) {
             ft.show(showFragment).commit();
         } else {
             ft.show(showFragment).hide(hideFragment).commit();
         }

     }*/
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

    @OnClick(R.id.fragmen_frist_search)
    public void onClick() {
        startActivity(new Intent(getActivity(), SearchActivity.class));
        /*overridePendingTransition(R.anim.fade_out,R.anim.fade_in);*/
    }
}
