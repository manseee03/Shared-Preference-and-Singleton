package com.example.registrationlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class UpdateDelete extends AppCompatActivity {
    EditText searchname,fullname,email,mobile,cityname,pincode,password;
    Button searchbtn,updatebtn,deletebtn,backbtn;

    Mysingleton mysingleton;

    private static String search_url="https://calligraphical-chie.000webhostapp.com/Fetchurl1.php";
    private static String update_url="https://calligraphical-chie.000webhostapp.com/Updatedata1.php";
    private static String delete_url="https://calligraphical-chie.000webhostapp.com/Deletedata1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        searchname=findViewById(R.id.getname);
        fullname=findViewById(R.id.fullname2);
        email=findViewById(R.id.email2);
        mobile=findViewById(R.id.mobile2);
        cityname=findViewById(R.id.city2);
        pincode=findViewById(R.id.pincode2);
        password=findViewById(R.id.password2);

        mysingleton = new Mysingleton(this);
        backbtn=findViewById(R.id.backtologin);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mysingleton.setIsLogin(false);
                finish();

            }
        });

        searchbtn=findViewById(R.id.btnsearch);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchdata();
            }
        });

        updatebtn=findViewById(R.id.updatebtn);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedata();
            }
        });

        deletebtn=findViewById(R.id.deletebtn);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedata();
            }
        });


    }

    public void searchdata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,search_url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int sucess= jsonObject.getInt("sucess");


                    if (sucess==1){

                        fullname.setText(jsonObject.getString("fullname"));
                        email.setText(jsonObject.getString("email"));
                        mobile.setText(jsonObject.getString("mobile"));
                        cityname.setText(jsonObject.getString("cityname"));
                        pincode.setText(jsonObject.getString("pincode"));
                        password.setText(jsonObject.getString("password"));

                        Toast.makeText(UpdateDelete.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        searchname.setText("");

                    }
                    else if (sucess==2){

                        Toast.makeText(UpdateDelete.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    }

                    else {
                        Toast.makeText(UpdateDelete.this,"Connection Failed",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(UpdateDelete.this,"EXCEPTION"+e,Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UpdateDelete.this,"ERROR"+error,Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param= new HashMap<>();
                param.put("searchname",searchname.getText().toString().trim());

                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(UpdateDelete.this);
        requestQueue.add(stringRequest);
    }

    public void updatedata(){
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, update_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int sucess = jsonObject.getInt("sucess");


                    if (sucess==1){

                        Toast.makeText(UpdateDelete.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                        fullname.setText("");
                        email.setText("");
                        mobile.setText("");
                        cityname.setText("");
                        pincode.setText("");
                        password.setText("");
                    }
                    else if (sucess==2){

                        Toast.makeText(UpdateDelete.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(UpdateDelete.this,"Connection Failed",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {

                    Toast.makeText(UpdateDelete.this,"EXCEPTION"+e,Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateDelete.this,"ERROR"+error,Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
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

        RequestQueue requestQueue1= Volley.newRequestQueue(UpdateDelete.this);
        requestQueue1.add(stringRequest1);
    }

    public void deletedata(){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, delete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int sucess= jsonObject.getInt("sucess");

                    if (sucess==1){
                        fullname.setText("");
                        email.setText("");
                        mobile.setText("");
                        cityname.setText("");
                        pincode.setText("");
                        password.setText("");
                        searchname.setText("");
                        Toast.makeText(UpdateDelete.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else if (sucess==2){

                        Toast.makeText(UpdateDelete.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(UpdateDelete.this,"Connection Failed",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(UpdateDelete.this,"EXCEPTION",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateDelete.this,"ERROR",Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paramss= new HashMap<String, String>();
                paramss.put("searchname",fullname.getText().toString().trim());

                return paramss;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(UpdateDelete.this);
        requestQueue.add(stringRequest);
    }

}