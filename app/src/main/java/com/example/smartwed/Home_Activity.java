package com.example.smartwed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//import com.example.smartwed.ui.NavigationMenu;
import com.example.smartwed.Sessions.SessionManagement;
import com.google.android.material.navigation.NavigationView;

public class Home_Activity extends AppCompatActivity {
    NavigationView nav;
    Button btn_notifi;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    int id;
SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_menu);
        sessionManagement=new SessionManagement(getApplicationContext());
         id=sessionManagement.getSession();
        Toast.makeText(this, "Session save "+id, Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                int id = menuitem.getItemId();
                switch (id) {
                    case R.id.orders:
                        Intent intent = new Intent(Home_Activity.this, OrdersActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.home1:
                    Intent homeintent = new Intent(Home_Activity.this, Customer_dashboard.class);
                    startActivity(homeintent);
                    break;
                    case R.id.chat:
                        Intent chatintent = new Intent(Home_Activity.this, chat_module.class);
                        startActivity(chatintent);
                        break;
//                    case R.id.notifications:
//                        Intent notification = new Intent(Home_Activity.this, NotificationActivity.class);
//                        startActivity(notification);
//                        break;
                    case R.id.logout:
                        Intent logintent = new Intent(Home_Activity.this, Manager_User.class);
                        startActivity(logintent);
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    public void btn_home(View view) {
        Intent intent = new Intent(this, Customer_dashboard.class);
        startActivity(intent);
    }

    public void btn_mybooking(View view) {
        Intent intent = new Intent(this,OrdersActivity.class);
        startActivity(intent);
    }

    public void btn_profile(View view) {
        Intent intent = new Intent(this, add_to_cart.class);
        startActivity(intent);
    }


    public void btn_logout(View view) {

        sessionManagement.removeSession();
        Intent intent = new Intent(this, Manager_User.class);
        startActivity(intent);
    }


    public void btn_designing(View view) {
        Intent intent = new Intent(this, upload_picture.class);
        startActivity(intent);
    }


}
