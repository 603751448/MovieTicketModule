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
 * 我的订单
 */
public class MyOderActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_oder);
        ButterKnife.bind(this);
        initData();
        initView();
    }
    private void initData() {
    }
    private void initView() {
        titleCenterTxt.setText("影票订单");
    }

    @OnClick(R.id.title_back_img)
    public void onClick() {
        finish();
    }
}
