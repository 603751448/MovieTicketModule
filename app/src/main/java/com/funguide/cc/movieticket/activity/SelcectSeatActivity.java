package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在线选座
 */
public class SelcectSeatActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.select_seat_web)
    WebView selectSeatWeb;
    @Bind(R.id.select_seat_phone_edit)
    EditText selectSeatPhoneEdit;
    @Bind(R.id.select_seat_btn)
    Button selectSeatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selcect_seat);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initData() {

    }

    private void initView() {
    }

    @OnClick({R.id.title_back_img, R.id.select_seat_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.select_seat_btn:
                startActivity(SubmitOrderActivity.class);
                break;
        }
    }
}
