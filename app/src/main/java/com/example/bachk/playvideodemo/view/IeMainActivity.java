package com.example.bachk.playvideodemo.view;

import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.fragment.VideoViewFragment;

import java.util.List;

/**
 * Created by bachk on 10/16/2017.
 */

public interface IeMainActivity {
    void finishLoadAllData(List<VideoEntity> listVideo);
    void errorLoadAllData(String errorMes);

    void finishLoadMoreData(List<VideoEntity> list);
    void notGetMoreData();

    void startLoadMediaVideo(VideoViewFragment videoViewFragment);
    void startLoadYoutubeVideo(String ytId);
}
