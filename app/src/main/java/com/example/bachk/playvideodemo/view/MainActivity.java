package com.example.bachk.playvideodemo.view;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bachk.playvideodemo.R;
import com.example.bachk.playvideodemo.adapter.CustomVideoAdapter;
import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.presenter.IeVideoPresenter;
import com.example.bachk.playvideodemo.presenter.VideoPresenter;

import java.util.List;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class MainActivity extends AppCompatActivity implements IeMainActivity, CustomVideoAdapter.IeOnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private IeVideoPresenter videoPresenter;
    private RecyclerView recyclerView;
    private CustomVideoAdapter videoAdapter;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        videoPresenter.loadAllData();
    }

    @Override
    public void finishLoadAllData(List<VideoEntity> listVideo) {
        videoAdapter.setListVideo(listVideo);
    }

    @Override
    public void errorLoadAllData(String errorMes) {
        Toast.makeText(this, "Error = " + errorMes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void playMediaVideo(VideoView videoView) {
        videoView.start();
    }

    @Override
    public void onItemClick(int position, String link) {
        Log.d(TAG, "onItemClick: pos = " + position);
        videoView.setVisibility(View.VISIBLE);
        videoPresenter.playMediaVideo(videoView,link,this);

//        // Use a media controller so that you can scroll the video contents
//        // and also to pause, start the video.
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(Uri.parse(link));
//        videoView.start();
//
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                long time = mediaPlayer.getDuration();
//                Log.d(TAG, "onPrepared: Time = " + time);
//            }
//        });
    }

    private void initView() {
        videoPresenter = new VideoPresenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerVideoList);
        videoView = (VideoView) findViewById(R.id.videoPlayer);
        videoAdapter = new CustomVideoAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setOnCustomItemClickListener(this);
    }
}
