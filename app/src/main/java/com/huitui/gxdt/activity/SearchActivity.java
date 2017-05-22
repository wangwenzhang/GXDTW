package com.huitui.gxdt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huitui.gxdt.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by wangwenzhang on 2016/11/21.
 */
public class SearchActivity extends Activity {
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.et_input_keyword)
    EditText etInputKeyword;
    @InjectView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @InjectView(R.id.tv_search_cancel)
    TextView tvSearchCancel;
    @InjectView(R.id.rv_search)
    ListView rvSearch;
    @InjectView(R.id.tv_search_result)
    TextView tvSearchResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

    }
    @OnClick(R.id.tv_search_cancel)
    public void onClick() {
        finish();
    }
}
