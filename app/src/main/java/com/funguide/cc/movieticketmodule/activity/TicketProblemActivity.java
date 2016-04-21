package com.funguide.cc.movieticketmodule.activity;

import android.os.Bundle;

import com.funguide.cc.movieticketmodule.BaseActivity;
import com.funguide.cc.movieticketmodule.R;

import butterknife.ButterKnife;

/**
 * 影票业务热点问题
 **/
public class TicketProblemActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_problem);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
}
