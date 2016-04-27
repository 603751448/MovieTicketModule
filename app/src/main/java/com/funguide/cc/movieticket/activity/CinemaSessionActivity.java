package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.adapter.CinemaSessionPagerAdapter;
import com.funguide.cc.movieticket.views.FancyCoverFlow;
import com.funguide.cc.movieticket.views.FancyCoverFlowSampleAdapter;
import com.funguide.cc.movieticket.views.NoScrollViewPager;
import com.funguide.cc.movieticket.views.ViewPagerScroller;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CinemaSessionActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.cinema_session_CoverFlow)
    FancyCoverFlow cinemaSessionCoverFlow;
    @Bind(R.id.cinema_session_tab)
    TabLayout cinemaSessionTab;
    @Bind(R.id.cinema_session_pager)
    NoScrollViewPager cinemaSessionPager;

    String data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_session);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        data = new String[]{"周日4月24日", "周一4月25日",
                "周二4月26日", "周三4月27日", "周四4月28日",
                "周日五月29日", "周六4月30日", "周日5月1日"};

    }

    private void initView() {

        cinemaSessionCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter(this));
        cinemaSessionCoverFlow.setUnselectedAlpha(1.0f);
        this.cinemaSessionCoverFlow.setUnselectedSaturation(1.0f);
        this.cinemaSessionCoverFlow.setUnselectedScale(1.0f);
        this.cinemaSessionCoverFlow.setSpacing(10);
        this.cinemaSessionCoverFlow.setMaxRotation(0);
        this.cinemaSessionCoverFlow.setScaleDownGravity(1.0f);
        this.cinemaSessionCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        //设置默认postion
        cinemaSessionCoverFlow.setSelection(2);
        cinemaSessionCoverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CinemaSessionActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cinemaSessionPager.setAdapter(new CinemaSessionPagerAdapter(getSupportFragmentManager(),data));
        cinemaSessionTab.setupWithViewPager(cinemaSessionPager);
        ViewPagerScroller scroller = new ViewPagerScroller(this);
        scroller.setScrollDuration(0);
        scroller.initViewPagerScroll(cinemaSessionPager);
        //不可左右滑动
        cinemaSessionPager.setNoScroll(true);
    }

    @OnClick(R.id.title_back_img)
    public void onClick() {
        finish();
    }
}
