package com.huitui.gxdt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.huitui.gxdt.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by wangwenzhang on 2016/11/22.
 */
public class JokeActivity extends Activity {
    @InjectView(R.id.activity_joke_cancel)
    TextView activityJokeCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.activity_joke_cancel)
    public void onClick() {
        finish();
    }
}
