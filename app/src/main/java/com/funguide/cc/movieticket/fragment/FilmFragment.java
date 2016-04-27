package com.funguide.cc.movieticket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.activity.SelectCinemaActivity;
import com.funguide.cc.movieticket.utils.ViewUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tom on 2016/4/23.
 */
public class FilmFragment extends Fragment {
    //    @Bind(R.id.text)
//    TextView text;
    @Bind(R.id.listview)
    ListView listview;
    private View viewRoot;
    private String name;
    String str_name[]=new String[]{"aaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbb","cccccccccccc"
            ,"aaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbb","aaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbb","cccccccccccc"
            ,"aaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbb",
            "aaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbb","cccccccccccc"
            ,"aaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbb"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = (String) getArguments().get("arg");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_film, container, false);
        ButterKnife.bind(this, viewRoot);
        initData();
        initView();
        return viewRoot;
    }

    private void initView() {
        listview.setAdapter(new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, str_name));
        int listViewHeight = ViewUtil.setListViewHeightBasedOnChildren1(listview);
        ViewGroup.LayoutParams params = ((SelectCinemaActivity)getActivity()).filmPager.getLayoutParams();
        params.height = listViewHeight;
        ((SelectCinemaActivity)getActivity()).filmPager.setLayoutParams(params);
    }

    private void initData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
