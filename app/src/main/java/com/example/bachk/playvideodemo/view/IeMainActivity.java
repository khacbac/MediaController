package com.example.bachk.playvideodemo.view;

import android.widget.VideoView;

import com.example.bachk.playvideodemo.entity.VideoEntity;

import java.util.List;

/**
 * Created by bachk on 10/16/2017.
 */

public interface IeMainActivity {
    void finishLoadAllData(List<VideoEntity> listVideo);
    void errorLoadAllData(String errorMes);

    void playMediaVideo(VideoView videoView);

    void finishLoadMoreData(List<VideoEntity> list);
    void notGetMoreData();
}
