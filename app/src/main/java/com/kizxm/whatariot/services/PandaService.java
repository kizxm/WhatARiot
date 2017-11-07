package com.kizxm.whatariot.services;

import android.util.Log;

import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.models.Champion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PandaService {
    public static void findChampions(String champion, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PANDA_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PANDA_CHAMP_QUERY, champion);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.PANDA_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Champion> processResults(Response response) {
        ArrayList<Champion> champions = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONArray pandaJSON = new JSONArray(jsonData);

            Log.v("Returned Data:", jsonData);

            for (int i = 0; i < pandaJSON.length(); i++) {
                JSONObject championJSON = pandaJSON.getJSONObject(i);
                String name = championJSON.getString("name");
                String id = championJSON.getString("id");
                String hp = championJSON.getString("hp");
                String mp = championJSON.getString("mp");
                String moveSpeed = championJSON.getString("movespeed");
                String image_url = championJSON.getString("image_url");
                String big_image_url = championJSON.getString("big_image_url");

                Champion champion = new Champion(name, id, hp, mp, moveSpeed, image_url, big_image_url);
                champions.add(champion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return champions;
    }
}
