package com.example.registrationlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity1 extends AppCompatActivity {

    Mysingleton mysingleton;
    private static int time_out=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        mysingleton= new Mysingleton(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mysingleton.getIsLogin()){
                    startActivity(new Intent(HomeActivity1.this,UpdateDelete.class));
                    finish();

                }
                else if (mysingleton.getIsRegister()){
                    startActivity(new Intent(HomeActivity1.this, LoginActivity1.class));
                    finish();
                }
                else {
                    startActivity(new Intent(HomeActivity1.this,MainActivity.class));
                }
            }
        },time_out);
    }
}