package com.huitui.gxdt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.huitui.gxdt.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by wangwenzhang on 2016/11/1.第一次登陆的时的页面
 */
public class FristActivity extends Activity {
    @InjectView(R.id.frist_vp)
    ViewPager fristVp;
    @InjectView(R.id.frist_btn)
    Button fristBtn;
    private List<View> viewList;
    //private int[] viewarray=new int[]{R.mipmap.banner_page1,R.mipmap.banner_pager2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        ButterKnife.inject(this);
        viewList=new ArrayList<>();
        LayoutInflater inflater=getLayoutInflater();
        //indata();
        View view1=inflater.inflate(R.layout.frist_vp1,null);
        View view2=inflater.inflate(R.layout.frist_vp2,null);
        viewList.add(view1);
        viewList.add(view2);
        PagerAdapter pagerAdapter = new PagerAdapter() {//viewpager的适配器

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };
        fristVp.setAdapter(pagerAdapter);
        fristVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==1){
                    fristBtn.setVisibility(View.VISIBLE);//当滑动到最后一页时  跳转的Button就显示出来
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick(R.id.frist_btn)
    public void onClick() {
        startActivity(new Intent(FristActivity.this,MainActivity.class));//点击跳转回主页面
        finish();
    }
    /*private void indata(){
        for (int i=0;i<viewarray.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            viewList.add(imageView);
        }
    }*/
}
