package com.example.bachk.playvideodemo.presenter;

import android.content.Context;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.model.IeVideoModel;
import com.example.bachk.playvideodemo.model.VideoModel;
import com.example.bachk.playvideodemo.view.IeMainActivity;

import java.util.List;

/**
 * Created by tho xinh dep on 10/16/2017.
 */

public class VideoPresenter implements IeVideoPresenter, IeVideoModel.IeFinishLoadAllData, IeVideoModel.IeFinishLoadMoreData {

    private IeMainActivity ieMain;
    private IeVideoModel ieVideoModel;

    public VideoPresenter(IeMainActivity ieMain) {
        this.ieMain = ieMain;
        ieVideoModel = new VideoModel();
    }

    @Override
    public void loadAllData() {
        ieVideoModel.loadAllData(this);
    }

    @Override
    public void playMediaVideo(VideoView videoView, String url, Context context) {
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(url));
        ieMain.playMediaVideo(videoView);
    }

    @Override
    public void loadMoreData(List<VideoEntity> listVideo) {
        ieVideoModel.loadMoreData(listVideo,this);
    }


    @Override
    public void finishLoadData(List<VideoEntity> listVideo) {
        ieMain.finishLoadAllData(listVideo);
    }

    @Override
    public void errorLoadData(String errorMes) {
        ieMain.errorLoadAllData(errorMes);
    }

    @Override
    public void finishLoadMoreData(List<VideoEntity> listVideo) {
        ieMain.finishLoadMoreData(listVideo);
    }

    @Override
    public void notGetMoreData() {
        ieMain.notGetMoreData();
    }
}
