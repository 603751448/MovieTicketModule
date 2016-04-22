package com.funguide.cc.movieticketmodule.activity;

import android.os.Bundle;

import com.funguide.cc.movieticketmodule.BaseActivity;
import com.funguide.cc.movieticketmodule.R;

import butterknife.ButterKnife;

public class AllFilmActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_film);
        ButterKnife.bind(this);
    }
}
