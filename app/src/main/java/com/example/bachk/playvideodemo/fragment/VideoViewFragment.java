package com.example.bachk.playvideodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bachk.playvideodemo.R;
import com.example.bachk.playvideodemo.customview.CustomVideoView;

/**
 * Created by bachk on 10/17/2017.
 */

public class VideoViewFragment extends Fragment implements CustomVideoView.IeOnImgBtnFullScreenClick{
    private CustomVideoView customVideoView;
    private String videoUrl;
    private IeOnFullScreenClick listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_view, container, false);
        customVideoView = (CustomVideoView) view.findViewById(R.id.customVideoView);
        customVideoView.setVideoUrl(videoUrl);
        customVideoView.setOnImgBtnFullClick(new CustomVideoView.IeOnImgBtnFullScreenClick() {
            @Override
            public void onImgBtnFullClick() {
                listener.onFullScreenClick();
            }
        });
        return view;
    }

    public void setVideoForUrl(String url) {
        this.videoUrl = url;
    }

    @Override
    public void onImgBtnFullClick() {
        listener.onFullScreenClick();
    }


    public interface IeOnFullScreenClick {
        void onFullScreenClick();
    }

    public void setOnFullScreenClick(IeOnFullScreenClick click) {
        this.listener = click;
    }
}
