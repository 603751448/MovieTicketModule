package com.funguide.cc.movieticket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.activity.SelectCinemaActivity;
import com.funguide.cc.movieticket.adapter.ViewHolder;
import com.funguide.cc.movieticket.adapter.listview.CommonAdapter;
import com.funguide.cc.movieticket.model.Cinema;
import com.funguide.cc.movieticket.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tom on 2016/4/23.
 */
public class FilmFragment extends Fragment {

    @Bind(R.id.cinema_listview)
    ListView cinemaListview;
    private List<Cinema> filmTocinemas;

    private View viewRoot;
    private String name;


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
        cinemaListview.setAdapter(new CommonAdapter<Cinema>(this.getActivity(),R.layout.layout_recommen_cinema_item,filmTocinemas) {
            @Override
            public void convert(ViewHolder holder, int postion, Cinema cinema) {
                holder.setText(R.id.cinema_name_txt,cinema.getCinemaName());
                holder.setText(R.id.cinema_addre_txt,cinema.getAddre());
                holder.setText(R.id.cinema_lowestprice_txt,cinema.getLowestPrice()+"元起");
                holder.setText(R.id.cinema_distance_txt,"2.3km");
            }
        });

        int listViewHeight = ViewUtil.setListViewHeightBasedOnChildren1(cinemaListview);
        ViewGroup.LayoutParams params = ((SelectCinemaActivity)getActivity()).filmPager.getLayoutParams();
        params.height = listViewHeight;
        ((SelectCinemaActivity)getActivity()).filmPager.setLayoutParams(params);
    }

    private void initData() {
        filmTocinemas=new ArrayList<>();
        for (int i = 0; i <20; i++) {
            Cinema ciname=new Cinema();
            ciname.setCinemaName("嘉年华国际影城（活力方店）");
            ciname.setAddre("北京市朝阳区姚家元路甲一号");
            ciname.setLowestPrice(48);
            filmTocinemas.add(ciname);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
