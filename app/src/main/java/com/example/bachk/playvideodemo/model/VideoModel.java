package com.example.bachk.playvideodemo.model;

import android.util.Log;

import com.example.bachk.playvideodemo.entity.VideoEntity;
import com.example.bachk.playvideodemo.helper.ApiServer;
import com.example.bachk.playvideodemo.helper.ApiUtils;
import com.example.bachk.playvideodemo.helper.VideoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import okhttp3.ResponseBody;
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
}
