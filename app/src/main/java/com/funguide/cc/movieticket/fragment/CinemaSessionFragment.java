package com.funguide.cc.movieticket.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.adapter.GroupData;
import com.funguide.cc.movieticket.adapter.SessionExListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CinemaSessionFragment extends Fragment {

    @Bind(R.id.session_exlist)
    ExpandableListView sessionExlist;
    private View viewRoot;
    private String name;
    List<GroupData> groupDatas;

    public CinemaSessionFragment() {

    }

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
        viewRoot = inflater.inflate(R.layout.fragment_cinema_session, container, false);
        ButterKnife.bind(this, viewRoot);
        initData();
        initView();
        return viewRoot;
    }

    private void initData() {
        groupDatas=new ArrayList<>();
        List<GroupData.Child> childs;

        for (int i = 0; i <10 ; i++) {
            GroupData groupData=new GroupData();
            groupData.setStartTime("2:00");
            groupData.setEndTime("4:00");
            groupData.setType("IMAX3D");
            childs=new ArrayList<>();
            for (int j = 0; j <4 ; j++) {
                GroupData.Child  child=new GroupData.Child();
                child.setUrl("http://g.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=250daa6fb7119313d34ef7e2045167b2/c9fcc3cec3fdfc03e40b8715d13f8794a4c2262f.jpg");
                child.setName("child"+j);
                childs.add(child);
            }
            groupData.setChilds(childs);
            groupDatas.add(groupData);
        }
        Log.d("group",groupDatas.toString());

    }
    private void initView() {
        sessionExlist.setAdapter(new SessionExListAdapter(this.getActivity(),groupDatas));

        //去掉走遍默认的箭头,也可以自定义但是位置还在左边
        sessionExlist.setGroupIndicator(null);

        //点击下一个关闭上一个箭头
        sessionExlist.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count=sessionExlist.getExpandableListAdapter().getGroupCount();
                for (int i = 0; i <count ; i++) {
                    if (i!=groupPosition){
                        sessionExlist.collapseGroup(i);
                    }
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
