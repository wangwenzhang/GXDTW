package com.huitui.gxdt.contents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.huitui.gxdt.activity.BaseActivity;
import com.huitui.gxdt.util.NetUtil;

/**
 * Created by wangwenzhang on 2016/11/29.
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    public NetEvevt evevt = BaseActivity.evevt;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            evevt.onNetChange(netWorkState);
        }
    }
    // 自定义接口
    public interface NetEvevt {
        void onNetChange(int netMobile);
    }
}
