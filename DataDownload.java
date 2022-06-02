package com.indev.ari_tracker.Util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;
import com.indev.ari_tracker.Activity.LoginActivity;
import com.indev.ari_tracker.Database.SharedPrefHelper;
import com.indev.ari_tracker.Database.SqliteHelper;
import com.indev.ari_tracker.RestAPI.Ari_Tracker;
import com.indev.ari_tracker.RestAPI.ClientAPI;

import org.json.JSONObject;

import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("StaticFieldLeak")
public class DataDownload extends AsyncTask<Void, Void, Void> {

    private final String[] tables = {"state","district","pollution","aqi_category","block"};

    SharedPrefHelper sharedPrefHelper;
    Context context;
    SqliteHelper sqliteHelper;

    public DataDownload(SharedPrefHelper sharedPrefHelper, Context context, SqliteHelper sqliteHelper) {
        this.sharedPrefHelper = sharedPrefHelper;
        this.context = context;
        this.sqliteHelper = sqliteHelper;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for (int j = 0; j < tables.length; j++) {
            final int finalJ = j;
            String data = "{\"table_name\": \""+tables[finalJ]+"\", \"user_id\": \"7\"}";
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, data);

            final Ari_Tracker apiService = ClientAPI.getClient().create(Ari_Tracker.class);
            Call<JsonArray> call = apiService.download_general(body);
            call.enqueue(new Callback<JsonArray>(){
                @Override
                public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                    try {
                        JsonArray data = response.body();
                        sqliteHelper.dropTable(tables[finalJ]);

                        for (int i = 0; i < data.size(); i++){
                            JSONObject singledata = new JSONObject(data.get(i).toString());
                            Iterator keys = singledata.keys();
                            ContentValues contentValues = new ContentValues();
                            while (keys.hasNext()) {
                                String currentDynamicKey = (String) keys.next();
                                contentValues.put(currentDynamicKey, singledata.get(currentDynamicKey).toString());
                            }
                            sqliteHelper.saveMasterTable(contentValues, tables[finalJ]);
                        }
                        if (tables[finalJ].equalsIgnoreCase("block")) {
                            sharedPrefHelper.setString("isSplashLoaded", "Yes");
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }
                    } catch (Exception s) {
                        s.printStackTrace();
                    }
                }
                @Override
                public void onFailure(@NonNull Call<JsonArray> call, @NonNull Throwable t) {
                }
            });
        }
        return null;
    }
}
