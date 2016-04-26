/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.funguide.cc.movieticketmodule.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.funguide.cc.movieticketmodule.R;
import com.funguide.cc.movieticketmodule.utils.DenstiyUtils;


public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {
    private Context mContext;
    //    private List<Integer>
    public  FancyCoverFlowSampleAdapter(Context context){
        this.mContext=context;
    }
    // =============================================================================
    // Private members
    // =============================================================================

    private int[] images = {R.drawable.test1, R.drawable.test1, R.drawable.test1, R.drawable.test1, R.drawable.test1, R.drawable.test1};


    // =============================================================================
    // Supertype overrides
    // =============================================================================

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Integer getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        ImageView imageView = null;
        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
        } else {
//            view=  LayoutInflater.from(mContext).inflate(R.layout.layout_img,viewGroup);
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(DenstiyUtils.dip2px(mContext,75),DenstiyUtils.dip2px(mContext,120)));
        }
        imageView.setImageResource(this.getItem(i));
        return imageView;
    }
}
