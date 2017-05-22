package com.huitui.gxdt.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huitui.gxdt.R;
import com.huitui.gxdt.bean.JokeBean;

import java.util.List;

/**
 * Created by wangwenzhang on 2016/11/9.
 */
public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.MyHodler> {
    private List<JokeBean>list;
    private Context mcontext;

    public SecondAdapter(Context context, List<JokeBean>list){//获取数据源
        this.mcontext=context;
        this.list=list;
    }
    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.fragment_second_item,parent,false);
        MyHodler hodler=new MyHodler(view,mListener);
        return hodler;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onBindViewHolder(final MyHodler holder, final int position) {
        holder.titletv.setText(list.get(position).getTiltle());
        holder.tvxihuan.setTag(position);
        holder.shanchutv.setTag(position);
        holder.itemView.setTag(position);
        if (list.get(position).isChick()){
            holder.tvxihuan.setTextColor(Color.RED);
        }
    }
    class MyHodler extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvxihuan,shanchutv,titletv;
        private ImageView iv1;
        public MyHodler(View itemView,MyItemClickListener myItemClickListener) {
            super(itemView);
            mListener=myItemClickListener;
            titletv= (TextView) itemView.findViewById(R.id.frgament_second_home_tiltletv);
            shanchutv=(TextView) itemView.findViewById(R.id.fragment_second_home_fenxiang);
            tvxihuan= (TextView) itemView.findViewById(R.id.fragment_second_home_xihuan);
            iv1= (ImageView) itemView.findViewById(R.id.fragment_second_home_image);
            itemView.setOnClickListener(this);//单项点击
            shanchutv.setOnClickListener(new View.OnClickListener() {//点击删除
                @Override
                public void onClick(View v) {
                    mListener.OnBtDelete((Integer) v.getTag());
                }
            });
            tvxihuan.setOnClickListener(new View.OnClickListener() {//点击喜欢
                @Override
                public void onClick(View v) {
                    list.get((Integer) v.getTag()).setChick(true);
                    mListener.OnLike((Integer) v.getTag());
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (mListener!=null){
                mListener.setOnItemClickListener(itemView,v, (Integer) v.getTag());
            }
        }
    }
    private MyItemClickListener mListener=null;//设置点击接口
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }
    public interface MyItemClickListener {
        void setOnItemClickListener(View itemview, View view, int postion);
        void OnLike(int position);
        void OnBtDelete(int position);
    }
   /* private OnCountClickListener onCountClickListener;
    public void setOnCountClickListener(OnCountClickListener listener){
        this.onCountClickListener=listener;
    }
    public interface OnCountClickListener{
        void OnLike(int position);
        void OnBtDelete(int position);
    }*/
}
