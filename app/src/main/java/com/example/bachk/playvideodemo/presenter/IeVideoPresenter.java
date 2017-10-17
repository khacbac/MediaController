package com.example.bachk.playvideodemo.presenter;

import android.content.Context;

import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.view.MainActivity;

import java.util.List;

/**
 * Created by tho xinh dep on 10/16/2017.
 */

public interface IeVideoPresenter {
    void loadAllData();

    void loadMoreData(List<VideoEntity> listVideo);

    void loadVideoFromUrl(String link);

    void shareLink(Context context, String currentLink);
}
