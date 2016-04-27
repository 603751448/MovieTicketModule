package com.funguide.cc.movieticket.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * Created by tom on 2016/4/27.
 */
public class AllListView extends ListView {
    public AllListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public AllListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
