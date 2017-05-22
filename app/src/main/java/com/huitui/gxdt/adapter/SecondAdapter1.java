package com.huitui.gxdt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huitui.gxdt.R;
import com.huitui.gxdt.bean.JokeBean1;

import java.util.List;

/**
 * Created by wangwenzhang on 2016/11/9.
 */
public class SecondAdapter1 extends RecyclerView.Adapter<SecondAdapter1.MyHodler> {
    private List<JokeBean1.ListBean>list;
    private Context mcontext;

    public SecondAdapter1(Context context, List<JokeBean1.ListBean>list){//获取数据源
        this.mcontext=context;
        this.list=list;
    }
    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.fragment_second_item1,parent,false);
        MyHodler hodler=new MyHodler(view,mListener);
        return hodler;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onBindViewHolder(final MyHodler holder, final int position) {
        holder.titletv.setText(list.get(position).getText());

    }
    class MyHodler extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titletv;
        public MyHodler(View itemView,MyItemClickListener myItemClickListener) {
            super(itemView);
            mListener=myItemClickListener;
            titletv= (TextView) itemView.findViewById(R.id.fragment_second_itme_tv);
            itemView.setOnClickListener(this);//单项点击

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
