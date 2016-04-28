package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.adapter.ViewHolder;
import com.funguide.cc.movieticket.adapter.listview.CommonAdapter;
import com.funguide.cc.movieticket.model.Film;

import java.util.ArrayList;
import java.util.List;

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
    @Bind(R.id.film_list)
    ListView filmList;
    private CommonAdapter<Film> filmAdapter;
    private List<Film> films;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_film);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        films=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Film film=new Film();
            film.setFilmName("荒野猎人"+i);
            film.setPicHor("http://www.xueliedu.com/uploads/allimg/151214/23110WB8_0.jpg");
            film.setSightType("3D");
            film.setScore("9.0");
            film.setFocus("熊袭惨兮兮，小李拿影帝");
            films.add(film);
        }
    }

    private void initView() {
        titleCenterTxt.setText("全部影片");
        filmAdapter=new CommonAdapter<Film>(this,R.layout.layout_film_item,films) {
            @Override
            public void convert(ViewHolder holder, int postion, Film film) {
                holder.setText(R.id.film_name_txt,film.getFilmName());
                holder.setText(R.id.film_score_txt,film.getScore());
                holder.setText(R.id.film_focus_txt,film.getFocus());
                holder.setText(R.id.film_sightType_text,film.getSightType()==null?"":film.getSightType());
                holder.setImageUrl(R.id.film_icon_img,film.getPicHor());
            }
        };
        filmList.setAdapter(filmAdapter);
        filmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("fromAllfilm",films.get(position));
                startActivity(SelectCinemaActivity.class,bundle);
            }
        });
    }

    @OnClick({R.id.title_back_img, R.id.film_hit_txt, R.id.film_upcoming_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.film_hit_txt:
                Toast.makeText(this, "film_hit_txt", Toast.LENGTH_SHORT).show();
                filmHitTxt.setTextColor(getResources().getColor(R.color.white));
                filmHitTxt.setBackgroundColor(getResources().getColor(R.color.blue));
                filmUpcomingTxt.setTextColor(getResources().getColor(R.color.blue));
                filmUpcomingTxt.setBackgroundColor(getResources().getColor(R.color.white));
                getHitFilm();
                break;
            case R.id.film_upcoming_txt:
                Toast.makeText(this, "film_upcoming_txt", Toast.LENGTH_SHORT).show();
                filmUpcomingTxt.setTextColor(getResources().getColor(R.color.white));
                filmUpcomingTxt.setBackgroundColor(getResources().getColor(R.color.blue));
                filmHitTxt.setTextColor(getResources().getColor(R.color.blue));
                filmHitTxt.setBackgroundColor(getResources().getColor(R.color.white));
                getUpComingFilm();
                break;
        }
    }

    /**
     * 获取即将上映的电影
     */
    private void getUpComingFilm() {

    }

    /**
     * 获取正在热播电影
     */
    private void getHitFilm() {

    }

}
