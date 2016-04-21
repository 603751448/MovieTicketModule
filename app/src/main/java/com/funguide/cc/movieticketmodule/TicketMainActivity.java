package com.funguide.cc.movieticketmodule;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;

import com.funguide.cc.movieticketmodule.adapter.AdvAdapter;
import com.funguide.cc.movieticketmodule.adapter.RecommendedCinemaAcapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 影票首页
 **/
public class TicketMainActivity extends BaseActivity {
    private ImageView back;
    private ImageView iv_problem;
    private ImageView[] imageViews = null;  	/**VP 开始 **/
    private ImageView imageView = null;
    private ViewPager advPager = null;
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;         /**  VP结束 **/
    private ListView lv_tuijianyingyuan; // 推荐影院
    private RecommendedCinemaAcapter recommendedCinemaAcapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:          // 返回
                this.finish();
                break;
            case R.id.iv_problem:    // 影票业务热点问题
                Intent intent = new Intent(TicketMainActivity.this, TicketProblemActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initView() {
        lv_tuijianyingyuan = (ListView) this.findViewById(R.id.lv_tuijianyingyuan);
        iv_problem = (ImageView) this.findViewById(R.id.iv_problem);
        back = (ImageView) this.findViewById(R.id.back);
        iv_problem.setOnClickListener(this);
        back.setOnClickListener(this);
        
        initViewPager();
    }

    private void initViewPager() {
        advPager = (ViewPager) findViewById(R.id.adv_pager);
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        List<View> advPics = new ArrayList<View>();


        ImageView img1 = new ImageView(this);
        img1.setBackgroundResource(R.drawable.tips_bg);
        advPics.add(img1);

        ImageView img2 = new ImageView(this);
        img2.setBackgroundResource(R.mipmap.back);
        advPics.add(img2);

        imageViews = new ImageView[advPics.size()];

        for (int i = 0; i < advPics.size(); i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new LayoutParams(20, 20));
            imageView.setPadding(5, 5, 5, 20);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.banner_dian_focus);
            } else {
                imageViews[i]
                        .setBackgroundResource(R.mipmap.banner_dian_blur);
            }
            group.addView(imageViews[i]);
            advPager.setAdapter(new AdvAdapter(advPics));
            advPager.setOnPageChangeListener(new GuidePageChangeListener());
            advPager.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                        case MotionEvent.ACTION_MOVE:
                            isContinue = false;
                            break;
                        case MotionEvent.ACTION_UP:
                            isContinue = true;
                            break;
                        default:
                            isContinue = true;
                            break;
                    }
                    return false;
                }
            });
            new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        if (isContinue) {
                            viewHandler.sendEmptyMessage(what.get());
                            whatOption();
                        }
                    }
                }

            }).start();
        }

    }
    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > imageViews.length - 1) {
            what.getAndAdd(-4);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
    }

    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            advPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            what.getAndSet(arg0);
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.mipmap.banner_dian_focus);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.mipmap.banner_dian_blur);
                }
            }

        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ticket_main;
    }

}
