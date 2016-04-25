package com.funguide.cc.movieticketmodule.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by c on 4/21 0021.
 * ScrollView工具类
 */
public class ScrollViewTicket extends ScrollView {
    private OnScrollListener onScrollListener;

    public ScrollViewTicket(Context context) {
        super(context, null);
    }


    public ScrollViewTicket(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollViewTicket(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScroll(t);
        }
    }

    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        public void onScroll(int scrollY);
    }
}
