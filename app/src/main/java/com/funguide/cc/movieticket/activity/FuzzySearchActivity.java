package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 模糊查询
 **/
public class FuzzySearchActivity extends BaseActivity {
    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.search_tv)
    TextView searchTv;
    @Bind(R.id.search_history_lv)
    ListView searchHistoryLv;
    @Bind(R.id.clear_history_tv)
    TextView clearHistoryTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuzzy_search);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public void initData() {

    }

    public void initView() {
        titleCenterTxt.setText("影院搜索");
    }


    @OnClick({R.id.title_back_img, R.id.search_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.search_tv:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
