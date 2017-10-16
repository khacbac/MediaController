package com.example.bachk.playvideodemo.helper;


import com.example.bachk.playvideodemo.constants.Constants;
import com.example.bachk.playvideodemo.entity.VideoEntity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by VST on 9/16/2017.
 */

public interface ApiServer {

    //    @GET("index.php")
//    Call<ResponseBody> getAllData(@Query("offset") int offset);
    @GET("/index.php")
    Call<VideoResponse> getAllData(@Query("offset") int offset);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.URL_HOME)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
