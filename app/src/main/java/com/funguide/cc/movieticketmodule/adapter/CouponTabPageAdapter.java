package com.funguide.cc.movieticketmodule.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.funguide.cc.movieticketmodule.CouponFragment;

/**
 * Created by tom on 2016/4/19.
 */
public class CouponTabPageAdapter extends FragmentPagerAdapter {

    private String[] couponItemName;

    public CouponTabPageAdapter(FragmentManager fm, String[] couponItemName) {
        super( fm );
        this.couponItemName=couponItemName;
    }

    @Override
    public Fragment getItem(int position) {
        //新建一个Fragment来展示ViewPager item的内容，并传递参数
        Fragment fragment = null;
        if (fragment==null){
            fragment = new CouponFragment() ;
        }
        Bundle args = new Bundle();
        args.putString("arg", couponItemName[position]);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return couponItemName[position % couponItemName.length];
    }
    @Override
    public int getCount() {
        return couponItemName.length;
    }
}
