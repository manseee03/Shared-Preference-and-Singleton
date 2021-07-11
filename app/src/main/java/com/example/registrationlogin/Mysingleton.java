package com.example.registrationlogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;

public class Mysingleton {

    private static final String Shared_name="Shared_Preference";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static Preference Instance;

    private static final String fullname="fullname";
    private static final String email="email";
    private static final String contact="contact_no";
    private static final String cityname="city_name";
    private static final String pincode="pin_code";
    private static final String password="password";
    private static final String isRegister="isRegister";
    private static final String isLogin = "isLogin";

    public Mysingleton(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor= sharedPreferences.edit();
        editor.putInt(Shared_name,0);
        editor.apply();

    }

    public static Preference getInstancePreference (Context context){

        if (Instance == null){
            Instance = new Preference(context);
        }
        return Instance;

    }
    public void setFullname(String fullname1){

        editor.putString(fullname,fullname1).commit();

    }

    public String getFullname() {

        return sharedPreferences.getString(fullname,"");

    }

    public void setEmail(String email1){

        editor.putString(email,email1).commit();

    }

    public String getEmail(){

        return sharedPreferences.getString(email,"");

    }

    public void setContact(String contact1){

        editor.putString(contact,contact1).commit();

    }

    public String getContact(){

        return sharedPreferences.getString(contact,"");

    }

    public void setCityname(String cityname1){

        editor.putString(cityname,cityname1).commit();

    }

    public String getCityname(){
        return sharedPreferences.getString(cityname,"");
    }

    public void setPincode(String pincode1){
        editor.putString(pincode,pincode1).commit();
    }

    public  String getPincode() {
        return sharedPreferences.getString(pincode,"");
    }

    public void setPassword(String password1){
        editor.putString(password,password1).commit();
    }

    public String getPassword() {
        return sharedPreferences.getString(password,"");
    }

    public void setIsRegister(Boolean fisregister)
    {
        editor.putBoolean(isRegister,fisregister).commit();

    }

    public boolean getIsRegister()
    {
        return sharedPreferences.getBoolean(isRegister,false);

    }

    public void setIsLogin(Boolean fislogin)
    {
        editor.putBoolean(isLogin,fislogin).commit();

    }

    public boolean getIsLogin()
    {
        return sharedPreferences.getBoolean(isLogin,false);

    }

}
