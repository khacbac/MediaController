package com.example.bachk.playvideodemo.model;

import com.example.bachk.playvideodemo.entity.VideoEntity;

import java.util.List;

/**
 * Created by bachk on 10/16/2017.
 */

public interface IeVideoModel {

    interface IeFinishLoadAllData {
        void finishLoadData(List<VideoEntity> listVideo);
        void errorLoadData(String errorMes);
    }

    void loadAllData(IeFinishLoadAllData onFinishLoad);
}
