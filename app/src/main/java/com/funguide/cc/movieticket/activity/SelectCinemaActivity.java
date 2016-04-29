package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.adapter.FilmPagerAdapter;
import com.funguide.cc.movieticket.views.HoverScrollView;
import com.funguide.cc.movieticket.views.NoScrollViewPager;
import com.funguide.cc.movieticket.views.ViewPagerScroller;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择影院
 */
public class SelectCinemaActivity extends BaseActivity {
    boolean hasMeasured = false;
    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.film_poster_pic_img)
    ImageView filmPosterPicImg;
    @Bind(R.id.film_poster_name_txt)
    TextView filmPosterNameTxt;
    @Bind(R.id.film_poster_score_txt)
    TextView filmPosterScoreTxt;
    @Bind(R.id.film_poster_introduce_txt)
    TextView filmPosterIntroduceTxt;
    @Bind(R.id.film_poster_type_txt)
    TextView filmPosterTypeTxt;
    @Bind(R.id.film_poster_runtime_txt)
    TextView filmPosterRuntimeTxt;
    @Bind(R.id.film_poster_releasetime_txt)
    TextView filmPosterReleasetimeTxt;
    @Bind(R.id.film_poster_lly)
    LinearLayout filmPosterLly;
    @Bind(R.id.film_pager)
    public   NoScrollViewPager filmPager;
    @Bind(R.id.film_tab)
    TabLayout filmTab;
    @Bind(R.id.top_lly)
    LinearLayout topLly;
    @Bind(R.id.hoverScrollView)
    HoverScrollView hoverScrollView;

    String data[];
    int topY;//滑动动态获取头部的位置
    int topHeight;//头部的高度
    int translationY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cinema);
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

        ViewTreeObserver vto =  filmPosterLly.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (hasMeasured==false){
                    topHeight=filmPosterLly.getMeasuredHeight();
                    topLly.setTranslationY(topHeight);
                    hasMeasured=true;
                }
                return true;
            }
        });
        titleCenterTxt.setText("电影名字");
        filmPager.setAdapter(new FilmPagerAdapter(getSupportFragmentManager(),data));
        filmTab.setupWithViewPager(filmPager);
        //禁止滑动动画
        final ViewPagerScroller scroller = new ViewPagerScroller(this);
        scroller.setScrollDuration(0);
        scroller.initViewPagerScroll(filmPager);
        filmPager.setNoScroll(true);
        filmTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                topLly.setTranslationY(translationY);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        hoverScrollView.setOnScrollListener(new HoverScrollView.OnScrollListener() {
            @Override
            public void onScrollchanged(int scrollY) {
                Log.e("scrollY",scrollY+"");
                if (scrollY==0){
                    topLly.setTranslationY(topHeight);
                }else {
                    int translation = Math.max(scrollY,filmPager.getTop()-topY);
                    translationY=translation;
                    topLly.setTranslationY(translation);
                }
            }
        });
        filmPosterLly.post(new Runnable() {
            @Override
            public void run() {
                topY=topLly.getTop();
            }
        });
        hoverScrollView.smoothScrollTo(0,0);
    }

    @OnClick({R.id.title_back_img,R.id.film_poster_lly})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back_img:
                finish();
                break;
            case R.id.film_poster_lly:
                startActivity(FilmDetailActivity.class);
                break;
        }
    }
}
