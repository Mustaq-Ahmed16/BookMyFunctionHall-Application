package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class admin_chats extends AppCompatActivity {
    EditText editText_name;
    ImageView btn_save,btn_show;
    SharedPreferences sp;
    String namestr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chats);
        editText_name=findViewById(R.id.messageET1);
        btn_save=findViewById(R.id.sendBtn1);
        btn_show=findViewById(R.id.next);
        sp=getSharedPreferences( "Myuserpref", Context.MODE_PRIVATE);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namestr=editText_name.getText().toString();
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("NAME",namestr);
                editor.commit();
                Toast.makeText(admin_chats.this,"Message Send",Toast.LENGTH_SHORT).show();
            }
        });

        TextView t2;
        t2=findViewById(R.id.chatting_user);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        String namess=sp.getString("NAME","");
        t2.setText(namess);
    }
}