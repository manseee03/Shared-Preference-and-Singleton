package com.example.registrationlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity1 extends AppCompatActivity {
    EditText number,password;
    Button back,loginbtn;

    Mysingleton mysingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        back=findViewById(R.id.backtoregister);
        number=findViewById(R.id.number);
        password=findViewById(R.id.passwordlogin);


        mysingleton= new Mysingleton(this);


        number.setText(mysingleton.getContact());
        password.setText(mysingleton.getPassword());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mysingleton.setIsRegister(false);
                mysingleton.getIsRegister();
                finish();
            }
        });

        loginbtn=findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mysingleton.setIsLogin(true);
                startActivity(new Intent(LoginActivity1.this,UpdateDelete.class));

            }
        });


    }
}