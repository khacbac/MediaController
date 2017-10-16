package com.example.bachk.playvideodemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bachk.playvideodemo.R;
import com.example.bachk.playvideodemo.adapter.CustomVideoAdapter;
import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.presenter.IeVideoPresenter;
import com.example.bachk.playvideodemo.presenter.VideoPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements IeMainActivity, CustomVideoAdapter.IeOnItemClickListener,CustomVideoAdapter.OnLoadMoreListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private IeVideoPresenter videoPresenter;
    private RecyclerView recyclerView;
    private CustomVideoAdapter videoAdapter;
    private VideoView videoView;
    private FrameLayout frameContent;

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
        videoAdapter.addListVideo(listVideo);
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
    public void finishLoadMoreData(List<VideoEntity> list) {
        videoAdapter.addListVideo(list);
    }

    @Override
    public void notGetMoreData() {
        videoAdapter.setMoreDataAvailable(false);
    }

    @Override
    public void onItemClick(int position, String link) {
        Log.d(TAG, "onItemClick: pos = " + position);
        frameContent.setVisibility(View.VISIBLE);
        videoPresenter.playMediaVideo(videoView,link,this);
    }

    private void initView() {
        videoPresenter = new VideoPresenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerVideoList);
        videoView = (VideoView) findViewById(R.id.videoPlayer);
        frameContent = (FrameLayout) findViewById(R.id.frameContent);
        videoAdapter = new CustomVideoAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setOnCustomItemClickListener(this);
        videoAdapter.setLoadMoreListener(this);
    }

    @Override
    public void onLoadMore(final List<VideoEntity> listVideo) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: loadmore");
                videoPresenter.loadMoreData(listVideo);
            }
        });
    }
}
