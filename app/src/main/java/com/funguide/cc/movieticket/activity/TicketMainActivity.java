package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.adapter.TicketAdvAdapter;
import com.funguide.cc.movieticket.adapter.ViewHolder;
import com.funguide.cc.movieticket.adapter.listview.CommonAdapter;
import com.funguide.cc.movieticket.model.Cinema;
import com.funguide.cc.movieticket.model.Film;
import com.funguide.cc.movieticket.utils.GlideUtils;
import com.funguide.cc.movieticket.views.AllGridView;
import com.funguide.cc.movieticket.views.AllListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 影票首页
 **/
public class TicketMainActivity extends BaseActivity {
    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.title_right_txt)
    TextView titleRightTxt;
    @Bind(R.id.title_right_img)
    ImageView titleRightImg;
    @Bind(R.id.ticket_main_viewpager)
    ViewPager ticketMainViewpager;
    @Bind(R.id.viewpager_point1_img)
    ImageView viewpagerPoint1Img;
    @Bind(R.id.viewpager_point2_img)
    ImageView viewpagerPoint2Img;
    @Bind(R.id.viewpager_point3_img)
    ImageView viewpagerPoint3Img;
    @Bind(R.id.viewpager_point_lly)
    LinearLayout viewpagerPointLly;
    @Bind(R.id.ticket_allfilm_lly)
    LinearLayout ticketAllfilmLly;
    @Bind(R.id.ticket_allcinema_lly)
    LinearLayout ticketAllcinemaLly;
    @Bind(R.id.ticket_myticket_lly)
    LinearLayout ticketMyticketLly;
    @Bind(R.id.ticket_hot_film_txt)
    TextView ticketHotFilmTxt;
    @Bind(R.id.ticket_hot_film_grid)
    AllGridView ticketHotFilmGrid;
    @Bind(R.id.ticket_recommend_cinema_txt)
    TextView ticketRecommendCinemaTxt;
    @Bind(R.id.ticket_recommend_cinema_list)
    AllListView ticketRecommendCinemaList;
    /**
     * 热门电影
     **/
    List<Film> hotFilms = new ArrayList<>();
    /**
     * 推荐影院
     **/
    List<Cinema> recommendCinemas = new ArrayList<>();
    private List<View> viewLists;
    private int[] imageResId; // 图片ID
    private int currentItem = 0; // 当前图片的索引号
    private List<View> dots; // 图片标题正文的那些点
    private ScheduledExecutorService scheduledExecutorService;
    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            ticketMainViewpager.setCurrentItem(currentItem);// 切换当前显示的图片
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    /**
     * 初始化数据
     **/
    public void initData() {
        initBannerData();
        for (int i = 0; i < 6; i++) {
            Cinema cinema = new Cinema();
            cinema.setAddre("北京");
            recommendCinemas.add(cinema);
        }
        for (int i = 0; i < 6; i++) {
            Film film = new Film();
            film.setFilmName("荒野猎人" + i);
            film.setScore("9.9");
            film.setPicHor("http://www.xueliedu.com/uploads/allimg/151214/23110WB8_0.jpg");
            hotFilms.add(film);
        }
    }

    /**
     * 初始化布局
     **/
    public void initView() {
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.VISIBLE);
        titleRightImg.setBackgroundResource(R.mipmap.wenhao);
        ticketMainViewpager.setAdapter(new TicketAdvAdapter(this, viewLists));
        // 设置一个监听器，当ViewPager中的页面改变时调用
        ticketMainViewpager.setOnPageChangeListener(new MyPageChangeListener());
        ticketHotFilmGrid.setAdapter(new CommonAdapter<Film>(this, R.layout.layout_hot_film_item, hotFilms) {
            @Override
            public void convert(ViewHolder holder, int postion, Film film) {
                holder.setText(R.id.hot_film_name_txt, film.getFilmName());
                holder.setText(R.id.hot_film_score_txt, film.getScore());
                holder.setImageUrl(R.id.hot_film_picHor_img, film.getPicHor());
            }
        });

        ticketHotFilmGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("film", hotFilms.get(position));
                startActivity(SelectCinemaActivity.class, bundle);
            }
        });

        ticketRecommendCinemaList.setAdapter(new CommonAdapter<Cinema>(this, R.layout.layout_recommen_cinema_item, recommendCinemas) {
            @Override
            public void convert(ViewHolder holder, int postion, Cinema cinema) {
                holder.setText(R.id.cinema_addre_txt, cinema.getAddre());
            }
        });
        ticketRecommendCinemaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("film", recommendCinemas.get(position));
                startActivity(CinemaSessionActivity.class, bundle);
            }
        });
    }

    /**
     * 初始化banner数据
     **/
    private void initBannerData() {
        viewLists = new ArrayList<>();
        dots = new ArrayList<>();
        imageResId = new int[]{R.drawable.a, R.drawable.b, R.drawable.c};
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(this);
            GlideUtils.loadImagByResource(this, imageResId[i], imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imageResId[i]);
            viewLists.add(imageView);
        }
        dots.add(viewpagerPoint1Img);
        dots.add(viewpagerPoint2Img);
        dots.add(viewpagerPoint3Img);
    }


    @OnClick({R.id.title_back_img, R.id.title_right_img, R.id.ticket_allfilm_lly,
            R.id.ticket_allcinema_lly, R.id.ticket_myticket_lly,
            R.id.ticket_hot_film_txt, R.id.ticket_recommend_cinema_txt
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.title_right_img://疑问题
                startActivity(TicketProblemActivity.class);
                break;
            case R.id.ticket_allfilm_lly:     //全部影片
                startActivity(AllFilmActivity.class);
                break;
            case R.id.ticket_allcinema_lly:    //全部影院
                startActivity(AllCinemaActivity.class);
                break;
            case R.id.ticket_myticket_lly:    // 我的影票
                startActivity(MyTicketActivity.class);
                break;
            case R.id.ticket_hot_film_txt://热门影片---》全部
                startActivity(AllFilmActivity.class);
                break;
            case R.id.ticket_recommend_cinema_txt: //推荐影院---》全部
                startActivity(AllCinemaActivity.class);
                break;
        }
    }


    /**
     * 换行切换任务
     **/
    private class ScrollTask implements Runnable {
        public void run() {
            synchronized (ticketMainViewpager) {
                System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % viewLists.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }
    }

    /**
     * 当ViewPager中页面的状态发生改变时调用
     **/
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(final int position) {
            currentItem = position;
            dots.get(oldPosition).setBackgroundResource(R.mipmap.dot1_w);
            dots.get(position).setBackgroundResource(R.mipmap.dot2_w);
            oldPosition = position;
            viewLists.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(TicketMainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }


    @Override
    protected void onStart() {
        Log.d("main", "onStart");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
        super.onStop();
    }
}

