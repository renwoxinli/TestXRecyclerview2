package com.lixu.testxrecyclerview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by lixu on 2016/3/19.
 */
class MyAdapter extends XRecyclerView.Adapter<MyAdapter.Myholder> {
    private int[] data;
    private Context context;
    private BitmapFactory.Options otp;


    public MyAdapter(int[] imgs, Context c) {
        context = c;
        data = imgs;
        otp = new BitmapFactory.Options();
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_list, null);

        return new Myholder(view);
    }

    //设置回调接口
    interface OnItemClickLitener {

        void onitemclick(View view, int pos);

        void onitemlongclick(View view, int pos);

    }

    private OnItemClickLitener onItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }


    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        //将本地图片改成bitmap图片
        holder.iv.setImageBitmap(BitmapFactory.decodeStream(context.getResources().openRawResource(data[position]), null, otp));
        // holder.iv.setImageResource(data[position]);
        //设置点击事件
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickLitener.onitemclick(holder.iv, pos);
            }
        });
        //设置长按点击事件
        holder.iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickLitener.onitemlongclick(holder.iv, pos);
                return false;
            }
        });

    }


    @Override
    public int getItemCount() {

        return data.length;
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public Myholder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}