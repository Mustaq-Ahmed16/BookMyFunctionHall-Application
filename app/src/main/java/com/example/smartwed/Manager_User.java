package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Manager_User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__user);



    }


    public void activity_user(View view) {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
    }

    public void activity_manager(View view) {
        Intent intent = new Intent(this,Admin_login_screen.class);
        startActivity(intent);
    }
}