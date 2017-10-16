package com.example.bachk.playvideodemo.model;

import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.presenter.VideoPresenter;

import java.util.List;

/**
 * Created by bachk on 10/16/2017.
 */

public interface IeVideoModel {

    interface IeFinishLoadAllData {
        void finishLoadData(List<VideoEntity> listVideo);
        void errorLoadData(String errorMes);
    }

    interface IeFinishLoadMoreData {
        void finishLoadMoreData(List<VideoEntity> listVideo);
        void notGetMoreData();
    }

    void loadAllData(IeFinishLoadAllData onFinishLoad);

    void loadMoreData(List<VideoEntity> listVideo, IeFinishLoadMoreData loadMoreData);
}
