package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaDetialActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.cinema_detial_name_txt)
    TextView cinemaDetialNameTxt;
    @Bind(R.id.cinema_detial_addre_txt)
    TextView cinemaDetialAddreTxt;
    @Bind(R.id.cinema_detial_tell_img)
    ImageView cinemaDetialTellImg;
    @Bind(R.id.cinema_detial_tell_txt)
    TextView cinemaDetialTellTxt;
    @Bind(R.id.cinema_detial_tell2_txt)
    TextView cinemaDetialTell2Txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detial);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        titleCenterTxt.setText("影院详情");


    }


    @OnClick(R.id.title_back_img)
    public void onClick() {
        finish();
    }
}
