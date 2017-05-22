package com.huitui.gxdt.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import com.huitui.gxdt.R;
import com.huitui.gxdt.adapter.MyPagerAdapder;
import com.huitui.gxdt.bean.UpdateBean;
import com.huitui.gxdt.contents.NetBroadcastReceiver;
import com.huitui.gxdt.contents.UpdateService;
import com.huitui.gxdt.fragment.FourFragment;
import com.huitui.gxdt.fragment.FristFragment;
import com.huitui.gxdt.fragment.SecondFragment;
import com.huitui.gxdt.fragment.ThreeFragment;
import com.huitui.gxdt.util.NetUtil;
import com.huitui.gxdt.util.OkhttpUtils;
import com.huitui.gxdt.util.UrlString;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.activity_main_rp)
    RadioGroup activityMainRp;
    @InjectView(R.id.activity_main_vp)
    FrameLayout activityMainVp;
    private List<Map<String, String>> title;
    private List<Fragment> fragmentList;
    private int previousIndex;
    private boolean isShow;
    private String newVersion="";
    private String oldVersion="";
    private NetBroadcastReceiver netBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.UMAnalyticsConfig umAnalyticsConfig=new MobclickAgent.UMAnalyticsConfig(this,"5850a092c62dca0408001945","Baidu", MobclickAgent.EScenarioType. E_UM_NORMAL);
        MobclickAgent. startWithConfigure(umAnalyticsConfig);
       /* MobclickAgent.setDebugMode( true );*/
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Network();
        detection();
        Log.i("旧版本",oldVersion);
        //newVersion="1.0";
        Log.i("这是最新版本",newVersion);
       /* if (oldVersion.equals(newVersion)){
            Log.i("执行了么","en");
            Update();
        }*/
       // Update();
        Log.i("执行了么1111","en");
        netBroadcastReceiver=new NetBroadcastReceiver();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(netBroadcastReceiver, filter);
        addfragment();
        Log.i("执行了么1111","en");
        MyPagerAdapder adapder = new MyPagerAdapder(getSupportFragmentManager(), fragmentList);
        RadioButton rb = (RadioButton) activityMainRp.getChildAt(0);//设置第一个Button默认选中
        rb.setChecked(true);
        previousIndex=0;
        showFragment(0,previousIndex);
        //activityMainVp.setPageTransformer(true,new DepthPageTransformer());
        activityMainRp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//对选项卡的点击监听
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);//选中的Button
                int index = group.indexOfChild(rb);
                showFragment(index,previousIndex);
                previousIndex=index;
                if (index==3){
                    if (buttonClickedListener!=null){
                        buttonClickedListener.onclicked();
                    }
                    if (onJokeButtonClickLinstener!=null){
                        onJokeButtonClickLinstener.onchange();
                    }
                }
            }
        });
    }
    private void addfragment() {//添加fragment数据源
        fragmentList = new ArrayList<>();
        fragmentList.add(new FristFragment());
        fragmentList.add(new SecondFragment());
        //fragmentList.add(new ThreeFragment());
        fragmentList.add(new FourFragment());
    }
    private void showFragment(int showIndex, int hideIndex) {//fragment管理
        Fragment showFragment = fragmentList.get(showIndex);
        Fragment hideFragment = fragmentList.get(hideIndex);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!showFragment.isAdded()) {
            ft.add(R.id.activity_main_vp, showFragment);
        }
        if (showIndex == hideIndex) {
            ft.show(showFragment).commit();
        } else {
            ft.show(showFragment).hide(hideFragment).commit();
        }
    }
    private void detection(){
        try {
            OkhttpUtils.get(UrlString.Update, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    /*if (response==null){
                        newVersion="2.0";
                    }else {*/
                       String result=response.body().string();
                        Log.i("axaiskajsia",result);
                        UpdateBean updateBean=new Gson().fromJson(result,UpdateBean.class);
                        newVersion=updateBean.getResult().getVersion();
                    final String upURl=updateBean.getResult().getLink();
                    Log.i("网站",updateBean.getResult().getLink());
                    //newVersion="1.0";
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            PackageManager pm = getApplicationContext().getPackageManager();
                            PackageInfo pi = null;
                            try {
                                pi = pm.getPackageInfo(getApplicationContext().getPackageName(), 0);
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                            oldVersion = pi.versionName;
                            if (!oldVersion.equals(newVersion)){
                                Log.i("执行了么","en");
                                Update(upURl);
                            }
                        }
                    });
                    Log.i("新版本",newVersion);
                    //}

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNetChange(int netMobile) {//网络状态发生改变时调用
        super.onNetChange(netMobile);
        if (netMobile!= NetUtil.NETWORK_WIFI){
            Network();
        }
        else {
        }
    }
    private void Update(final String URL){
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("检测到有版本需要更新");
        dialog.setCancelable(false);
        Log.i("新版本",newVersion);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, UpdateService.class);
                intent.putExtra("apk_url",URL);
                startService(intent);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void Network(){//提示使用无线网的dialog

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            final String netname=networkInfo.getTypeName();
            if (!netname.equalsIgnoreCase("WIFI")){
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("检测到大神你没有使用无线网");
                dialog.setCancelable(false);
                dialog.setPositiveButton("本神神力多", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("赶紧带我去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this,"不好意思不会",Toast.LENGTH_SHORT).show();
                        try {
                            //不关闭
                            isShow=false;
                            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                            field.setAccessible(true);
                            field.set(dialog, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                if((isShow==false)){//摇一摇之后立即切换界面时也会出现dialog
                    dialog.show();
                    isShow=true;
                }
                //dialog.create().show();
            }
        }
       else {
            Toast.makeText(MainActivity.this,"没有网络连接",Toast.LENGTH_SHORT).show();
        }
    }
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private OnButtonClickedListener buttonClickedListener=null;
    public interface OnButtonClickedListener {
        void onclicked();
    }
    public void setButtonClickedListener(OnButtonClickedListener buttonClickedListener) {
        this.buttonClickedListener = buttonClickedListener;
    }
    private OnJokeButtonClickLinstener onJokeButtonClickLinstener=null;
    public interface OnJokeButtonClickLinstener{
        void onchange();
    }
    public void setJokeButtonclickLinstener(OnJokeButtonClickLinstener onJokeButtonClickLinstener){
        this.onJokeButtonClickLinstener=onJokeButtonClickLinstener;
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(netBroadcastReceiver);
        super.onDestroy();
    }
}
