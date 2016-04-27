package com.funguide.cc.movieticket.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.funguide.cc.movieticket.fragment.FilmFragment;

/**
 * Created by tom on 2016/4/23.
 */
public class FilmPagerAdapter extends FragmentStatePagerAdapter {

    private String[] title;

    public FilmPagerAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title=title;
    }
    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  title[position];
    }

    @Override
    public Fragment getItem(int position) {

        //新建一个Fragment来展示ViewPager item的内容，并传递参数
        Log.e("fragment","fragment"+position);
        Fragment fragment = null;
        if (fragment==null){
            Log.e("fragment==null","fragment"+position);
            fragment = new FilmFragment() ;
        }
        Bundle args = new Bundle();
        args.putString("arg", title[position]);
        fragment.setArguments(args);
        return fragment;
    }


}


