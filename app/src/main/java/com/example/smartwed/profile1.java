package com.example.smartwed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class profile1 extends AppCompatActivity {
    private TextView logout;
    private FirebaseAuth mAuth;

    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        logout= findViewById(R.id.logout_btn);
        t1=findViewById(R.id.txt_gmail);
        t2=findViewById(R.id.user_shared_name);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        String email=sp.getString("EMAIL","");
        String name=sp.getString("NAME","");
        t1.setText(email);
        t2.setText(name);
        mAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(profile1.this, SignInActivity.class));
                finish();
            }
        });
    }

    public void back_cutomer_page(View view) {
        Intent intent=new Intent(this,Home_Activity.class);
        startActivity(intent);
    }


    public void txt_3dopener(View view) {
        Intent intent1=new Intent(this,new_designs.class);
        startActivity(intent1);
    }

    public void order_modules(View view) {
        Intent intents=new Intent(this,feedback.class);
        startActivity(intents);
    }
}