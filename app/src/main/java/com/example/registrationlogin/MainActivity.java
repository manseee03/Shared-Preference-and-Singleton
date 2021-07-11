package com.example.registrationlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText fullname,email,mobile,cityname,pincode,password;
    Button register;

    String Fullname,Email,Mobile,Cityname,Pincode,Password;

    Mysingleton mysingleton;

    private static String web_url="https://calligraphical-chie.000webhostapp.com/Registration1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullname= findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        cityname=findViewById(R.id.city);
        pincode=findViewById(R.id.pincode);
        password=findViewById(R.id.password);


        mysingleton =new Mysingleton(this);


        register=findViewById(R.id.register);


        register.setOnClickListener(new MysharedPrefrence());
    }
    class MysharedPrefrence implements View.OnClickListener  {
        @Override
        public void onClick(View view) {
            Fullname=fullname.getText().toString().trim();
            Email=email.getText().toString().trim();
            Mobile= mobile.getText().toString().trim();
            Cityname=cityname.getText().toString().trim();
            Pincode= pincode.getText().toString().trim();
            Password= password.getText().toString().trim();

            if (Fullname.isEmpty() || Email.isEmpty() || Mobile.isEmpty() || Cityname.isEmpty() || Pincode.isEmpty() || Password.isEmpty()){

                Toast.makeText(MainActivity.this,"Please fill All Details mentioned",Toast.LENGTH_LONG).show();

            }
            else {

                mysingleton.setFullname(fullname.getText().toString().trim());
                mysingleton.setEmail(email.getText().toString().trim());
                mysingleton.setContact(mobile.getText().toString().trim());
                mysingleton.setCityname(cityname.getText().toString().trim());
                mysingleton.setPincode(pincode.getText().toString().trim());
                mysingleton.setPassword(password.getText().toString().trim());

                Mydataconnection();
                mysingleton.setIsRegister(true);
                startActivity(new Intent(MainActivity.this,LoginActivity1.class));
            }



        }
    }
    public void Mydataconnection(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, web_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int sucess= jsonObject.getInt("sucess");

                    if (sucess==1){
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        fullname.setText("");
                        email.setText("");
                        mobile.setText("");
                        cityname.setText("");
                        pincode.setText("");
                        password.setText("");
                    }

                    else if (sucess==2){
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }

                    else {
                        Toast.makeText(MainActivity.this," Connection Failed ",Toast.LENGTH_LONG).show();
                    }

                }
                catch (JSONException e)
                {
                    Toast.makeText(MainActivity.this,"EXCEPTION",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fullname",fullname.getText().toString().trim());
                params.put("email",email.getText().toString().trim());
                params.put("mobile",mobile.getText().toString().trim());
                params.put("cityname",cityname.getText().toString().trim());
                params.put("pincode",pincode.getText().toString().trim());
                params.put("password",password.getText().toString().trim());

                return params;
            }
        }
                ;

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}