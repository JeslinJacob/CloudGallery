package com.example.blackmask.cloudgallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.blackmask.cloudgallery.Retrofit.APIClient;
import com.example.blackmask.cloudgallery.Retrofit.APIInterface;
import com.example.blackmask.cloudgallery.Retrofit.MainResponseClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPicsPage extends AppCompatActivity {

    private StorageReference mstorage;
    ProgressDialog progressDialog;
    Bitmap bitmap;
    ImageView myimage;
    EditText titletxt;
    Uri path;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pics_page);
        progressDialog=new ProgressDialog(this);
        myimage=(ImageView)findViewById(R.id.pic_placeholder) ;
        mstorage= FirebaseStorage.getInstance().getReference();
        titletxt=(EditText)findViewById(R.id.et_title);
    }

    public void selectimage(View view)
    {
                Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 0);

    }

    public void upload(View view)
    {
        final String titleOfImage = titletxt.getText().toString();
        if(!(myimage==null) && !(titleOfImage.matches("")))
        {
            progressDialog.setMessage("uploading");
            progressDialog.show();

            StorageReference filepath = mstorage.child("photo").child(file.getName());
            filepath.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                Toast.makeText(UploadPicsPage.this, "success", Toast.LENGTH_SHORT).show();

                    Uri downloaduri= taskSnapshot.getDownloadUrl();

                    Log.i("uri :",""+downloaduri);
                    SharedPreferences Pref = getApplicationContext().getSharedPreferences("LoginCredentials", MODE_PRIVATE);
                    String userid = Pref.getString("userid",null);

                    retrofitcode(userid,downloaduri.toString(),titleOfImage);
//                    Picasso.get().load(downloaduri).into(imageView);

                }
            });

        }
        else{
            Toast.makeText(this, "select image and add title", Toast.LENGTH_SHORT).show();
        }

    }

    private void retrofitcode(String uid,String imguri,String imgtitle)
    {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<MainResponseClass> call = apiInterface.uploadImage(uid, imguri,imgtitle);

        call.enqueue(new Callback<MainResponseClass>() {
            @Override
            public void onResponse(Call<MainResponseClass> call, Response<MainResponseClass> response) {
                progressDialog.dismiss();
                MainResponseClass reg = response.body();
                System.out.println("$$$$$$$$$$$$$$$$ RESPONSE : " + reg.getResponse());
                Toast.makeText(UploadPicsPage.this, " " + reg.getResponse(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MainResponseClass> call, Throwable t) {
                progressDialog.dismiss();

                Log.d("$$$failure : ",t.getMessage());
                Toast.makeText(UploadPicsPage.this, "The web server is down", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {


             path = data.getData();
            file= new File(path.getPath());
            //putting the image from obtained path into a bitmap
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                myimage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //setting the bitmap on the image view


        }
        else {
            Toast.makeText(this, "error while selecting", Toast.LENGTH_SHORT).show();
        }

    }

}
