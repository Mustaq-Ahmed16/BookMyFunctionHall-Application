package com.example.smartwed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartwed.Adapters.DataBaseHelper;

import com.example.smartwed.Sessions.SessionManagement;

public class SignInActivity extends AppCompatActivity {
    EditText name1, password1;
    SharedPreferences sp;
    Button btnlogin1,btnsignupheader;
    DataBaseHelper db;
    TextView signups;
    int userid;
    ProgressDialog progressDialog ;
SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinactivity);
        sessionManagement=new SessionManagement(getApplicationContext());

        name1 = (EditText) findViewById(R.id.inputEmail);
        password1 = (EditText) findViewById(R.id.inputPassword);
        btnlogin1 = (Button) findViewById(R.id.btnLogin);
        signups=findViewById(R.id.gotoRegister);
        progressDialog = new ProgressDialog(SignInActivity.this);
        sp=getSharedPreferences( "Myuserpref", Context.MODE_PRIVATE);
        db = new DataBaseHelper(this);

        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email=name1.getText().toString();
               String password=password1.getText().toString();
                String user = name1.getText().toString();
                String pass = password1.getText().toString();
               // progress_user();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(SignInActivity.this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = db.checkusernamepassword(user, pass);
                    userid = db.checkUserid(user,pass);
                    if (checkuserpass == true) {
                        Toast.makeText(SignInActivity.this, "SignIn Successful!",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                        sessionManagement.saveSession(userid);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();


                    } else {
                        Toast.makeText(SignInActivity.this, "If you don't have an account please SignUp",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }

    private void progress_user() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                progressDialog.dismiss();
            }
        },6000);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        SignInActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }



    public void btn_Forget_Password(View view){

        Intent intent=new Intent(SignInActivity.this,Forget_Password.class);
        startActivity(intent);

    }


    public void btn_sigup2(View view) {
        Intent intent=new Intent(SignInActivity.this,SignUp_page.class);
        startActivity(intent);
    }


    public void keyboard_down_signin(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
