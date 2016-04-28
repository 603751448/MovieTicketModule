package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.views.ExpandableTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 影片详情
 */
public class FilmDetailActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.film_chooseSet_btn)
    Button filmChooseSetBtn;
    @Bind(R.id.expandable_text)
    TextView expandableText;
    @Bind(R.id.expand_collapse)
    ImageButton expandCollapse;
    @Bind(R.id.expand_text_view)
    ExpandableTextView expandTextView;
    @Bind(R.id.film_comment_list)
    ListView filmCommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        titleCenterTxt.setText("电影名字");

    }


    @OnClick({R.id.title_back_img, R.id.film_chooseSet_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.film_chooseSet_btn:

                break;
        }
    }
}
