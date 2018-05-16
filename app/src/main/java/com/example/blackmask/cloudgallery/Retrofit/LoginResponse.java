package com.example.blackmask.cloudgallery.Retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Blackma$k on 09/05/2018.
 */

public class LoginResponse {

    @SerializedName("Pemail")
    private String Pemail;

    @SerializedName("PPass")
    private String PPass;

    @SerializedName("response")
    private String response;

    @SerializedName("userid")
    private String userid;

    public String getResponse() {
        return response;
    }

    public String getUserid() {
        return userid;
    }
}
