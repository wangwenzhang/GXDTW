package com.huitui.gxdt.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huitui.gxdt.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by wangwenzhang on 2016/11/8.
 */
public class StartActivity extends Activity {
    @InjectView(R.id.start_iamge)
    ImageView startIamge;
    @InjectView(R.id.start_time)
    TextView startTime;
    @InjectView(R.id.start_tiao)
    TextView startTiao;
    private String SHARE_APP_TAG="frist";
    private int timer=3;
     final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startTime.setText(timer + "秒");
                    timer--;
                    if (timer == 0) {
                        IfStart();
                        handler.removeMessages(1);
                    }else {
                        Message message=handler.obtainMessage(1);
                        handler.sendMessageDelayed(message,1000);
                    }
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.inject(this);
        Message message=handler.obtainMessage(1);
        handler.sendMessageDelayed(message,1000);
    }
    @OnClick({R.id.start_iamge, R.id.start_time, R.id.start_tiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_iamge:
                break;
            case R.id.start_time:
                break;
            case R.id.start_tiao:
                IfStart();
                handler.removeMessages(1);
                break;
        }
    }
    private void IfStart(){
        SharedPreferences setting = getSharedPreferences(SHARE_APP_TAG, 0);//判断是否是第一次启动
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            Toast.makeText(StartActivity.this, "第一次", Toast.LENGTH_LONG).show();
            startActivity(new Intent(StartActivity.this,FristActivity.class));
            finish();
        }else{
            startActivity(new Intent(StartActivity.this,MainActivity.class));
            Toast.makeText(StartActivity.this, "欢迎回来", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
