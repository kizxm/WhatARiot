package com.kizxm.whatariot;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PandaService {
    public static void findChampions(String champion, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PANDA_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PANDA_CHAMP_QUERY, champion);
        String url = urlBuilder.build().toString();

        Log.d("url1: ", url);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.PANDA_KEY)
                .build();

        Log.d("url: ", url);

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

}
