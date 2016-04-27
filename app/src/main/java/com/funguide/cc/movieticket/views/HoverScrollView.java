package com.funguide.cc.movieticket.views;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * ScrollView并没有实现滚动监听，所以我们必须自行实现对ScrollView的监听，
 * 我们很自然的想到在onTouchEvent()方法中实现对滚动Y轴进行监听
 * ScrollView的滚动Y值进行监听
 */
public class HoverScrollView extends ScrollView {

    // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;

    private  OnScrollListener onScrollListener;
    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;
    public HoverScrollView(Context context) {
        super(context);
    }
    public HoverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public HoverScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xDistance = yDistance = 0f;
//                xLast = ev.getX();
//                yLast = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final float curX = ev.getX();
//                final float curY = ev.getY();
//
//                xDistance += Math.abs(curX - xLast);
//                yDistance += Math.abs(curY - yLast);
//                xLast = curX;
//                yLast = curY;
//
//                if(xDistance > yDistance){
//                    return false;
//                }
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }
    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }
//    /**
//     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
//     */
//    private Handler handler = new Handler() {
//
//        public void handleMessage(android.os.Message msg) {
//            int scrollY =HoverScrollView.this.getScrollY();
//
//            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
//            if(lastScrollY != scrollY){
//                lastScrollY = scrollY;
//                handler.sendMessageDelayed(handler.obtainMessage(), 5);
//            }
//            if(onScrollListener != null){
//                onScrollListener.onScroll(scrollY);
//            }
//
//        };
//
//    };
//    /**
//     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
//     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
//     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
//     * MyScrollView滑动的距离
//     */
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if(onScrollListener != null){
//            onScrollListener.onScroll(lastScrollY = this.getScrollY());
//        }
//        switch(ev.getAction()){
//            case MotionEvent.ACTION_UP:
//                handler.sendMessageDelayed(handler.obtainMessage(), 20);
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener != null){
            onScrollListener.onScrollchanged(t);
        }
    }
    /**
     * 由垂直方向滚动条代表的所有垂直范围，缺省的范围是当前视图的画图高度。
     */
    public int computeVerticalScrollRange(){
        return super.computeVerticalScrollRange();
    }
    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        public void onScrollchanged(int scrollY);
    }
}
