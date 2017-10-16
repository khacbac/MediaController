package com.example.bachk.playvideodemo.helper;

/**
 * Created by VST on 9/16/2017.
 */

public class ApiUtils {
    public static ApiServer getApiServer() {
        return ApiServer.retrofit.create(ApiServer.class);
    }
}
