package com.funguide.cc.movieticketmodule.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticketmodule.BaseActivity;
import com.funguide.cc.movieticketmodule.R;
import com.funguide.cc.movieticketmodule.adapter.TicketAdvAdapter;
import com.funguide.cc.movieticketmodule.utils.GlideUtils;

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
    /**
     * 广告布局控件
     **/
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
    @Bind(R.id.ticket_allfilm_txt)
    TextView ticketAllfilmTxt;
    @Bind(R.id.ticket_allcinema_txt)
    TextView ticketAllcinemaTxt;

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

        ;
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
    }

    @OnClick({R.id.title_back_img, R.id.title_right_img, R.id.ticket_allfilm_lly,
            R.id.ticket_allcinema_lly, R.id.ticket_myticket_lly,
            R.id.ticket_allfilm_txt, R.id.ticket_allcinema_txt
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.title_right_img:
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
            case R.id.ticket_allfilm_txt:////全部影片
                startActivity(AllFilmActivity.class);
                break;
            case R.id.ticket_allcinema_txt: //全部影院
//                startActivity(AllCinemaActivity.class);
                startActivity(CinemaSessionActivity.class);
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


    @Override
    protected void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    protected void onStop() {
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
        super.onStop();
    }
}

