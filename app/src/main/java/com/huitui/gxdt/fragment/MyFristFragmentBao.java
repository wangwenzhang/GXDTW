package com.huitui.gxdt.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.huitui.gxdt.R;
import com.huitui.gxdt.activity.MainActivity;
import com.huitui.gxdt.activity.PictureActivity;
import com.huitui.gxdt.adapter.HomeAdapter;
import com.huitui.gxdt.adapter.PictureAdapter1;
import com.huitui.gxdt.bean.PictureBean;
import com.huitui.gxdt.util.SpaceItemDecoration;
import com.huitui.gxdt.yuliu.PictureDao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangwenzhang on 2016/11/10.
 */
public class MyFristFragmentBao extends Fragment {
    @InjectView(R.id.fragmen_my_frist_rg)
    RadioGroup fragmenMyFristRg;
    @InjectView(R.id.fragment_my_frist_bao_image2)
    RecyclerView fragmentMyFristBaoImage2;
    @InjectView(R.id.fragment_my_frist_bao_image1)
    RecyclerView fragmentMyFristBaoImage1;
    private Button b1, b2;
    private ImageView iv1, iv2;
    private List<PictureBean> pictureBeanList;
    private PictureDao pictureDao;
    private HomeAdapter homeAdapter;
    private MainActivity mactivity;
    private List<String> imagepath;
    private PictureAdapter1 pictureAdapter1;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mactivity= (MainActivity) activity;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_frist_bao, container, false);
        ButterKnife.inject(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RadioButton rb = (RadioButton) fragmenMyFristRg.getChildAt(0);//设置第一个Button默认选中
        rb.setChecked(true);
        fragmenMyFristRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);//选中的Button

                int index = group.indexOfChild(rb);
                if (index == 0) {
                    fragmentMyFristBaoImage2.setVisibility(View.GONE);
                    fragmentMyFristBaoImage1.setVisibility(View.VISIBLE);
                } else {
                    fragmentMyFristBaoImage1.setVisibility(View.GONE);
                    fragmentMyFristBaoImage2.setVisibility(View.VISIBLE);
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());//布局管理
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentMyFristBaoImage1.setLayoutManager(layoutManager);
        pictureBeanList = new ArrayList<>();
        pictureDao = new PictureDao(getContext());
        pictureBeanList.addAll(pictureDao.getDatabse());//获取数据库中的数据源
        //basAdapter=new BasAdapter(pictureBeanList,getActivity());
        homeAdapter=new HomeAdapter(getContext(), pictureBeanList);
        homeAdapter.registerAdapterDataObserver(mObserver);
        fragmentMyFristBaoImage1.setAdapter(homeAdapter);
        fragmentMyFristBaoImage1.setItemAnimator(new DefaultItemAnimator());

        homeAdapter.setOnItemClickListener(new HomeAdapter.MyItemClickListener() {//点击事件
            @Override
            public void setOnItemClickListener(View itemview, View view, int postion) {
                startActivity(new Intent(getActivity(), PictureActivity.class));
            }
            @Override
            public void OnLike(int position) {
            }
            @Override
            public void OnBtDelete(int position) {
                PictureBean id = pictureBeanList.get(position);
                pictureDao.removeData(id);
                pictureBeanList.remove(position);
               /* pictureBeanList.clear();
                pictureBeanList.addAll(pictureDao.getDatabse());*/
                homeAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),"已从我的表情包中删除",Toast.LENGTH_SHORT).show();
            }
        });
        fragmentMyFristBaoImage2.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        fragmentMyFristBaoImage2.addItemDecoration(new SpaceItemDecoration(5));
        imagepath=new ArrayList<>();
        imagepath=getImagePathFromSD();
        pictureAdapter1=new PictureAdapter1(getContext(),imagepath);
        fragmentMyFristBaoImage2.setAdapter(pictureAdapter1);
        pictureAdapter1.setOnItemClickListener(new PictureAdapter1.MyItemClickListener() {
            @Override
            public void setOnItemClickListener(View itemview, View view, int postion) {
                showShare(imagepath.get(postion));
            }
        });
        mactivity.setButtonClickedListener(new MainActivity.OnButtonClickedListener() {//实现当Activity下面选项卡被点击时 重新加载数据
            @Override
            public void onclicked() {
                pictureBeanList.clear();
                pictureBeanList.addAll(pictureDao.getDatabse());
                homeAdapter.notifyDataSetChanged();
                imagepath.clear();
                imagepath.addAll(getImagePathFromSD());
                pictureAdapter1.notifyDataSetChanged();

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
        }
    };
    private List<String> getImagePathFromSD() {
        // 图片列表
        List<String> imagePathList = new ArrayList<String>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/zcdt";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath())) {
                imagePathList.add(file.getPath());
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }
    private boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg")|| FileEnd.equals("bmp") ) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            Log.d("plat", "platform" + platform);

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), platform + " 分享失败啦" + t.getMessage(), Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    private void showShare(String URL) {//分享
        UMImage image = new UMImage(getActivity(), BitmapFactory.decodeFile(URL));
        new ShareAction(getActivity()).withMedia(image)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(umShareListener).open();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (homeAdapter != null) {
            homeAdapter.unregisterAdapterDataObserver(mObserver);
        }
        ButterKnife.reset(this);
    }
}
