package com.example.blackmask.cloudgallery.Retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Blackma$k on 09/05/2018.
 */

public interface APIInterface {

    @FormUrlEncoded
    @POST("Sreg.php")
    Call<MainResponseClass> uploadDetails(@Field("name") String name,
                                          @Field("semail") String email,
                                          @Field("pass") String pass );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> Login(@Field("Pemail") String Pemail,
                              @Field("PPass") String pass);

    @FormUrlEncoded
    @POST("imageupload.php")
    Call<MainResponseClass> uploadImage(@Field("uid") String uid,
                              @Field("imgURI") String imgURI,
                              @Field("title") String imgtitle);


    @FormUrlEncoded
    @POST("listImages.php")
    Call<ArrayList<ImageDetails>> getUserImages(@Field("uid") String uid);

}
