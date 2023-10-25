
package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.smartwed.Adapters.DataBaseHelper;

public class Forget_Password extends AppCompatActivity {

    EditText forgotemail,forgotpass,forgotrepass;
    Button updatepass;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        forgotemail = findViewById(R.id.signupmail);
        forgotpass = findViewById(R.id.new_pass);
        forgotrepass = findViewById(R.id.new_passwordd);
        updatepass = findViewById(R.id.btnchange);

        db = new DataBaseHelper(this);

        updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass,repass;
                try {
                    email = forgotemail.getText().toString();
                    pass = forgotpass.getText().toString();
                    repass = forgotrepass.getText().toString();

                    if (email.equals("") || pass.equals("") || repass.equals("")) {
                        Toast.makeText(Forget_Password.this, "Required all Feilds", Toast.LENGTH_SHORT).show();
                    } else {
                        if (pass.equals(repass)) {
                            int updatepass =   db.updatepass(email,pass);
                            if(updatepass == 1){
                                forgotemail.setText("");
                                forgotpass.setText("");
                                forgotrepass.setText("");
                                Toast.makeText(Forget_Password.this, "Successfully Update password", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Forget_Password.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(Forget_Password.this, "Invalid email", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Forget_Password.this, "Password Not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(Forget_Password.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}



