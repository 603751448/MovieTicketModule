package com.funguide.cc.movieticketmodule.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by c on 4/21 0021.
 * 首页广告viewpager适配器
 */
public class TicketAdvAdapter extends PagerAdapter{
    private List<View> viewList;
    private Context context;
    public   TicketAdvAdapter(Context context,List<View> list){
        context=context;
        this.viewList=list;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container!=null){
            container.removeView((View) object);
        }
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
