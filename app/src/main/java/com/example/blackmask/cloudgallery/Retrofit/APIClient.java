package com.example.blackmask.cloudgallery.Retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Blackma$k on 09/05/2018.
 */
public class APIClient extends Application {
    public static final String BASE_URL = "http://192.168.1.6/Cloudgallery/";

    private static Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Retrofit getClient() {

        if (retrofit==null) {

            retrofit = new Retrofit.Builder()

                    .baseUrl(BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

        }

        return retrofit;

    }
}
