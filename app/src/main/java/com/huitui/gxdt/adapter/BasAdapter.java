package com.huitui.gxdt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huitui.gxdt.R;
import com.huitui.gxdt.bean.AppInfo;

import java.util.ArrayList;

/**
 * Created by wangwenzhang on 2016/11/17.
 */
public class BasAdapter extends BaseAdapter {
    private ArrayList<AppInfo> list;
    private Context mcontext;
    public BasAdapter(ArrayList<AppInfo> list,Context context){
        this.list=list;
        this.mcontext=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.appitem,null);
            holder=new MyHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }

        holder.appname.setText(list.get(position).getApp_name());
        holder.apppakegername.setText(list.get(position).getPackagename());
        holder.appvisition.setText(list.get(position).getApp_version());
        holder.icon.setImageDrawable(list.get(position).getApp_icon());
        return convertView;
    }
      class MyHolder{
        private TextView appname,appvisition,apppakegername;
        private ImageView icon;
        private MyHolder(View itemView) {
            appname= (TextView) itemView.findViewById(R.id.app_name);
            appvisition=(TextView) itemView.findViewById(R.id.app_visition);
            apppakegername= (TextView) itemView.findViewById(R.id.app_pakegername);
            icon= (ImageView) itemView.findViewById(R.id.app_icon);

        }
    }



}
