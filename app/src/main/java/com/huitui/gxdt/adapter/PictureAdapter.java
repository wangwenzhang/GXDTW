package com.huitui.gxdt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.huitui.gxdt.R;
import com.huitui.gxdt.bean.BiaoQingBean;
import com.huitui.gxdt.view.GlideRoundTransform;

import java.util.List;

/**
 * Created by wangwenzhang on 2016/11/9.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyHodler> {
    private List<BiaoQingBean.NewsBean>list;
    private Context mcontext;

    public PictureAdapter(Context context, List<BiaoQingBean.NewsBean>list){//获取数据源
        this.mcontext=context;
        this.list=list;
    }
    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.activity_picture_item,parent,false);
        MyHodler hodler=new MyHodler(view,mListener);
        return hodler;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onBindViewHolder(final MyHodler holder, final int position) {
        holder.itemView.setTag(position);
        int higth=list.get(position).getHeight();
        if (higth>=240){
            higth=240;
        }
        WindowManager wm = (WindowManager) mcontext
                .getSystemService(Context.WINDOW_SERVICE);
        int width = (wm.getDefaultDisplay().getWidth())/4;
        holder.iv1.setLayoutParams(new RelativeLayout.LayoutParams(width,higth));
        /*holder.iv1.setMaxWidth(list.get(position).getWidth());
        holder.iv1.setMaxHeight(list.get(position).getHeight());*/
        Glide.with(mcontext).load(list.get(position).getFurl()).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new GlideRoundTransform(mcontext,5)).crossFade().into(holder.iv1);
    }
    class MyHodler extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView iv1;
        public MyHodler(View itemView,MyItemClickListener myItemClickListener) {
            super(itemView);
            mListener=myItemClickListener;
            itemView.setOnClickListener(this);//单项
            iv1= (ImageView) itemView.findViewById(R.id.activity_picture_item_image);

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
