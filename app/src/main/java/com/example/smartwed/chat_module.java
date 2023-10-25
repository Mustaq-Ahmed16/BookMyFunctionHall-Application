package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class chat_module extends AppCompatActivity {
    EditText editText_name;
    ImageView btn_save,btn_show;
    SharedPreferences sp;
    String namestr;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_module);
        editText_name=findViewById(R.id.messageET1);
        btn_save=findViewById(R.id.sendBtnuser);
        btn_show=findViewById(R.id.next);
        progressDialog = new ProgressDialog(chat_module.this);
        sp=getSharedPreferences( "Myuserpref", Context.MODE_PRIVATE);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namestr=editText_name.getText().toString();
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("NAME",namestr);
                editor.commit();
                Toast.makeText(chat_module.this,"Message Send",Toast.LENGTH_SHORT).show();
            }
        });



        TextView t2;
        t2=findViewById(R.id.chatting_user);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        String name=sp.getString("NAME","");
        t2.setText(name);
    }
}