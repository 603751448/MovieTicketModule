package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 影票业务热点问题
 **/
public class TicketProblemActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_problem);
        ButterKnife.bind(this);
        initData();
        initView();
    }


    public void initData() {

    }

    public void initView() {
        titleCenterTxt.setText("影票业务热点问题");
    }

    @OnClick(R.id.title_back_img)
    public void onClick() {
        finish();
    }
}
