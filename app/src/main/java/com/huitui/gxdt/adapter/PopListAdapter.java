package com.huitui.gxdt.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huitui.gxdt.R;

import java.util.List;

/**
 * Created by wangwenzhang on 2016/11/9.
 */
public class PopListAdapter extends BaseAdapter {
    private List<String> list;
    private Context mcontext;
    public PopListAdapter(Context context,List<String>list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final  MyHodler myHodler;
        if (convertView==null){
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.main_pop_item,null);
            myHodler=new MyHodler(convertView);
            convertView.setTag(myHodler);
        }else {
            myHodler= (MyHodler) convertView.getTag();
        }
        myHodler.t1.setText(list.get(position));
        return convertView;
    }
    class MyHodler{
        private TextView t1;
        private MyHodler(View viewItme){
            t1= (TextView) viewItme.findViewById(R.id.main_pop_item_textview);
        }
    }
}
