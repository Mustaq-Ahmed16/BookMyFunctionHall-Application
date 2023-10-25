package com.example.smartwed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Admin_Home extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_navigaion);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = (NavigationView) findViewById(R.id.navmenu2);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

                switch (menuitem.getItemId()) {
                    case R.id.menu_home:
                        Intent homeintent = new Intent(Admin_Home.this, Admin_Home.class);
                        startActivity(homeintent);
                        Toast.makeText(getApplicationContext(), "Home Panel is Open", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu_call:
                        Intent orderintent = new Intent(Admin_Home.this, Confirm_Order.class);
                        startActivity(orderintent);
                        Toast.makeText(getApplicationContext(), "Order Panel is Open", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.menu_setting:
                        Intent chatsintent = new Intent(Admin_Home.this, admin_chats.class);
                        startActivity(chatsintent);
                        Toast.makeText(getApplicationContext(), "Chat Panel is Open", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.manager_logout:
                        Intent logoutintent = new Intent(Admin_Home.this, Manager_User.class);
                        startActivity(logoutintent);
                        Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();

                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



}

    public void btn_addhall(View view) {
        Intent intent = new Intent(this,AddHall.class);
        startActivity(intent);
    }
    public void btn_UpdateHall(View view) {
        Intent intent = new Intent(this,showaddhall.class);
        startActivity(intent);
    }
    public void btn_RemoveHall(View view) {
        Intent intent = new Intent(this,Admin_Hall_Remove.class);
        startActivity(intent);
    }


    public void btn_confirmorder(View view) {
        Intent intent = new Intent(this,Confirm_Order.class);
        startActivity(intent);
    }
}