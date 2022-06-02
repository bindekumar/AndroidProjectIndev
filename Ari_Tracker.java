package com.indev.ari_tracker.RestAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Ari_Tracker {
    @POST("login.php")
    Call<JsonObject> LoginAPI(@Body RequestBody body);
    @POST("download_general.php")
    Call<JsonArray> download_general(@Body RequestBody body);
}