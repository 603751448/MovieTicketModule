package com.funguide.cc.movieticket.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.adapter.CouponTabPageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的优惠券
 */
public class MyCouponActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.coupon_tab)
    TabLayout couponTab;
    @Bind(R.id.coupon_pager)
    ViewPager couponPager;
    String []couponItemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        couponItemName=new String[]{"可使用","已使用","已作废"};
    }

    private void initView() {
        titleCenterTxt.setText("我的优惠券");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            couponTab.setLayoutMode(TabLayout.GRAVITY_CENTER);
        }
        couponPager.setAdapter(new CouponTabPageAdapter(getSupportFragmentManager(),couponItemName));
        couponTab.setupWithViewPager(couponPager);

    }

    @OnClick(R.id.title_back_img)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back_img:
                finish();
                break;
        }
    }
}
