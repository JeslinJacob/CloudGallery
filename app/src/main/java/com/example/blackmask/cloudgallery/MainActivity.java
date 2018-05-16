package com.example.blackmask.cloudgallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
    }
    public void selectUpload(View view)
    {
        Intent intent=new Intent(this,UploadPicsPage.class);
        startActivity(intent);
    }
    public void viewImageList(View view)
    {
        Intent intent=new Intent(this,ListImages.class);
        startActivity(intent);
    }

}
