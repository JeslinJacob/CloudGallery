package com.example.blackmask.cloudgallery;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.blackmask.cloudgallery.Retrofit.APIClient;
import com.example.blackmask.cloudgallery.Retrofit.APIInterface;
import com.example.blackmask.cloudgallery.Retrofit.ImageDetails;
import com.example.blackmask.cloudgallery.Retrofit.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListImages extends AppCompatActivity {
    List<ImageDetails> imageDetailsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_images);

        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.imgListRV);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        imageDetailsList=new ArrayList<>();

        APIInterface apiInterface =
                APIClient.getClient().create(APIInterface.class);
        SharedPreferences pref= getApplicationContext().getSharedPreferences("LoginCredentials",MODE_PRIVATE);
        String userid=pref.getString("userid",null);
        Call<ArrayList<ImageDetails>> call = apiInterface.getUserImages(userid);
        call.enqueue(new Callback<ArrayList<ImageDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ImageDetails>> call, Response<ArrayList<ImageDetails>> response) {
                Log.d("SIZE>>",32+" "+response.body());
                System.out.println("######################## "+response.body());
                ImageListAdapter adapter=new ImageListAdapter(ListImages.this,response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<ImageDetails>> call, Throwable t) {

            }
        });


        ImageListAdapter adapter=new ImageListAdapter(this,imageDetailsList);
        recyclerView.setAdapter(adapter);
    }
    public void initializedata()
    {

        imageDetailsList.add(new ImageDetails("1","2","feb 1 2017","hello1"));
        imageDetailsList.add(new ImageDetails("2","2","feb 1 2017","hello2"));
        imageDetailsList.add(new ImageDetails("3","2","feb 1 2017","hello3"));
    }
}
