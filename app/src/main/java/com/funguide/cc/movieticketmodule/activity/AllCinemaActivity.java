package com.funguide.cc.movieticketmodule.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticketmodule.BaseActivity;
import com.funguide.cc.movieticketmodule.R;
import com.funguide.cc.movieticketmodule.expandtabview.ExpandTabView;
import com.funguide.cc.movieticketmodule.expandtabview.ViewMiddle;
import com.funguide.cc.movieticketmodule.expandtabview.ViewLeft;
import com.funguide.cc.movieticketmodule.expandtabview.ViewRight;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 全部影院列表页面
 **/
public class AllCinemaActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.title_right_txt)
    TextView titleRightTxt;
    @Bind(R.id.title_right_img)
    ImageView titleRightImg;
    // 级联控件
    @Bind(R.id.expandtab_view)
    ExpandTabView expandtabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ViewLeft viewLeft;
    private ViewMiddle viewMiddle;
    private ViewRight viewRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cinema);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }


    public void initView() {
        titleCenterTxt.setText("全部影院");
        titleRightTxt.setVisibility(View.GONE);
        titleRightImg.setVisibility(View.VISIBLE);
        titleRightImg.setBackgroundResource(R.drawable.search);
        viewLeft = new ViewLeft(this);
        viewMiddle = new ViewMiddle(this);
        viewRight = new ViewRight(this);

    }

    public void initData() {
        ArrayList<String> mTextArray = new ArrayList<String>();
        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);
        synchronized (mTextArray) {
            mTextArray.add("全部商圈");
            mTextArray.add("排序");
            mTextArray.add("筛选");
            expandtabView.setValue(mTextArray, mViewArray);
        }
        expandtabView.setTitle(viewLeft.getShowText(), 0);
    }

    private void initListener() {

        viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {
            @Override
            public void getValue(String showText) {
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewMiddle, showText);
            }
        });

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText);
            }
        });

    }

    private void onRefresh(View view, String showText) {

        expandtabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandtabView.getTitle(position).equals(showText)) {
            expandtabView.setTitle(showText, position);
        }
        Toast.makeText(AllCinemaActivity.this, showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @OnClick({R.id.title_back_img, R.id.title_right_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.title_right_img:   //  影院搜索
                startActivity(FuzzySearchActivity.class);
                break;
        }
    }
}
