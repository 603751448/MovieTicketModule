package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllFilmActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.film_hit_txt)
    TextView filmHitTxt;
    @Bind(R.id.film_upcoming_txt)
    TextView filmUpcomingTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_film);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
    }
    private void initView() {
        titleCenterTxt.setText("全部影片");
    }
    @OnClick({R.id.title_back_img, R.id.film_hit_txt, R.id.film_upcoming_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.film_hit_txt:
                Toast.makeText(this,"film_hit_txt",Toast.LENGTH_SHORT).show();
                filmHitTxt.setTextColor(getResources().getColor(R.color.white));
                filmHitTxt.setBackgroundColor(getResources().getColor(R.color.blue));
                filmUpcomingTxt.setTextColor(getResources().getColor(R.color.blue));
                filmUpcomingTxt.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.film_upcoming_txt:
                Toast.makeText(this,"film_upcoming_txt",Toast.LENGTH_SHORT).show();
                filmUpcomingTxt.setTextColor(getResources().getColor(R.color.white));
                filmUpcomingTxt.setBackgroundColor(getResources().getColor(R.color.blue));
                filmHitTxt.setTextColor(getResources().getColor(R.color.blue));
                filmHitTxt.setBackgroundColor(getResources().getColor(R.color.white));

                break;
        }
    }
}
