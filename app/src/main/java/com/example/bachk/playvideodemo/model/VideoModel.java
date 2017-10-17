package com.example.bachk.playvideodemo.model;

import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.helper.ApiUtils;
import com.example.bachk.playvideodemo.helper.VideoResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tho xinh dep on 10/16/2017.
 */

public class VideoModel implements IeVideoModel {

    private static final String TAG = VideoModel.class.getSimpleName();

    @Override
    public void loadAllData(final IeFinishLoadAllData onFinishLoad) {
        Call<VideoResponse> onGetData = ApiUtils.getApiServer().getAllData(10);
        onGetData.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                VideoResponse res = response.body();
                if (res != null) {
                    List<VideoEntity> listVideo = res.getListVideo();
                    if (!listVideo.isEmpty()) {
                        onFinishLoad.finishLoadData(listVideo);
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                onFinishLoad.errorLoadData(t.getMessage());
            }
        });

    }

    @Override
    public void loadMoreData(final List<VideoEntity> list, final IeFinishLoadMoreData loadMoreData) {
        Call<VideoResponse> onGetData = ApiUtils.getApiServer().getAllData(list.size() - 1);
        onGetData.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                VideoResponse res = response.body();
                if (res != null) {
                    List<VideoEntity> listVideo = res.getListVideo();
                    if (!listVideo.isEmpty()) {
                        loadMoreData.finishLoadMoreData(listVideo);
                    } else {
                        loadMoreData.notGetMoreData();
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                loadMoreData.notGetMoreData();
            }
        });
    }
}
