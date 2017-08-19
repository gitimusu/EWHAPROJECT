package org.ros.android.android_tutorial_pubsub;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//import org.ros.cafe_Boss_Emp.app.R;

/**
    Created by user on 2017-07-02.
 */

public class tablelistviewAdapter extends BaseAdapter {

    private ArrayList<TableModel> menuList;
    Activity activity;

    public tablelistviewAdapter(Activity activity, ArrayList<TableModel> menuList) {
        super();
        this.activity = activity;
        this.menuList = menuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    private class ViewHolder {
        TextView mTableNo;
        TextView mMenu;
        TextView mSize;
        TextView mCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tablelistview_row, null);
            holder = new ViewHolder();
            holder.mTableNo = (TextView) convertView.findViewById(R.id.tableNo);
            holder.mMenu = (TextView) convertView.findViewById(R.id.menu);
            holder.mSize = (TextView) convertView.findViewById(R.id.size);
            holder.mCount = (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TableModel item = menuList.get(position);
        holder.mTableNo.setText(item.getTableNo().toString());
        holder.mMenu.setText(item.getMenu().toString());
        holder.mSize.setText(item.getSize().toString());
        holder.mCount.setText(item.getCount().toString());

        return convertView;
    }
    }
