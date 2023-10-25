//SessionManagement
package com.example.smartwed.Sessions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.smartwed.SignInActivity;

import java.util.HashMap;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    int id;

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(int user){
        //save session of user whenever user is logged in
        // int id = user.getUserid();
        this.id=user;
        editor.putInt(SESSION_KEY,id).commit();

    }

    public int getSession(){
        //return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY,-1).commit();
    }
}