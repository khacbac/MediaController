package com.example.bachk.playvideodemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bachk.playvideodemo.R;
import com.example.bachk.playvideodemo.customview.CustomVideoView;
import com.example.bachk.playvideodemo.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bachk on 10/16/2017.
 */

public class CustomVideoAdapter extends RecyclerView.Adapter<CustomVideoAdapter.MyViewHolder> {

    private static final String TAG = CustomVideoAdapter.class.getSimpleName();
    private List<VideoEntity> listVideo = new ArrayList<>();
    private IeOnItemClickListener itemClickListener;

    public CustomVideoAdapter() {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_video_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        final VideoEntity entity = listVideo.get(position);
        holder.layoutView.setText(entity.getName());
//        holder.layoutView.setTxtTime(entity.getLink());
        holder.layoutView.setBackGroundImage(entity.getImage());

        holder.layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(position, entity.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVideo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CustomVideoView layoutView;

        MyViewHolder(View itemView) {
            super(itemView);
            layoutView = (CustomVideoView) itemView.findViewById(R.id.customVideoView);
        }
    }

    public void setListVideo(List<VideoEntity> listVideo) {
        this.listVideo = listVideo;
        notifyDataSetChanged();
    }

    public interface IeOnItemClickListener {
        void onItemClick(int position, String link);
    }

    public void setOnCustomItemClickListener(IeOnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
