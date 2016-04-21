package com.funguide.cc.movieticketmodule;

import android.view.View;
import android.widget.ImageView;

/**
 * 影票业务热点问题
 **/
public class TicketProblemActivity extends BaseActivity {
    private ImageView back;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                iv_Back(v);
                break;
        }

    }

    @Override
    protected void initView() {
        back = (ImageView) this.findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ticket_problem;
    }

    public void iv_Back(View v) {
        this.finish();
    }


}
