package com.huitui.gxdt.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.huitui.gxdt.R;
import com.huitui.gxdt.adapter.PictureAdapter;
import com.huitui.gxdt.bean.BiaoQingBean;
import com.huitui.gxdt.util.SpaceItemDecoration;
import com.huitui.gxdt.util.UrlString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangwenzhang on 2016/11/17.
 */
public class PictureActivity extends Activity {
    @InjectView(R.id.picture_fanhui)
    TextView pictureFanhui;

    @InjectView(R.id.activity_picture_rcv)
    RecyclerView activityPictureRcv;
    @InjectView(R.id.sharepop_image)
    ImageView sharepopImage;
    @InjectView(R.id.sharepop_sharebtn)
    Button sharepopSharebtn;
    @InjectView(R.id.sharepop_downbtn)
    Button sharepopDownbtn;
    @InjectView(R.id.activity_picture_pop)
    LinearLayout activityPicturePop;
   /* @InjectView(R.id.activity_picture_sll)
    NestedScrollView activityPictureSll;*/
    private List<BiaoQingBean.NewsBean> newsBeanList;
    private PictureAdapter adapter;
    private String imageURl = null;
    private String imagename = null;
    private Bitmap bitmap1;
    private String path = Environment.getExternalStorageDirectory().getPath() + "/zcdt";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.inject(this);
        Config.dialogSwitch = false;
        loadData();
        pictureFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activityPictureRcv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        adapter = new PictureAdapter(this, newsBeanList);
        activityPictureRcv.setAdapter(adapter);

        activityPictureRcv.addItemDecoration(new SpaceItemDecoration(5));
        adapter.setOnItemClickListener(new PictureAdapter.MyItemClickListener() {
            @Override
            public void setOnItemClickListener(View itemview, View view, int postion) {
                //showPopWindow(newsBeanList.get(postion), newsBeanList.get(postion).getId() + "");
                activityPicturePop.setVisibility(View.VISIBLE);
                ShareDown(newsBeanList.get(postion).getFurl());
                //Glide.with(PictureActivity.this).load(newsBeanList.get(postion).getFurl()).crossFade().into(sharepopImage);
            }
        });
        activityPictureRcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                activityPicturePop.setVisibility(View.GONE);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
       /* activityPictureSll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                activityPicturePop.setVisibility(View.GONE);
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(PictureActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            Log.d("plat", "platform" + platform);

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(PictureActivity.this, platform + " 分享失败啦" + t.getMessage(), Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(PictureActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    private void ShareDown(final String URL){
        Glide.with(PictureActivity.this).load(URL).crossFade().into(sharepopImage);
        sharepopSharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(URL);
            }
        });
        sharepopDownbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute(URL);
                Toast.makeText(PictureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
   /* private void showPopWindow(final BiaoQingBean.NewsBean url, final String name) {
        LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = lf.inflate(R.layout.sharepop, null);
        PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);//设置可点击
        popupWindow.setTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);//透明度
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);//动画
        popupWindow.showAtLocation(pictureFanhui, Gravity.BOTTOM, 0, 0);//底部弹出
        ImageView popimage = (ImageView) view.findViewById(R.id.sharepop_image);
       *//* int higth=url.getHeight();
        if (higth>=240){
            higth=240;
        }
        popimage.setLayoutParams(new LinearLayout.LayoutParams(182,higth));*//*
        Glide.with(this).load(url.getFurl()).crossFade().into(popimage);
        Button sharebtn = (Button) view.findViewById(R.id.sharepop_sharebtn);
        Button downbtn = (Button) view.findViewById(R.id.sharepop_downbtn);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(url.getFurl());
            }
        });
        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute(url.getFurl());
                Toast.makeText(PictureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    private void showShare(String URL) {//分享
        UMImage image = new UMImage(this, URL);
        new ShareAction(this).withMedia(image)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(umShareListener).open();
    }

    private void loadData() {//获取数据
        newsBeanList = new ArrayList<>();
        BiaoQingBean biaoQingBean = new Gson().fromJson(UrlString.biaoqing, BiaoQingBean.class);
        newsBeanList.addAll(biaoQingBean.getNews());
    }


    class Task extends AsyncTask<String, Integer, Void> {

        protected Void doInBackground(String... params) {
            GetImageInputStream(params[0]);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    public void GetImageInputStream(String imageurl) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(imageurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(4000); //超时设置
            connection.setDoInput(true);
            connection.setUseCaches(false); //设置不使用缓存
            InputStream inputStream = connection.getInputStream();
            SavaImage(inputStream, path);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SavaImage(InputStream inputStream, String path) {
        File file = new File(path);
        FileOutputStream fileOutputStream = null;
        //文件夹不存在，则创建它
        if (!file.exists()) {
            file.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".gif";
        File filena = new File(file, fileName);
        try {
            int i = 0;
            fileOutputStream = new FileOutputStream(filena);
            byte[] bytes = new byte[2048];
            while ((i = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, i);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MediaStore.Images.Media.insertImage(PictureActivity.this.getContentResolver(),//将图片插入系统图库
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//保存成功，通知系统更新相册
        Uri uri = Uri.fromFile(filena);
        intent.setData(uri);
        getApplicationContext().sendBroadcast(intent);
    }
}
