package com.example.smartwed;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.smartwed.Adapters.DataBaseHelper;

import com.example.smartwed.Sessions.SessionManagement;

public class Admin_login_screen extends AppCompatActivity {
    EditText email, password;
    Button btnlogin1,btnsignupheader;
    DataBaseHelper db;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_screen);
        email = (EditText) findViewById(R.id.manager_mail);
        password = (EditText) findViewById(R.id.manager_password);
        btnlogin1 = (Button) findViewById(R.id.btnLogin_manager);
        progressDialog = new ProgressDialog(Admin_login_screen.this);



        db = new DataBaseHelper(this);

        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails=email.getText().toString();
                String passwords=password.getText().toString();
                String user = email.getText().toString();
                String pass = password.getText().toString();
//             progress();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(Admin_login_screen.this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = db.checkManagerPassword(user, pass);

                    if (checkuserpass == true) {
                        Toast.makeText(Admin_login_screen.this, "SignIn Successful!",
                                Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(getApplicationContext(),Admin_Home.class);
                        startActivity(intent);
                        intent.putExtra("keyname",user);
                        finish();

                    } else {
                        Toast.makeText(Admin_login_screen.this, "If you don't have an account please SignUp",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });


    }

    private void progress() {

//        progressDialog.setTitle("Logging In.....");
//        progressDialog.setMessage("Loading");
//        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                progressDialog.dismiss();
            }
        },5000);
    }


    public void btn_Forget_Password_manager(View view) {
        Intent intent=new Intent(Admin_login_screen.this,Forget_Password.class);
        startActivity(intent);
    }
    public void btn_sigup_manager(View view) {
        Intent intent=new Intent(Admin_login_screen.this,SignUp_Manager.class);
        startActivity(intent);
    }
}