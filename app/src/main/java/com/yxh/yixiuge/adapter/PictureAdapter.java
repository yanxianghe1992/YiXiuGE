package com.yxh.yixiuge.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yxh.yixiuge.R;

import org.xutils.x;

import java.io.File;
import java.util.List;


/**
 * Created by zykj on 2017/4/15.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHodler> {
    private List<String> list;
    private Context context;
    private OnItemClickL itemClickL;

    //事件接口
    public interface OnItemClickL {
        void onClick(MyViewHodler holder, int pos);
    }

    public void setOnItemClickL(OnItemClickL itemClickL) {
        this.itemClickL = itemClickL;
    }

    public PictureAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repair_picture, parent, false);
        MyViewHodler myViewHodler = new MyViewHodler(view);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, final int position) {
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickL.onClick(holder, position);
            }
        });
        if (list.size() == (position + 1)) {
            holder.view.setImageResource(R.mipmap.tianjia);
        } else {
            String s = list.get(position);
            x.image().bind(holder.view, new File(s).toURI().toString());
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        ImageView view;
        LinearLayout layout;

        public MyViewHodler(View itemView) {
            super(itemView);
            view = (ImageView) itemView.findViewById(R.id.item_repair_picture_img);
            layout = (LinearLayout) itemView.findViewById(R.id.item_repair_picture_ll);
        }
    }
}
