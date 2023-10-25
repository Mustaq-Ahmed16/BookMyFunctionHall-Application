package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.smartwed.Adapters.DataBaseHelper;

public class SignUp_Manager extends AppCompatActivity {
    EditText name,email,password,confirmpassword;
    Button signup,btnhaveanaccount,btnsignupheader;
    DataBaseHelper db;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__manager);


        name=(EditText) findViewById(R.id.username_manager);
        email=(EditText) findViewById(R.id.signupmail_manager);
        password=(EditText) findViewById(R.id.spassword_manager);
        confirmpassword=(EditText) findViewById(R.id.confirm_passwordd_manager);
        signup=(Button) findViewById(R.id.btnsinups_manager);
        db=new DataBaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=name.getText().toString();
                String useremail=email.getText().toString();
                String pass=password.getText().toString();
                String confirmpass=confirmpassword.getText().toString();
                if(user.equals("")||useremail.equals("")||pass.equals("")||confirmpass.equals("")) {
                    Toast.makeText(SignUp_Manager.this,
                            "Please enter the values", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(useremail.trim().matches(emailPattern)) {
                        if (pass.trim().matches(passwordPattern)) {
                            if (pass.equals(confirmpass)) {
                                Boolean checkuser = db.checkManagerUserName(useremail);
                                if (checkuser == false) {
                                    Boolean insert = db.insertManager(useremail, pass);

                                    if (insert == true) {
                                        Toast.makeText(SignUp_Manager.this,
                                                "SignUp Successful!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Admin_login_screen.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(SignUp_Manager.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(SignUp_Manager.this, "User already exists please SignIn",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(SignUp_Manager.this, "password Mismatched!", Toast.LENGTH_SHORT).show();
                            }
                        }


                        else{Toast.makeText(SignUp_Manager.this,"Password should be one capital" +
                                        " letter ,integers and special characters(#,. etc)",
                                Toast.LENGTH_SHORT).show();}
                    }
                    else{Toast.makeText(SignUp_Manager.this,"Please insert valid email address",Toast.LENGTH_SHORT).show();}
                }}
        });

    }

}