package com.example.bachk.playvideodemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.fragment.VideoViewFragment;
import com.example.bachk.playvideodemo.model.IeVideoModel;
import com.example.bachk.playvideodemo.model.VideoModel;
import com.example.bachk.playvideodemo.view.IeMainActivity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tho xinh dep on 10/16/2017.
 */

public class VideoPresenter implements IeVideoPresenter, IeVideoModel.IeFinishLoadAllData, IeVideoModel.IeFinishLoadMoreData {

    private static final String TAG = VideoPresenter.class.getSimpleName();
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
    public void loadMoreData(List<VideoEntity> listVideo) {
        ieVideoModel.loadMoreData(listVideo,this);
    }

    @Override
    public void loadVideoFromUrl(String link) {
        if (link.contains("youtube.com")) {
            String id = extractYTId(link);
            ieMain.startLoadYoutubeVideo(id);
        } else {
            VideoViewFragment fragment = new VideoViewFragment();
            fragment.setVideoForUrl(link);
            ieMain.startLoadMediaVideo(fragment);
        }
    }

    @Override
    public void shareLink(Context context, String currentLink) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, currentLink);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    @Override
    public void finishLoadData(List<VideoEntity> listVideo) {
        ieMain.finishLoadAllData(listVideo);

        for (VideoEntity entity : listVideo) {
            if (entity.getLink().contains("youtube.com")) {
                String id = extractYTId(entity.getLink());
                Log.d(TAG, "finishLoadData: id = " + id);
            }
        }
    }

    private String extractYTId(String ytUrl) {
        String vId = null;
        String pattern = "\\W([\\w-]{9,})(\\W|$)";
        Pattern pattern2 = Pattern.compile(pattern);
        Matcher matcher2 = pattern2.matcher(ytUrl);
        if (matcher2.find()){
            vId = matcher2.group(1);
        } else {
            Log.d(TAG, "extractYTId: not found");
        }
        return vId;
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
