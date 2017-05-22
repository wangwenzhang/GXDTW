package com.huitui.gxdt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


import com.huitui.gxdt.R;


/**
 * Created by wangwenzhang on 2016/11/7.
 */
public class ShareActivity extends Activity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        btn= (Button) findViewById(R.id.share_btn);

    }

}
