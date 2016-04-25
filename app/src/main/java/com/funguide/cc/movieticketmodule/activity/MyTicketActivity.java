package com.funguide.cc.movieticketmodule.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.funguide.cc.movieticketmodule.BaseActivity;
import com.funguide.cc.movieticketmodule.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的影票
 */

public class MyTicketActivity extends BaseActivity {


    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.title_right_txt)
    TextView titleRightTxt;
    @Bind(R.id.ticket_order_rly)
    RelativeLayout ticketOrderRly;
    @Bind(R.id.ticket_coupon_rly)
    RelativeLayout ticketCouponRly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {

    }

    public void initView() {
        titleCenterTxt.setText("我的影票");
    }


    @OnClick({R.id.title_back_img,R.id.ticket_order_rly, R.id.ticket_coupon_rly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.ticket_order_rly:
                startActivity(MyOderActivity.class);
                break;
            case R.id.ticket_coupon_rly:
                startActivity(MyCouponActivity.class);
                break;
        }
    }
}
