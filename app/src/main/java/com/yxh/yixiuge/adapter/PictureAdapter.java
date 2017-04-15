package com.yxh.yixiuge.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yxh.yixiuge.R;

import org.xutils.x;

import java.io.File;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/15.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHodler> {
    private List<PhotoInfo> list;
    private Context context;

    public PictureAdapter(List<PhotoInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<PhotoInfo> list) {
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
    public void onBindViewHolder(MyViewHodler holder, int position) {
        PhotoInfo photoInfo = list.get(position);
        String photoPath = photoInfo.getPhotoPath();
        x.image().bind(holder.view, new File(photoPath).toURI().toString());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        ImageView view;

        public MyViewHodler(View itemView) {
            super(itemView);
            view = (ImageView) itemView.findViewById(R.id.item_repair_picture_img);
        }
    }


}
