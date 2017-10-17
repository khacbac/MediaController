package com.example.bachk.playvideodemo.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bachk.playvideodemo.R;
import com.example.bachk.playvideodemo.fragment.YoutubeFragment;
import com.example.bachk.playvideodemo.adapter.CustomVideoAdapter;
import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.fragment.VideoViewFragment;
import com.example.bachk.playvideodemo.presenter.IeVideoPresenter;
import com.example.bachk.playvideodemo.presenter.VideoPresenter;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements IeMainActivity, CustomVideoAdapter.IeOnItemClickListener,
        CustomVideoAdapter.OnLoadMoreListener, VideoViewFragment.IeOnFullScreenClick {

    private static final String TAG = MainActivity.class.getSimpleName();
    private IeVideoPresenter videoPresenter;
    private RecyclerView recyclerView;
    private CustomVideoAdapter videoAdapter;
    private FrameLayout frameContent;
    private String currentLink;
    private boolean isFullScreen = false;
    private ProgressBar progressLoadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        videoPresenter = new VideoPresenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerVideoList);
        frameContent = (FrameLayout) findViewById(R.id.frameContent);
        progressLoadMore = (ProgressBar) findViewById(R.id.progressBarLoadMore);
        videoAdapter = new CustomVideoAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setOnCustomItemClickListener(this);
        videoAdapter.setLoadMoreListener(this);
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
    public void finishLoadMoreData(List<VideoEntity> list) {
        videoAdapter.addListVideo(list);
        progressLoadMore.setVisibility(View.GONE);
    }

    @Override
    public void notGetMoreData() {
        videoAdapter.setMoreDataAvailable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressLoadMore.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Not get more data", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    @Override
    public void startLoadMediaVideo(VideoViewFragment videoViewFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, videoViewFragment).commit();
        videoViewFragment.setOnFullScreenClick(this);
    }

    @Override
    public void startLoadYoutubeVideo(String ytId) {
        YoutubeFragment playerFragment = new YoutubeFragment();
        playerFragment.setYoutubeId(ytId);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, playerFragment).commit();
    }

    @Override
    public void onItemClick(int position, String link) {
        Log.d(TAG, "onItemClick: pos = " + position);
        frameContent.setVisibility(View.VISIBLE);
        videoPresenter.loadVideoFromUrl(link);
        currentLink = link;
    }

    @Override
    public void onLoadMore(final List<VideoEntity> listVideo) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: loadmore");
                videoPresenter.loadMoreData(listVideo);
                progressLoadMore.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_share,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuShare:
                if (!currentLink.isEmpty()) {
                    videoPresenter.shareLink(this, currentLink);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFullScreenClick() {
        recyclerView.setVisibility(recyclerView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}
