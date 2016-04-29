package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认提交订单
 */
public class SubmitOrderActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.order_remaintime_txt)
    TextView orderRemaintimeTxt;
    @Bind(R.id.order_cinema_name_txt)
    TextView orderCinemaNameTxt;
    @Bind(R.id.order_film_name_txt)
    TextView orderFilmNameTxt;
    @Bind(R.id.order_session_txt)
    TextView orderSessionTxt;
    @Bind(R.id.order_hall_txt)
    TextView orderHallTxt;
    @Bind(R.id.order_seat_txt)
    TextView orderSeatTxt;
    @Bind(R.id.order_total_txt)
    TextView orderTotalTxt;
    @Bind(R.id.order_phone_txt)
    TextView orderPhoneTxt;
    @Bind(R.id.order_coupons_title_txt)
    TextView orderCouponsTitleTxt;
    @Bind(R.id.order_coupons_txt)
    TextView orderCouponsTxt;
    @Bind(R.id.order_ishas_coupons_txt)
    TextView orderIshasCouponsTxt;
    @Bind(R.id.order_pay_total_txt)
    TextView orderPayTotalTxt;
    @Bind(R.id.order_confirm_btn)
    Button orderConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
    }

    private void initData() {
    }

    @OnClick({R.id.title_back_img, R.id.order_ishas_coupons_txt, R.id.order_confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.order_ishas_coupons_txt:

                break;
            case R.id.order_confirm_btn:

                break;
        }
    }
}
