package com.example.bachk.playvideodemo.helper;

import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tho xinh dep on 10/16/2017.
 */

public class VideoResponse {
    @SerializedName("data")
    private List<VideoEntity> listVideo;

    public List<VideoEntity> getListVideo() {
        return listVideo;
    }

    public void setListVideo(List<VideoEntity> listVideo) {
        this.listVideo = listVideo;
    }
}
