package com.example.bachk.playvideodemo.presenter;

import android.content.Context;
import android.widget.VideoView;

/**
 * Created by tho xinh dep on 10/16/2017.
 */

public interface IeVideoPresenter {
    void loadAllData();
    void playMediaVideo(VideoView videoView, String url, Context context);
}
