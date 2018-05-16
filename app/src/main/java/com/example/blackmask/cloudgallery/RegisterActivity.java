package com.example.blackmask.cloudgallery;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blackmask.cloudgallery.Retrofit.APIClient;
import com.example.blackmask.cloudgallery.Retrofit.APIInterface;
import com.example.blackmask.cloudgallery.Retrofit.MainResponseClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText name , email , pass;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.input_name);
        email=(EditText)findViewById(R.id.input_email);
        pass=(EditText)findViewById(R.id.input_pass);
        dialog = new ProgressDialog(this);
    }


    public void signup(View v)
    {
        dialog.setMessage("uploading, please wait.");
        dialog.show();

        String Sname,Semail,Spass;
        Sname = name.getText().toString();
        Semail=email.getText().toString();
        Spass=pass.getText().toString();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<MainResponseClass> call = apiInterface.uploadDetails(Sname, Semail, Spass);

        call.enqueue(new Callback<MainResponseClass>() {
            @Override
            public void onResponse(Call<MainResponseClass> call, Response<MainResponseClass> response) {
                dialog.dismiss();
                MainResponseClass reg = response.body();
                System.out.println("$$$$$$$$$$$$$$$$ RESPONSE : " + reg.getResponse());
                Toast.makeText(RegisterActivity.this, " " + reg.getResponse(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<MainResponseClass> call, Throwable t) {
                dialog.dismiss();

                Toast.makeText(RegisterActivity.this, "The web server is down"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
