package com.huitui.gxdt.app;

import android.app.Application;
import android.os.Environment;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.huitui.gxdt.yuliu.DataBaseHelper1;

import java.io.File;

/**
 * Created by wangwenzhang on 2016/11/1.
 */
public class App extends Application {
    String path = Environment.getExternalStorageDirectory().getPath() + "/zcdt";
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        DataBaseHelper1.getInstace(this);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    {
        PlatformConfig.setQQZone("1105810027", "IWFFYKCZeJoVOEYc");
    }
}
