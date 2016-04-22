package com.funguide.cc.movieticketmodule.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticketmodule.BaseActivity;
import com.funguide.cc.movieticketmodule.R;

import butterknife.ButterKnife;

/**
 * 影票业务热点问题
 **/
public class TicketProblemActivity extends BaseActivity implements View.OnClickListener{

    private ImageView title_back_img;
    private TextView title_center_txt;
    private TextView title_right_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_problem);
        ButterKnife.bind(this);
        initData();
        initView();
    }


    public void initData() {

    }


    public void initView() {
        title_center_txt = (TextView) this.findViewById(R.id.title_center_txt);
        title_back_img = (ImageView) this.findViewById(R.id.title_back_img);
        title_right_txt = (TextView) this.findViewById(R.id.title_right_txt);
        title_center_txt.setText(R.string.ticket_problem);
        title_center_txt.setTextColor(this.getResources().getColor(R.color.red));
        title_back_img.setOnClickListener(this);
        title_right_txt.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_img:
                finish();
            break;
        }
    }
}
