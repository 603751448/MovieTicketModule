package com.funguide.cc.movieticketmodule.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticketmodule.BaseActivity;
import com.funguide.cc.movieticketmodule.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 模糊查询
 **/
public class FuzzySearchActivity extends BaseActivity {
    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.title_right_txt)
    TextView titleRightTxt;
    @Bind(R.id.title_right_img)
    ImageView titleRightImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuzzy_search);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {

    }

    public void initView() {
        titleCenterTxt.setText("影院搜索");
    }

    @OnClick(R.id.title_back_img)
    public void onClick() {
        finish();
    }
}
