package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class admin_header_main extends AppCompatActivity {
TextView email_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_header_main);
        email_txt=findViewById(R.id.drawer_email);
String emails=getIntent().getStringExtra("keyname");
email_txt.setText(emails);

    }
}