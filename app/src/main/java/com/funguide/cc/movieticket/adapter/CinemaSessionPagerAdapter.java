package com.funguide.cc.movieticket.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.funguide.cc.movieticket.fragment.CinemaSessionFragment;

/**
 * Created by tom on 2016/4/25.
 * 影院场次适配器
 *
 */
public class CinemaSessionPagerAdapter extends FragmentPagerAdapter {

    private String[] cinemaSessionTitle;

    public CinemaSessionPagerAdapter(FragmentManager fm, String[] cinemaSessionTitle) {
        super( fm );
        this.cinemaSessionTitle=cinemaSessionTitle;
    }

    @Override
    public Fragment getItem(int position) {
        //新建一个Fragment来展示ViewPager item的内容，并传递参数
        Fragment fragment = null;
        if (fragment==null){
            fragment = new CinemaSessionFragment() ;
        }
        Bundle args = new Bundle();
        args.putString("arg", cinemaSessionTitle[position]);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return cinemaSessionTitle[position % cinemaSessionTitle.length];
    }
    @Override
    public int getCount() {
        return cinemaSessionTitle.length;
    }
}
