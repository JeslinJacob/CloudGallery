package com.example.blackmask.cloudgallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blackmask.cloudgallery.Retrofit.APIClient;
import com.example.blackmask.cloudgallery.Retrofit.APIInterface;
import com.example.blackmask.cloudgallery.Retrofit.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {

    EditText ET_email,ET_pass;
    private ProgressDialog dialog;
    SharedPreferences Pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();
        ET_email=(EditText)findViewById(R.id.login_user);
        ET_pass=(EditText)findViewById(R.id.login_password);
        dialog = new ProgressDialog(this);

        //check if user already logged in
        Pref = getApplicationContext().getSharedPreferences("LoginCredentials", MODE_PRIVATE);
        boolean check = Pref.getBoolean("IS_LOGIN", false);
        if(check){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(LoginPage.this, "not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    public void signin(View view)
    {
        dialog.setMessage("uploading, please wait.");
        dialog.show();
        String email,pass;
        email=ET_email.getText().toString();
        pass=ET_pass.getText().toString();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LoginResponse> call = apiInterface.Login(email,pass);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                dialog.dismiss();
                LoginResponse loginResponse = response.body();
                System.out.println("$$$$$$$$$$$$$$$$ user ID  : " + loginResponse.getUserid());
                Toast.makeText(LoginPage.this, " " + loginResponse.getResponse(), Toast.LENGTH_SHORT).show();

                //set logged in value as true in shared preferance
                SharedPreferences.Editor editor = Pref.edit();
                editor.putBoolean("IS_LOGIN", true);
                editor.putString("userid",loginResponse.getUserid());
                editor.commit();
                // shared pref ending
                Intent i= new Intent(LoginPage.this,MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginPage.this, "The web server is down"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void signuppage(View v)
    {
        Intent i = new Intent(LoginPage.this, RegisterActivity.class);
        startActivity(i);
    }

}
