package com.xsl.mson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xsl.mson.R;
import com.xsl.mson.entity.User;

import java.util.List;

/**
 * Created by forest on 2017/9/7 0007.
 */

public class TimeDataAdapter extends BaseAdapter {

    List<User> userList;
    private Context context;
    private LayoutInflater layoutInflater;

    public TimeDataAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TimeHodler hodler;
        if (convertView == null){
            hodler = new TimeHodler();
            convertView = layoutInflater.inflate(R.layout.item_time_layout,null);

            hodler.time = (TextView) convertView.findViewById(R.id.tv_time);
            hodler.time_data = (TextView) convertView.findViewById(R.id.tv_time_data);
            convertView.setTag(hodler);

        }else {
            hodler = (TimeHodler) convertView.getTag();
        }
        hodler.time.setText(userList.get(position).getTime());
        hodler.time_data.setText(userList.get(position).getName());
        return convertView;
    }

    class TimeHodler{
        TextView time,time_data;
    }
}
