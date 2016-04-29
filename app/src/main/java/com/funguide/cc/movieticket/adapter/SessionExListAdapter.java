package com.funguide.cc.movieticket.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.activity.SelcectSeatActivity;
import com.funguide.cc.movieticket.adapter.listview.CommonAdapter;
import com.funguide.cc.movieticket.views.NoScrollGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by tom on 2016/4/25.
 * 影院比价适配器
 *
 */
public class SessionExListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GroupData> groupDatas;


    public SessionExListAdapter(Context context, List<GroupData> groups) {
        this.context = context;
        this.groupDatas = groups;
    }

    @Override
    public int getGroupCount() {
        return groupDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d("getChildrenCount", groupDatas.get(groupPosition).getChilds().size() + "");
        //  返回值必须为1，否则会重复数据
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.d("getGroup", groupDatas.get(groupPosition) + "");

        return groupDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.d("getChild", groupDatas.get(groupPosition).getChilds().get(childPosition) + "");
        return groupDatas.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //加载组布局
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_session_group_item, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //设置数据

        if (isExpanded){
            groupViewHolder.groupShowChildImg.setImageResource(R.mipmap.icon_merchant_arrow_up);
        }else {
            groupViewHolder.groupShowChildImg.setImageResource(R.mipmap.icon_merchant_arrow_down);

        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_session_child_grid, null);
            viewHolder=new ChildViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ChildViewHolder) convertView.getTag();
        }
        viewHolder.childGridview.setNumColumns(2);
        viewHolder.childGridview.setGravity(Gravity.CENTER);
        viewHolder.childGridview.setVerticalSpacing(20);
        viewHolder.childGridview.setHorizontalSpacing(10);
        viewHolder.childGridview.setAdapter(new CommonAdapter<GroupData.Child>(context, R.layout.layout_session_child_item, groupDatas.get(groupPosition).getChilds()) {
            @Override
            public void convert(com.funguide.cc.movieticket.adapter.ViewHolder holder, int postion, GroupData.Child child) {
                holder.setImageUrl(R.id.child_img, child.getUrl());
            }
        });
        viewHolder.childGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(context, SelcectSeatActivity.class);
                context.startActivity(intent);

            }
        });
        return convertView;
    }


    static class GroupViewHolder {
        @Bind(R.id.group_start_time_txt)
        TextView groupStartTimeTxt;
        @Bind(R.id.group_end_time_txt)
        TextView groupEndTimeTxt;
        @Bind(R.id.group_film_type_txt)
        TextView groupFilmTypeTxt;
        @Bind(R.id.group_film_addres_txt)
        TextView groupFilmAddresTxt;
        @Bind(R.id.group_supplier_num_txt)
        TextView groupSupplierNumTxt;
        @Bind(R.id.group_show_child_img)
        ImageView groupShowChildImg;
        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @Bind(R.id.child_gridview)
        NoScrollGridView childGridview;
        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
